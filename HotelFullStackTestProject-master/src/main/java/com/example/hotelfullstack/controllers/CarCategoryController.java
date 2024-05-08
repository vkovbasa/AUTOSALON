package com.example.hotelfullstack.controllers;

import com.example.hotelfullstack.models.CarCategory;
import com.example.hotelfullstack.services.CarCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-categories")
public class CarCategoryController {

    @Autowired
    private CarCategoryService carCategoryService;

    @GetMapping
    public ResponseEntity<List<CarCategory>> getAllCarCategories() {
        List<CarCategory> carCategories = carCategoryService.getAllCarCategories();
        return new ResponseEntity<>(carCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarCategory> getCarCategoryById(@PathVariable("id") Long id) {
        CarCategory carCategory = carCategoryService.getCarCategoryById(id);
        if (carCategory != null) {
            return new ResponseEntity<>(carCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CarCategory> addCarCategory(@RequestBody CarCategory carCategory) {
        CarCategory savedCarCategory = carCategoryService.saveCarCategory(carCategory);
        return new ResponseEntity<>(savedCarCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarCategory> editCarCategory(@PathVariable("id") Long id, @RequestBody CarCategory carCategory) {
        CarCategory savedCarCategory = carCategoryService.editCatCategory(id, carCategory);
        return new ResponseEntity<>(savedCarCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarCategory(@PathVariable("id") Long id) {
        carCategoryService.deleteCarCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
