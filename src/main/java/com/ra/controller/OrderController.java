package com.ra.controller;

import com.ra.dto.reponse.ListOrder;
import com.ra.dto.reponse.OrdersDTO;
import com.ra.dto.requets.CreateOrder;
import com.ra.exception.CustomException;
import com.ra.model.Orders;
import com.ra.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;
    @PostMapping("/{idUser}")
    public ResponseEntity<Orders> addNewOrderToUserByIdUser(@PathVariable Integer idUser, @RequestBody CreateOrder createOrder) throws CustomException {
        return new ResponseEntity<>(orderService.addNewOrderToUserByIdUser(idUser,createOrder), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<ListOrder>> getAllOrder(){
        return new ResponseEntity<>(orderService.getAllOrder(),HttpStatus.OK);
    }
}
