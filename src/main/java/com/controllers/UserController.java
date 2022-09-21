package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.UserEntity;
import com.services.iml.ImlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ImlUserService userService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<UserEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return userService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<UserEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<UserEntity> readById(@PathVariable Integer id) {
        return userService.readById(id);
    }

    @PostMapping("/create")
    public UserEntity create(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @PutMapping("/update")
    public UserEntity update(@RequestBody UserEntity user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public UserEntity delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
