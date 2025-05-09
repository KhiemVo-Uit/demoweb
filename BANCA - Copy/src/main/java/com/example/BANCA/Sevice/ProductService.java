package com.example.BANCA.Sevice;

import com.example.BANCA.Entyti.Product;
import com.example.BANCA.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query); // Tìm kiếm không phân biệt chữ hoa chữ thường
    }
}
