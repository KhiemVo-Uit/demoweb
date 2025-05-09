package com.example.BANCA.Controller;

import com.example.BANCA.Entyti.Product;
import com.example.BANCA.Sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // Phương thức xử lý tìm kiếm sản phẩm
    @GetMapping("/search")
    public String searchProducts(@RequestParam("query") String query, Model model) {
        List<Product> filteredProducts = productService.searchProducts(query);
        model.addAttribute("product", filteredProducts); // Thêm sản phẩm đã tìm vào model
        return "product-list";  // Trả về trang hiển thị sản phẩm
    }



}
