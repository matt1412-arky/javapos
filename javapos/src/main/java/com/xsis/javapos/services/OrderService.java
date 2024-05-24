package com.xsis.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.server.ResponseStatusException;

import com.xsis.javapos.models.OrderDetail;
import com.xsis.javapos.models.OrderHeader;
import com.xsis.javapos.models.Product;
import com.xsis.javapos.repositories.OrderDetailRepository;
import com.xsis.javapos.repositories.OrderHeaderRepository;
import com.xsis.javapos.repositories.ProductRepository;

@Transactional
@Service
public class OrderService {
    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;
    
    private Optional<OrderHeader> orderHeaderExsist;

    private Optional<OrderDetail> orderDetailExsist;

    private Optional<Product> productExsist;

    public List<OrderHeader> getAll() throws Exception {
        try {
            return orderHeaderRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

    public List<OrderDetail> getAllDetail(long oderHeaderId) {
		List<OrderDetail> data = orderDetailRepository.findByOrderHeaderIdNative(oderHeaderId).get();
		
		if (data.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product table has no data!");

		return data;
	}
	
	public OrderHeader Create(OrderHeader data) throws Exception {
		try {
			orderHeaderExsist = orderHeaderRepository.findById(data.getId());
			
			if (orderHeaderExsist.isEmpty()) {
				//Insert new Order Header Process
				orderHeaderRepository.save(data);
				
				//Insert new Order Details Process
				for (OrderDetail orderDetail : data.getOrderDetails()) {
					orderDetail.setOrderHeaderId(data.getId());
					orderDetail.setCreateBy(data.getCreateBy());
					orderDetail.setCreateDate(data.getCreateDate());
					
					orderDetailRepository.save(orderDetail);

                    productExsist = productRepository.findById(orderDetail.getProductId());

                    if (productExsist.isPresent()) {
                        int currStock = productExsist.get().getStock();
                        if (currStock >= orderDetail.getQty()) {
                            currStock -= orderDetail.getQty();

                            productExsist.get().setStock(currStock);
                            productRepository.save(productExsist.get());
                        } else {
                            throw new Exception( "Create new Order canceled! "
                                    + "Product with ID " + orderDetail.getProductId() + " does not have enough stock!");
                        }
                    } else {
                        throw new Exception( "Create new Order canceled! "
                                + "Product with ID " + orderDetail.getProductId() + " does not exsist!");
                    }
				}
				
				return data;
			} else {
				//Cancel Process
				return new OrderHeader();
			}			
		}
		catch (Exception e) {
			// TODO: handle INSERT process exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			throw e;
		}
	}
    
    public OrderHeader update(OrderHeader order) throws Exception {
		try {
			orderHeaderExsist = orderHeaderRepository.findById(order.getId());
			
			if (!orderHeaderExsist.isEmpty()) {
				//Update new Order Header Process
				order.setCreateBy(orderDetailExsist.get().getCreateBy());
				order.setCreateDate(orderDetailExsist.get().getCreateDate());
				order.setUpdateDate(LocalDateTime.now());
				
				orderHeaderRepository.save(order);
				
				//Update new Order Details Process
				for (OrderDetail orderDetail : order.getOrderDetails()) {
					OrderDetail orderDetailExsist = orderDetailRepository.findById(orderDetail.getId()).get();
					
					orderDetail.setCreateBy(orderDetailExsist.getCreateBy());
					orderDetail.setCreateDate(orderDetailExsist.getCreateDate());
					orderDetail.setUpdateDate(LocalDateTime.now());
					
					//Update Product Stock process
					productExsist = productRepository.findById(orderDetail.getProductId());
					//Get current stock
					int currStock = productExsist.get().getStock();
					if (productExsist.isPresent()) {	
						//If current order less than before
						if (orderDetail.getQty() < orderDetailExsist.getQty()) {
							currStock += (orderDetailExsist.getQty() - orderDetail.getQty());
						}
						else {
							//If current order more than before
							if (currStock >= (orderDetail.getQty() - orderDetailExsist.getQty())) {
								currStock -= (orderDetail.getQty() - orderDetailExsist.getQty());
							}
							else {
								throw new Exception(
									"UPDATE Order canceled! "
									+ "Product (ID = "+orderDetail.getProductId()+") does not have enough STOCK."
								);
							}
						}
						
						//Update PRODUCT Stock data
						productRepository.updateStock(orderDetail.getProductId(), currStock);
					}
					else {
						throw new Exception(
							"Create new Order canceled! "
							+ "Product (ID = " + orderDetail.getProductId() + ") does not exist."
						);
					}

					//Update the TABLES
					orderDetailRepository.save(orderDetail);
				}
				
				return order;
			}
			else {
				//Cancel Process
				return new OrderHeader();
			}			
		}
		catch (Exception ex) {
			// TODO: handle INSERT process exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			throw ex;
		}
	}

    public OrderHeader delete(Long orderHeaderId, int userId) throws Exception{
		try {
			orderHeaderExsist = orderHeaderRepository.findById(orderHeaderId);
			
			//Check if Order Exist
			if (orderHeaderExsist.isPresent()) {
				if (orderHeaderExsist.get().isCheckedOut()) {
					throw new Exception("Order has already been CHECKED OUT!"); 
				}
				
				//Delete selected Order
				orderHeaderRepository.deleteOrderHeader(orderHeaderId, userId);
				
				//Check if Order Details exist
				List<OrderDetail> orderDetailExisting = orderHeaderExsist.get().getOrderDetails(); 
				if (!orderDetailExisting.isEmpty()) {
					//Delete all Order Details items
					orderDetailRepository.deleteOrderDetail(orderHeaderId, userId, orderDetailExsist.get().getUpdateDate());
					
					//Update Stock per Product
					for(OrderDetail item : orderDetailExisting) {
						productExsist = productRepository.findById(item.getProductId());
						if (productExsist.isPresent()){
							int currStock = productExsist.get().getStock();
							
							// Return Item Quantity to Product Stock
							productRepository.updateStock(productExsist.get().getId(), currStock+item.getQty());
						}
						else {
							throw new Exception("Product (ID = " + productExsist.get().getId() + ") does not exist!");
						}
					}
				}
			}
			
			return orderHeaderExsist.get();
		} catch (Exception e) {
			// TODO: handle DELETE process exception
			// Roll back All DELETE Transaction
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			throw e;
		}
	}
}
