package com.example.BANCA.Entyti;

import com.example.BANCA.Entyti.Orders;
import com.example.BANCA.Entyti.Product;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")  // Khóa ngoại trong bảng OrderItem trỏ tới bảng Orders
    private Orders order;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    // getters/setters

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}