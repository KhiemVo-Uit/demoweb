package com.example.BANCA.Sevice;

import com.example.BANCA.Entyti.CartItem;
import com.example.BANCA.Entyti.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope // Lưu giỏ hàng theo từng phiên người dùng
public class CartService {

    private Map<Long, CartItem> cart = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        cart.compute(product.getId(), (id, item) -> {
            if (item == null) {
                return new CartItem(product, quantity);
            } else {
                item.setQuantity(item.getQuantity() + quantity);
                return item;
            }
        });
    }

    public void removeFromCart(Long productId) {
        cart.remove(productId);
    }

    public void clearCart() {
        cart.clear();
    }

    public Collection<CartItem> getItems() {
        return cart.values();
    }

    public int getTotalQuantity() {
        return cart.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public double getTotalPrice() {
        return cart.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }
}
