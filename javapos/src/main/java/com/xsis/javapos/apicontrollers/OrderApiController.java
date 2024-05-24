package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> get() {
        try {
            List<OrderHeader> data = orderSvc.getAll();

            if (data.size() > 0) {
                return new ResponseEntity<List<OrderHeader>>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllDetail/{orderHeaderId}")
	public ResponseEntity<?> getById(@PathVariable long orderHeaderId){
		try {
			//TODO: process Order Details data request
			List<OrderDetail> data = orderSvc.getAllDetail(orderHeaderId);
			
			if (data.isEmpty())
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<List<OrderDetail>>(data, HttpStatus.OK);
		}
		catch (Exception ex) {
			// TODO: handle exception
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("")
	public ResponseEntity<?> Create (@RequestBody final OrderHeader data) {
        //TODO: process POST request
		try {
			OrderHeader newOrderHeader = orderSvc.Create(data);

            if (newOrderHeader.getId() > 0) {
    	        return new ResponseEntity<OrderHeader>(newOrderHeader, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Order already exists!", HttpStatus.CONFLICT);
            }			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PutMapping("")
	public ResponseEntity<?> update(@RequestBody final OrderHeader data) {
		try {
			//TODO: process UPDATE Order request
			OrderHeader orderHeader = orderSvc.update(data);
			
			if (orderHeader.getId() > 0) {
				return new ResponseEntity<OrderHeader>(orderHeader, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Order does not exist!", HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception ex) {
			// TODO: handle UPDATE Order process exception
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}
