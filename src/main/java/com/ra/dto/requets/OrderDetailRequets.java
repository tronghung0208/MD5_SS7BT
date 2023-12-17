package com.ra.dto.requets;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailRequets {
    private Integer productId;

    private Integer quantity;

}
