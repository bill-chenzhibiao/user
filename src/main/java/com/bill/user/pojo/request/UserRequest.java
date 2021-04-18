package com.bill.user.pojo.request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
}
