package com.ra.dto.reponse;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private Integer idOrder;

    private String username;

    private List<OrderDetailResponse> orderDetailResponses;
}
