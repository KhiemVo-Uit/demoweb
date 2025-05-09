package com.example.BANCA.Controller;

import com.example.BANCA.Entyti.CartItem;
import com.example.BANCA.Entyti.Product;
import com.example.BANCA.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private ProductRepository productRepo;

    // Khởi tạo giỏ hàng nếu chưa có
    @ModelAttribute("cart")
    public Map<Long, CartItem> cart() {
        return new HashMap<>();
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity,
                            @ModelAttribute("cart") Map<Long, CartItem> cart,
                            Model model) {

        Product product = productRepo.findById(productId).orElseThrow();

        cart.compute(productId, (id, item) -> {
            if (item == null) {
                return new CartItem(product, quantity);
            } else {
                item.setQuantity(item.getQuantity() + quantity);
                return item;
            }
        });

        // Thêm thông tin vào Model để cập nhật giỏ hàng
        model.addAttribute("cartSize", cart.size());
        model.addAttribute("totalPrice", cart.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum());

        return "fragments :: cartSummary";  // Trả về một phần tử HTML (fragments) để cập nhật giỏ hàng
    }


    // 👉 Xem giỏ hàng
    @GetMapping("/cart")
    public String showCart(@ModelAttribute("cart") Map<Long, CartItem> cart, Model model) {
        model.addAttribute("cartItems", cart.values());

        // Tính tổng tiền của giỏ hàng
        double totalPrice = cart.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("totalPrice", totalPrice);

        return "cart"; // cart.html
    }

    // 👉 Xóa một sản phẩm khỏi giỏ
    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId,
                                 @ModelAttribute("cart") Map<Long, CartItem> cart) {
        cart.remove(productId);
        return "redirect:/cart";
    }

    // 👉 Xóa toàn bộ giỏ
    @PostMapping("/cart/clear")
    public String clearCart(SessionStatus status) {
        status.setComplete(); // Xóa session
        return "redirect:/cart";
    }
}
