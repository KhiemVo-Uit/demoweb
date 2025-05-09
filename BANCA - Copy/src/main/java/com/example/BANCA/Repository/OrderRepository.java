package com.example.BANCA.Repository;

import com.example.BANCA.Entyti.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
