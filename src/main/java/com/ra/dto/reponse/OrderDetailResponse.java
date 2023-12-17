package com.ra.dto.reponse;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailResponse {
    private Integer id;

    private String productName;

    private Double price;

    private Integer quantity;
}
