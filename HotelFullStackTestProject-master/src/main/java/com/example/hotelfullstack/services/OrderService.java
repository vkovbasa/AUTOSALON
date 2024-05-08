package com.example.hotelfullstack.services;

import com.example.hotelfullstack.dtos.CarDTO;
import com.example.hotelfullstack.dtos.OrderDTO;
import com.example.hotelfullstack.exceptions.ResourceNotFoundException;
import com.example.hotelfullstack.models.*;
import com.example.hotelfullstack.repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByDateAndStatus(LocalDate date, String status) {
        return orderRepository.findByDateOrderAndOrderStatus(date, status);
    }

    public long getTotalOrders() {
        return orderRepository.count();
    }

    public BigDecimal getTotalSalePriceByStatus(String status) {
        return orderRepository.sumSalePriceByOrderStatus(status);
    }

    public List<Order> getOrdersWithCarDetails() {
        return orderRepository.findOrdersWithCarDetails();
    }

    public List<Order> getOrdersWithCarDetailsByBrand(String brand) {
        return orderRepository.findOrdersWithCarDetailsByCar_Brand(brand);
    }

    public List<Order> getOrdersWithCarDetailsByModelContaining(String keyword) {
        return orderRepository.findOrdersWithCarDetailsByCar_ModelContaining(keyword);
    }

    public List<Order> getOrdersWithSales() {
        return orderRepository.findOrdersWithSales();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(OrderDTO orderDTO) {
        Client client = clientRepository.findById(orderDTO.getClientId()).orElseThrow(
                () -> new ResourceNotFoundException("Client not found id : " + orderDTO.getClientId())
        );
        Car car = carRepository.findById(orderDTO.getCarId()).orElseThrow(
                () -> new ResourceNotFoundException("Car not found id : " + orderDTO.getCarId())
        );
        Employee employee = employeeRepository.findById(orderDTO.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException("Car not found id : " + orderDTO.getEmployeeId())
        );

        Order order = modelMapper.map(orderDTO, Order.class);
        order.setClient(client);
        order.setCar(car);
        order.setEmployee(employee);

        return orderRepository.save(order);
    }
    public Order updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found id : " + id)
        );
        Client client = clientRepository.findById(orderDTO.getClientId()).orElseThrow(
                () -> new ResourceNotFoundException("Client not found id : " + orderDTO.getClientId())
        );
        Car car = carRepository.findById(orderDTO.getCarId()).orElseThrow(
                () -> new ResourceNotFoundException("Car not found id : " + orderDTO.getCarId())
        );
        Employee employee = employeeRepository.findById(orderDTO.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException("Car not found id : " + orderDTO.getEmployeeId())
        );

        order.setDateOrder(orderDTO.getDateOrder());
        order.setOrderStatus(orderDTO.getOrderStatus());

        order.setClient(client);
        order.setCar(car);
        order.setEmployee(employee);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
