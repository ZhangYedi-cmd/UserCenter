package com.zhang.model.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    private String userAccount;
    private String userPassword;
}
