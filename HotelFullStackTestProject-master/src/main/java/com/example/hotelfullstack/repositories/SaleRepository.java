package com.example.hotelfullstack.repositories;

import com.example.hotelfullstack.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT new map(SUM(s.salePrice) as totalSalePrice) FROM Sale s")
    Map<String, BigDecimal> sumSalePrice();

    @Query("SELECT COUNT(DISTINCT s.id) FROM Sale s")
    Long countDistinctOrders();
    List<Sale> findBySalePriceGreaterThanOrderBySalePriceDesc(BigDecimal totalPrice);

    @Query("SELECT s.id, s.dateSale, SUM(s.salePrice) FROM Sale s GROUP BY s.id, s.dateSale")
    List<Object[]> findSalesWithTotalSalePrice();

    @Query("SELECT s.id, SUM(s.salePrice) " +
            "FROM Sale s " +
            "GROUP BY s.id " +
            "HAVING SUM(s.salePrice) > :totalPrice")
    List<Object[]> findSalesWithTotalSalePriceByTotalSalePriceGreaterThan(@Param("totalPrice") BigDecimal totalPrice);

    @Query("SELECT s FROM Sale s GROUP BY s.id HAVING SUM(s.salePrice) > (SELECT AVG(sale.salePrice) FROM Sale sale)")
    List<Sale> findSalesWithTotalSalePriceGreaterThanAverage();
    @Query("SELECT s FROM Sale s INNER JOIN FETCH s.order.employee e INNER JOIN FETCH s.order.car c")
    List<Sale> findSalesWithEmployeeAndCar();
}
