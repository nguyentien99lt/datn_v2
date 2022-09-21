package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.BrandEntity;
import com.services.iml.ImlBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private ImlBrandService brandService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<BrandEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return brandService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<BrandEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return brandService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<BrandEntity> readById(@PathVariable Integer id) {
        return brandService.readById(id);
    }

    @PostMapping("/create")
    public BrandEntity create(@RequestBody BrandEntity brand) {
        return brandService.create(brand);
    }

    @PutMapping("/update")
    public BrandEntity update(@RequestBody BrandEntity brand) {
        return brandService.update(brand);
    }

    @DeleteMapping("/{id}")
    public BrandEntity delete(@PathVariable Integer id) {
        return brandService.delete(id);
    }


}
