package com.example.hotelfullstack.services;

import com.example.hotelfullstack.dtos.OrderDTO;
import com.example.hotelfullstack.dtos.SaleDTO;
import com.example.hotelfullstack.exceptions.ResourceNotFoundException;
import com.example.hotelfullstack.models.*;
import com.example.hotelfullstack.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public Map<String, BigDecimal> getTotalSalePrice() {
        return saleRepository.sumSalePrice();
    }

    public Long countDistinctOrders() {
        return saleRepository.countDistinctOrders();
    }

    public List<Sale> getSalesWithTotalPriceGreaterThan(BigDecimal totalPrice) {
        return saleRepository.findBySalePriceGreaterThanOrderBySalePriceDesc(totalPrice);
    }

    public List<Object[]> getSalesWithTotalSalePrice() {
        return saleRepository.findSalesWithTotalSalePrice();
    }

    public List<Object[]> getSalesByTotalSalePriceGreaterThan(BigDecimal totalPrice) {
        return saleRepository.findSalesWithTotalSalePriceByTotalSalePriceGreaterThan(totalPrice);
    }

    public List<Sale> getSalesWithTotalSalePriceGreaterThanAverage() {
        return saleRepository.findSalesWithTotalSalePriceGreaterThanAverage();
    }

    public List<Sale> getSalesWithEmployeeAndCar() {
        return saleRepository.findSalesWithEmployeeAndCar();
    }

    public Sale saveSale(SaleDTO saleDTO) {
        Order order = orderRepository.findById(saleDTO.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("Order not found id : " + saleDTO.getOrderId())
        );

        Sale sale = modelMapper.map(saleDTO, Sale.class);
        sale.setOrder(order);

        return saleRepository.save(sale);
    }
    public Sale updateSale(Long id, SaleDTO saleDTO) {
        Sale sale = saleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sale not found id : " + id)
        );
        Order order = orderRepository.findById(saleDTO.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("Order not found id : " + saleDTO.getOrderId())
        );

        sale.setDateSale(saleDTO.getDateSale());
        sale.setOrder(order);
        sale.setSalePrice(saleDTO.getSalePrice());

        return saleRepository.save(sale);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
