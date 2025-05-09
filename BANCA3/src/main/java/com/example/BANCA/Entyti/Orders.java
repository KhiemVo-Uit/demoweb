package com.example.BANCA.Entyti;

import com.example.BANCA.Entyti.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

// --- Entity: Orders.java ---
@Entity
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private String guestAddress;
    private LocalDateTime orderDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")  // Khóa ngoại trong bảng Orders trỏ tới bảng User
    private UserEntity user;

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    // getters/setters
}