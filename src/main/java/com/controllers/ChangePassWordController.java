package com.controllers;

import com.client.request.ChangePassWord;
import com.entities.UserEntity;
import com.services.ChangePassWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePassWordController {
    @Autowired
    private ChangePassWordService changePassWordService;

    @PutMapping("/changepassword")
    public UserEntity forgotPassWord(@RequestBody ChangePassWord changePassWord) {
        return changePassWordService.changePassWord(changePassWord);
    }
}
