package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.ProductDetailEntity;
import com.services.iml.ImlProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/product-detail")
@CrossOrigin
public class ProductDetailController {
    @Autowired
    private ImlProductDetailService productDetailService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<ProductDetailEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return productDetailService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<ProductDetailEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return productDetailService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<ProductDetailEntity> readById(@PathVariable Integer id) {
        return productDetailService.readById(id);
    }

    @PostMapping("/create")
    public ProductDetailEntity create(@RequestBody ProductDetailEntity productDetail) {
        return productDetailService.create(productDetail);
    }

    @PutMapping("/update")
    public ProductDetailEntity update(@RequestBody ProductDetailEntity productDetail) {
        return productDetailService.update(productDetail);
    }

    @DeleteMapping("/{id}")
    public ProductDetailEntity delete(@PathVariable Integer id) {
        return productDetailService.delete(id);
    }
}
