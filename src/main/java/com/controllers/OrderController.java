package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.OrderEntity;
import com.services.iml.ImlOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ImlOrderService orderService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<OrderEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return orderService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<OrderEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return orderService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<OrderEntity> readById(@PathVariable Integer id) {
        return orderService.readById(id);
    }

    @PostMapping("/create")
    public OrderEntity create(@RequestBody OrderEntity order) {
        return orderService.create(order);
    }

    @PutMapping("/update")
    public OrderEntity update(@RequestBody OrderEntity order) {
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public OrderEntity delete(@PathVariable Integer id) {
        return orderService.delete(id);
    }
}
