package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.OrderDetailEntity;
import com.services.iml.ImlOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {
    @Autowired
    private ImlOrderDetailService orderDetailService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<OrderDetailEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return orderDetailService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<OrderDetailEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return orderDetailService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<OrderDetailEntity> readById(@PathVariable Integer id) {
        return orderDetailService.readById(id);
    }

    @PostMapping("/create")
    public OrderDetailEntity create(@RequestBody OrderDetailEntity orderDetail) {
        return orderDetailService.create(orderDetail);
    }

    @PutMapping("/update")
    public OrderDetailEntity update(@RequestBody OrderDetailEntity orderDetail) {
        return orderDetailService.update(orderDetail);
    }

    @DeleteMapping("/{id}")
    public OrderDetailEntity delete(@PathVariable Integer id) {
        return orderDetailService.delete(id);
    }
}
