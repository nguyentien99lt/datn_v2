package com.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassWord {
    private String email;
    private String oldPassword;
    private String newPassWord;
    private  String confirmPassWord;
}
