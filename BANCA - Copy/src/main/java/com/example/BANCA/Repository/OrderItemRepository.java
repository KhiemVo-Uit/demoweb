package com.example.BANCA.Repository;

import com.example.BANCA.Entyti.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
