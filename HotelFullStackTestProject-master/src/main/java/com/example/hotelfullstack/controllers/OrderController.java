package com.example.hotelfullstack.controllers;

import com.example.hotelfullstack.dtos.CarDTO;
import com.example.hotelfullstack.dtos.OrderDTO;
import com.example.hotelfullstack.mapper.CarMapper;
import com.example.hotelfullstack.mapper.OrderMapper;
import com.example.hotelfullstack.models.Car;
import com.example.hotelfullstack.models.Order;
import com.example.hotelfullstack.services.CarService;
import com.example.hotelfullstack.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> cars = orderMapper.toListEntity(orderService.getAllOrders());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-date-and-status")
    public ResponseEntity<List<Order>> getOrdersByDateAndStatus(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                @RequestParam("status") String status) {
        List<Order> orders = orderService.getOrdersByDateAndStatus(date, status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/total-orders")
    public ResponseEntity<Long> getTotalOrders() {
        long totalOrders = orderService.getTotalOrders();
        return ResponseEntity.ok(totalOrders);
    }

    @GetMapping("/total-sale-price")
    public ResponseEntity<BigDecimal> getTotalSalePriceByStatus(@RequestParam("status") String status) {
        BigDecimal totalSalePrice = orderService.getTotalSalePriceByStatus(status);
        return ResponseEntity.ok(totalSalePrice);
    }

    @GetMapping("/with-car-details")
    public ResponseEntity<List<Order>> getOrdersWithCarDetails() {
        List<Order> orders = orderService.getOrdersWithCarDetails();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/with-car-details-by-brand")
    public ResponseEntity<List<Order>> getOrdersWithCarDetailsByBrand(@RequestParam("brand") String brand) {
        List<Order> orders = orderService.getOrdersWithCarDetailsByBrand(brand);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/with-car-details-by-model")
    public ResponseEntity<List<Order>> getOrdersWithCarDetailsByModelContaining(@RequestParam("keyword") String keyword) {
        List<Order> orders = orderService.getOrdersWithCarDetailsByModelContaining(keyword);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/with-sales")
    public ResponseEntity<List<Order>> getOrdersWithSales() {
        List<Order> orders = orderService.getOrdersWithSales();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.saveOrder(orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO) {
        Order order = orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
