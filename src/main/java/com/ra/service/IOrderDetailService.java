package com.ra.service;

import com.ra.dto.reponse.OrderDetailResponse;
import com.ra.dto.reponse.OrderResponse;
import com.ra.dto.requets.OrderDetailRequets;
import com.ra.exception.CustomException;

import java.util.List;

public interface IOrderDetailService {
    OrderResponse addNewOrderDetail (OrderDetailRequets orderDetailRequest, Integer idUser) throws CustomException;

    List<OrderDetailResponse> getAllOrderDetailByIdOrder(Integer idOrder) throws CustomException;
}
