package com.ra.dto.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdersDTO {
    private Integer id;

    private String address;

    private String phone;

    private String note;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created;

    private Boolean status;

    private String username;
    private String email;
}
