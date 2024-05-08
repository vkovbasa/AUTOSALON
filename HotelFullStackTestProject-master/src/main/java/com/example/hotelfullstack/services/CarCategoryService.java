package com.example.hotelfullstack.services;

import com.example.hotelfullstack.exceptions.ResourceNotFoundException;
import com.example.hotelfullstack.models.CarCategory;
import com.example.hotelfullstack.repositories.CarCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarCategoryService {

    @Autowired
    private CarCategoryRepository carCategoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<CarCategory> getAllCarCategories() {
        return carCategoryRepository.findAll();
    }

    public CarCategory getCarCategoryById(Long id) {
        return carCategoryRepository.findById(id).orElse(null);
    }

    public CarCategory saveCarCategory(CarCategory carCategory) {
        return carCategoryRepository.save(carCategory);
    }
    public CarCategory editCatCategory(Long id, CarCategory carCategory) {
        CarCategory updateCarCategory = carCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Car Category not found id : " + id)
        );
        modelMapper.map(carCategory, updateCarCategory);

        return carCategoryRepository.save(updateCarCategory);
    }

    public void deleteCarCategory(Long id) {
        carCategoryRepository.deleteById(id);
    }
}
