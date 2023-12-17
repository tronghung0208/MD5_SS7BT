package com.ra.service;

import com.ra.dto.reponse.ListOrder;
import com.ra.dto.reponse.OrdersDTO;
import com.ra.dto.requets.CreateOrder;
import com.ra.exception.CustomException;
import com.ra.model.Orders;

import java.util.List;

public interface IOrderService {
    Orders addNewOrderToUserByIdUser(Integer idUser, CreateOrder createOrder) throws CustomException;
    List<ListOrder> getAllOrder();
    List<OrdersDTO> getOrders();

    void updateByStatusAndId(Boolean status,Integer id);
}
