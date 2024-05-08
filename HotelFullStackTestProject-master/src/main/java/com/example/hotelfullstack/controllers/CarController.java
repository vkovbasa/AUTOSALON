package com.example.hotelfullstack.controllers;

import com.example.hotelfullstack.dtos.CarDTO;
import com.example.hotelfullstack.mapper.CarMapper;
import com.example.hotelfullstack.models.Car;
import com.example.hotelfullstack.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carMapper.toListEntity(carService.getAllCars());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<Car>> getCarsBetweenDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Car> cars = carService.getCarsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Car>> getCarsByBrands(@RequestParam("brandName") List<String> brands) {
        List<Car> cars = carService.getCarsByBrands(brands);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/distinct-brands")
    public ResponseEntity<List<Map<String, String>>> getDistinctBrands() {
        List<Map<String, String>> brands = carService.getDistinctBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/min-price")
    public ResponseEntity<Map<String, BigDecimal>> getMinPrice() {
        Map<String, BigDecimal> minPrice = carService.getMinPrice();
        return ResponseEntity.ok(minPrice);
    }

    @GetMapping("/brand-count")
    public ResponseEntity<List<Object[]>> getBrandAndCount() {
        List<Object[]> brandAndCount = carService.getBrandAndCount();
        return ResponseEntity.ok(brandAndCount);
    }
    @GetMapping("/price-greater-than-average")
    public ResponseEntity<List<Car>> getCarsWithPriceGreaterThanAverage() {
        List<Car> cars = carService.getCarsWithPriceGreaterThanAverage();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/price-greater-than-any-sale-price")
    public ResponseEntity<List<Car>> getCarsWithPriceGreaterThanAnySalePrice() {
        List<Car> cars = carService.getCarsWithPriceGreaterThanAnySalePrice();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/with-sale")
    public ResponseEntity<List<Car>> getCarsWithSale() {
        List<Car> cars = carService.getCarsWithSale();
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody CarDTO carDTO) {
        Car savedCar = carService.saveCar(carDTO);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> addCar(@PathVariable("id") Long id, @RequestBody CarDTO carDTO) {
        Car savedCar = carService.updateCar(id, carDTO);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
