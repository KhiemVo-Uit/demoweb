package com.example.BANCA.Controller;

import com.example.BANCA.Entyti.Product;
import com.example.BANCA.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository fishRepo;

//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("product", fishRepo.findAll());
//        return "index";
//    }

    @GetMapping("/")
    public String listProducts(@RequestParam(defaultValue = "0") int page,
                               Model model) {
        int pageSize = 9; // số sản phẩm mỗi trang
        Page<Product> productPage = fishRepo.findAll(PageRequest.of(page, pageSize));

        model.addAttribute("product", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "index"; // tên file HTML của bạn
    }

}
