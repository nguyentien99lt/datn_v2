package com.controllers;


import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.CartEntity;
import com.services.iml.ImlCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ImlCartService cartService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<CartEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return cartService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<CartEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return cartService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<CartEntity> readById(@PathVariable Integer id) {
        return cartService.readById(id);
    }

    @PostMapping("/create")
    public CartEntity create(@RequestBody CartEntity cart) {
        return cartService.create(cart);
    }

    @PutMapping("/update")
    public CartEntity update(@RequestBody CartEntity cart) {
        return cartService.update(cart);
    }

    @DeleteMapping("/{id}")
    public CartEntity delete(@PathVariable Integer id) {
        return cartService.delete(id);
    }

}
