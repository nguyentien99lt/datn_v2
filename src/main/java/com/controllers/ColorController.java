package com.controllers;

import com.client.request.FindByPageRequest;
import com.client.response.FindByPageResponse;
import com.entities.ColorEntity;
import com.services.iml.ImlColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ImlColorService colorService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<ColorEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return colorService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<ColorEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return colorService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/read-by-id/{id}")
    public Optional<ColorEntity> readById(@PathVariable Integer id) {
        return colorService.readById(id);
    }

    @PostMapping("/create")
    public ColorEntity create(@RequestBody ColorEntity color) {
        return colorService.create(color);
    }

    @PutMapping("/update")
    public ColorEntity update(@RequestBody ColorEntity color) {
        return colorService.update(color);
    }

    @DeleteMapping("/{id}")
    public ColorEntity delete(@PathVariable Integer id) {
        return colorService.delete(id);
    }
}
