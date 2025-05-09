package com.example.BANCA.Sevice;

import com.example.BANCA.Entyti.Orders;
import com.example.BANCA.Repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSerVice {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void saveOrder(Orders order) {
        entityManager.persist(order);
    }

}
