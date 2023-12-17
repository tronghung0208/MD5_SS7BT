package com.ra.service.impl;

import com.ra.dto.reponse.OrderDetailResponse;
import com.ra.dto.reponse.OrderResponse;
import com.ra.dto.requets.OrderDetailRequets;
import com.ra.exception.CustomException;
import com.ra.model.OrderDetail;
import com.ra.model.Orders;
import com.ra.model.Product;
import com.ra.model.User;
import com.ra.repositoty.IOrderDetailRepository;
import com.ra.repositoty.IOrderRepository;
import com.ra.repositoty.IProductRepository;
import com.ra.repositoty.IUserRepository;
import com.ra.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    IOrderDetailRepository orderDetailRepository;
    @Override
    public OrderResponse addNewOrderDetail(OrderDetailRequets orderDetailRequest, Integer idUser) throws CustomException {
        User user = userRepository.findById(idUser).orElseThrow(() -> new CustomException("user not found"));

        Optional<Orders> optionalOrders = orderRepository.findByStatusAndUserId(false, idUser);

        if (optionalOrders.isPresent()) {
            Product product = productRepository.findById(orderDetailRequest.getProductId()).orElseThrow(() -> new CustomException("product not found"));
            Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findByOrdersIdAndProductId(optionalOrders.get().getId(), product.getId());
            if (optionalOrderDetail.isPresent()) {
                OrderDetail oldOrderDetail = optionalOrderDetail.get();
                oldOrderDetail.setQuantity(oldOrderDetail.getQuantity() + orderDetailRequest.getQuantity());
                orderDetailRepository.save(oldOrderDetail);
            } else {
                OrderDetail orderDetail = OrderDetail.builder()
                        .orders(optionalOrders.get())
                        .price(product.getPrice())
                        .product(product)
                        .quantity(orderDetailRequest.getQuantity())
                        .build();
                orderDetailRepository.save(orderDetail);
            }
            return OrderResponse.builder()
                    .idOrder(optionalOrders.get().getId())
                    .username(optionalOrders.get().getUser().getUsername())
                    .orderDetailResponses(orderDetailRepository.findAllByOrdersId(optionalOrders.get().getId()).stream().map(item -> OrderDetailResponse.builder()
                            .id(item.getId())
                            .productName(item.getProduct().getProduct_name())
                            .price(item.getPrice())
                            .quantity(item.getQuantity())
                            .build()).toList())
                    .build();
        }
        throw new CustomException("order not found");
    }

    @Override
    public List<OrderDetailResponse> getAllOrderDetailByIdOrder(Integer idOrder) throws CustomException {
        Orders orders = orderRepository.findById(idOrder).orElseThrow(()->new CustomException("order not found"));

        List<OrderDetailResponse> list = orderDetailRepository.findAllByOrdersId(idOrder).stream().map(item -> OrderDetailResponse.builder()
                .id(item.getId())
                .productName(item.getProduct().getProduct_name())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build()).toList();

        return list;
    }
}
