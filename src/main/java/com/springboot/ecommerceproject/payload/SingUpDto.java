package com.springboot.ecommerceproject.payload;


import lombok.Data;

@Data
public class SingUpDto {
    private String name;
    private String username;
    private String email;
    private String password;

}
