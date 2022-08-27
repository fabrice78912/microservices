package com.fabricedev.orderserv.repository;

import com.fabricedev.orderserv.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order , Long> {
}
