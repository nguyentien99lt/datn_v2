package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.ProductEntity;
import com.services.iml.ImlProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ImlProductService productService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<ProductEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return productService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<ProductEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return productService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> readById(@PathVariable Integer id) {
        return productService.readById(id);
    }

    @PostMapping("/create")
    public ProductEntity create(@RequestBody ProductEntity product) {
        return productService.create(product);
    }

    @PutMapping("/update")
    public ProductEntity update(@RequestBody ProductEntity product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public ProductEntity delete(@PathVariable Integer id) {
        return productService.delete(id);
    }
}
