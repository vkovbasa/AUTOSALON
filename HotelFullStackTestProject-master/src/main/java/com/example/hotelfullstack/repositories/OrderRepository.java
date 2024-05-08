package com.example.hotelfullstack.repositories;

import com.example.hotelfullstack.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByDateOrderAndOrderStatus(LocalDate date, String status);

    @Query("SELECT SUM(o.car.price) FROM Order o WHERE o.orderStatus = :status")
    BigDecimal sumSalePriceByOrderStatus(@Param("status") String status);
    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.car c WHERE c.id IS NOT NULL")
    List<Order> findOrdersWithCarDetails();


    List<Order> findOrdersWithCarDetailsByCar_Brand(String brand);

    List<Order> findOrdersWithCarDetailsByCar_ModelContaining(String keyword);

    @Query("SELECT o FROM Order o WHERE EXISTS (SELECT s FROM Sale s WHERE s.order = o)")
    List<Order> findOrdersWithSales();}
