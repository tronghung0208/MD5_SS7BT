package com.ra.service.impl;

import com.ra.dto.reponse.ListOrder;
import com.ra.dto.reponse.OrdersDTO;
import com.ra.dto.requets.CreateOrder;
import com.ra.exception.CustomException;
import com.ra.model.Orders;
import com.ra.model.User;
import com.ra.repositoty.IOrderRepository;
import com.ra.repositoty.IUserRepository;
import com.ra.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IOrderRepository orderRepository;

    @Override
    public Orders addNewOrderToUserByIdUser(Integer idUser, CreateOrder createOrder) throws CustomException {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Orders orders = Orders.builder()
                    .address(createOrder.getAddress())
                    .phone(createOrder.getPhone())
                    .note(createOrder.getNote())
                    .created(createOrder.getCreated())
                    .status(false)
                    .user(user)
                    .build();
            return orderRepository.save(orders);
        }
        throw new CustomException("user not found");
    }

    @Override
    public List<ListOrder> getAllOrder() {
        List<ListOrder> listOrders=orderRepository.findAll().stream().map(item->ListOrder.builder()
                .id(item.getId())
                .address(item.getAddress())
                .phone(item.getPhone())
                .note(item.getNote())
                .created(item.getCreated())
                .status(item.getStatus())
                .username(item.getUser().getUsername())
                .build()).toList();
        return listOrders;
    }

    @Override
    public List<OrdersDTO> getOrders() {
        return null;
    }

    @Override
    public void updateByStatusAndId(Boolean status, Integer id) {
        orderRepository.updateStatus(status,id);
    }
}
