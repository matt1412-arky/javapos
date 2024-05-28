package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.OrderDetail;
import com.xsis.javapos.models.OrderHeader;
import com.xsis.javapos.services.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderApiController {
    @Autowired
	private OrderService orderSvc;
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		try {
			//TODO: process Order data request
			List<OrderHeader> data = orderSvc.getAll();
			
			if (data.size()>0)
				return new ResponseEntity<List<OrderHeader>>(data, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("getAllDetail/{orderHeaderId}")
	public ResponseEntity<?> getById(@PathVariable long orderHeaderId){
		try {
			//TODO: process Order Details data request
			List<OrderDetail> data = orderSvc.getAllDetail(orderHeaderId);
			
			if (data.isEmpty()){
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<OrderDetail>>(data, HttpStatus.OK);
			}
		} catch (Exception ex) {
			// TODO: handle exception
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody final OrderHeader data) {
		try {
			//TODO: process CREATE New Order request
			OrderHeader newOrderHeader = orderSvc.Create(data);
			
			if (newOrderHeader.getId() > 0) {
				return new ResponseEntity<OrderHeader>(newOrderHeader, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("Order already Existed!", HttpStatus.CONFLICT);
			}
		}
		catch (Exception ex) {
			// TODO: handle CREATE New Order process exception
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody final OrderHeader data) {
		try {
			//TODO: process UPDATE Order request
			OrderHeader orderHeader = orderSvc.Update(data);
			
			if (orderHeader.getId() > 0) {
				return new ResponseEntity<OrderHeader>(orderHeader, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Order (ID = " + data.getId() + ") does not exist!", HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception ex) {
			// TODO: handle UPDATE Order process exception
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/{orderHeaderId}/{userId}")
	public ResponseEntity<?> delete(@PathVariable final long orderHeaderId, @PathVariable final int userId) {
		try {
			//TODO: process DELETE Order request
			if (orderSvc.Delete(orderHeaderId, userId)) {
				return new ResponseEntity<OrderHeader>(orderSvc.getById(orderHeaderId), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Order (ID = " + orderHeaderId + ") does not exist!", HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception ex) {
			// TODO: handle DELETE Order process exception
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

}
