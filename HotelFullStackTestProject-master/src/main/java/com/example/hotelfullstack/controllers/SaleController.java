package com.example.hotelfullstack.controllers;

import com.example.hotelfullstack.dtos.OrderDTO;
import com.example.hotelfullstack.dtos.SaleDTO;
import com.example.hotelfullstack.mapper.OrderMapper;
import com.example.hotelfullstack.mapper.SaleMapper;
import com.example.hotelfullstack.models.Order;
import com.example.hotelfullstack.models.Sale;
import com.example.hotelfullstack.services.OrderService;
import com.example.hotelfullstack.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleMapper saleMapper;
    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> sales = saleMapper.toListEntity(saleService.getAllSale());
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getOrderById(@PathVariable("id") Long id) {
        Sale sale = saleService.getSaleById(id);
        if (sale != null) {
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total-sale-price")
    public ResponseEntity<Map<String, BigDecimal>> getTotalSalePrice() {
        Map<String, BigDecimal> totalSalePrice = saleService.getTotalSalePrice();
        return ResponseEntity.ok(totalSalePrice);
    }

    @GetMapping("/distinct-orders-count")
    public ResponseEntity<Long> countDistinctOrders() {
        Long distinctOrdersCount = saleService.countDistinctOrders();
        return ResponseEntity.ok(distinctOrdersCount);
    }

    @GetMapping("/with-total-sale-price")
    public ResponseEntity<List<Object[]>> getSalesWithTotalSalePrice() {
        List<Object[]> sales = saleService.getSalesWithTotalSalePrice();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/total-sale-price-greater-than")
    public ResponseEntity<List<Object[]>> getSalesByTotalSalePriceGreaterThan(@RequestParam("totalPrice") BigDecimal totalPrice) {
        List<Object[]> sales = saleService.getSalesByTotalSalePriceGreaterThan(totalPrice);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/total-price-greater-than")
    public ResponseEntity<List<Sale>> getSalesWithTotalPriceGreaterThan(@RequestParam("totalPrice") BigDecimal totalPrice) {
        List<Sale> sales = saleService.getSalesWithTotalPriceGreaterThan(totalPrice);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/total-sale-price-greater-than-average")
    public ResponseEntity<List<Sale>> getSalesWithTotalSalePriceGreaterThanAverage() {
        List<Sale> sales = saleService.getSalesWithTotalSalePriceGreaterThanAverage();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/with-employee-and-car")
    public ResponseEntity<List<Sale>> getSalesWithEmployeeAndCar() {
        List<Sale> sales = saleService.getSalesWithEmployeeAndCar();
        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<Sale> addSale(@RequestBody SaleDTO saleDTO) {
        Sale sale = saleService.saveSale(saleDTO);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable("id") Long id, @RequestBody SaleDTO saleDTO) {
        Sale sale = saleService.updateSale(id, saleDTO);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
