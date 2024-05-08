package com.example.hotelfullstack.repositories;

import com.example.hotelfullstack.dtos.BrandDto;
import com.example.hotelfullstack.models.Car;
import com.example.hotelfullstack.models.Client;
import com.example.hotelfullstack.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByDateOfIssueBetween(LocalDate startDate, LocalDate endDate);
    List<Car> findByBrandIn(List<String> brands);
    @Query("SELECT new map(MIN(c.price) as minPrice) FROM Car c")
    Map<String, BigDecimal> findMinPrice();

    @Query("SELECT DISTINCT new map(c.brand as brandName) FROM Car c")
    List<Map<String, String>> findDistinctBrands();

    @Query("SELECT c.brand, COUNT(c) FROM Car c GROUP BY c.brand")
    List<Object[]> findBrandAndCount();

    @Query("SELECT c FROM Car c WHERE c.price > (SELECT AVG(c2.price) FROM Car c2)")
    List<Car> findCarsWithPriceGreaterThanAverage();
    @Query("SELECT c FROM Car c WHERE c.price > ANY (SELECT s.salePrice FROM Sale s WHERE s.order.car = c)")
    List<Car> findCarsWithPriceGreaterThanAnySalePrice();
    @Query("SELECT c FROM Car c WHERE EXISTS (SELECT s FROM Sale s WHERE s.order.car = c)")
    List<Car> findCarsWithSale();
}
