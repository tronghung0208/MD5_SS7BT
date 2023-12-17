package com.ra.controller;

import com.ra.dto.reponse.OrderDetailResponse;
import com.ra.dto.reponse.OrderResponse;
import com.ra.dto.requets.OrderDetailRequets;
import com.ra.exception.CustomException;
import com.ra.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @PostMapping("/{idUser}")
    public ResponseEntity<OrderResponse> addNewOrderDetail(@RequestBody OrderDetailRequets orderDetailRequest, @PathVariable Integer idUser) throws CustomException {
        return new ResponseEntity<>(orderDetailService.addNewOrderDetail(orderDetailRequest, idUser), HttpStatus.CREATED);
    }

    @GetMapping("/{idOrder}")
    public ResponseEntity<List<OrderDetailResponse>> getAllOrderDetailByIdOrder(@PathVariable Integer idOrder) throws CustomException {
        return new ResponseEntity<>(orderDetailService.getAllOrderDetailByIdOrder(idOrder), HttpStatus.OK);
    }
}
