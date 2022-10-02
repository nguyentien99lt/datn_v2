package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.SizeEntity;
import com.services.iml.ImlSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private ImlSizeService sizeService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<SizeEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return sizeService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<SizeEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return sizeService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/read-by-id/{id}")
    public Optional<SizeEntity> readById(@PathVariable Integer id) {
        return sizeService.readById(id);
    }

    @PostMapping("/create")
    public SizeEntity create(@RequestBody SizeEntity size) {
        return sizeService.create(size);
    }

    @PutMapping("/update")
    public SizeEntity update(@RequestBody SizeEntity size) {
        return sizeService.update(size);
    }

    @DeleteMapping("/{id}")
    public SizeEntity delete(@PathVariable Integer id) {
        return sizeService.delete(id);
    }
}
