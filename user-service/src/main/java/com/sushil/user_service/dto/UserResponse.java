package com.sushil.user_service.dto;

import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String email;
    private String name;
}
