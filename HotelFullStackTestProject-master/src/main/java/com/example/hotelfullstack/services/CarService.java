package com.example.hotelfullstack.services;

import com.example.hotelfullstack.dtos.CarDTO;
import com.example.hotelfullstack.exceptions.ResourceNotFoundException;
import com.example.hotelfullstack.models.Car;
import com.example.hotelfullstack.models.CarCategory;
import com.example.hotelfullstack.repositories.CarCategoryRepository;
import com.example.hotelfullstack.repositories.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarCategoryRepository carCategoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getCarsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return carRepository.findByDateOfIssueBetween(startDate, endDate);
    }

    public List<Car> getCarsByBrands(List<String> brands) {
        return carRepository.findByBrandIn(brands);
    }

    public Map<String, BigDecimal> getMinPrice() {
        return carRepository.findMinPrice();
    }

    public List<Map<String, String>> getDistinctBrands() {
        return carRepository.findDistinctBrands();
    }

    public List<Object[]> getBrandAndCount() {
        return carRepository.findBrandAndCount();
    }

    public List<Car> getCarsWithPriceGreaterThanAverage() {
        return carRepository.findCarsWithPriceGreaterThanAverage();
    }

    public List<Car> getCarsWithPriceGreaterThanAnySalePrice() {
        return carRepository.findCarsWithPriceGreaterThanAnySalePrice();
    }

    public List<Car> getCarsWithSale() {
        return carRepository.findCarsWithSale();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(CarDTO carDTO) {
        CarCategory category = carCategoryRepository.findById(carDTO.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Car Category not found id : " + carDTO.getCategoryId())
        );

        Car car = modelMapper.map(carDTO, Car.class);
        car.setCategory(category);

        return carRepository.save(car);
    }
    public Car updateCar(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Car not found id : " + id)
        );
        CarCategory category = carCategoryRepository.findById(carDTO.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Car Category not found id : " + carDTO.getCategoryId())
        );

        /// Оновлення полів автомобіля, крім ідентифікатора
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setDateOfIssue(carDTO.getDateOfIssue());
        car.setPrice(carDTO.getPrice());

        car.setCategory(category);

        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
