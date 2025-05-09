package com.example.BANCA.Controller;

import com.example.BANCA.Entyti.*;
import com.example.BANCA.Repository.OrderItemRepository;
import com.example.BANCA.Repository.OrderRepository;
import com.example.BANCA.Repository.ProductRepository;
import com.example.BANCA.Repository.UserRepository;
import com.example.BANCA.Sevice.CartService;
import com.example.BANCA.Sevice.OrderSerVice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderSerVice orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private UserRepository userRepo;

    // Trang checkout để người dùng nhập thông tin thanh toán
    @GetMapping("/checkout")
    public String checkoutPage(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "checkout"; // Trả về trang checkout
    }

    // Xử lý thông tin thanh toán và lưu đơn hàng vào CSDL
    @PostMapping("/checkout")
    public String checkout(@RequestParam String guestName,
                           @RequestParam String guestEmail,
                           @RequestParam String guestPhone,
                           @RequestParam String guestAddress) {

        // Tạo đơn hàng mới
        Orders order = new Orders();
        order.setGuestName(guestName);
        order.setGuestEmail(guestEmail);
        order.setGuestPhone(guestPhone);
        order.setGuestAddress(guestAddress);
        order.setOrderDate(LocalDateTime.now());


        // Lưu đơn hàng vào CSDL
        orderRepo.saveOrder(order);

        // Lưu các sản phẩm trong giỏ hàng vào bảng OrderItem
        for (CartItem cartItem : cartService.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemRepo.save(orderItem);
        }

        // Xóa giỏ hàng sau khi đã đặt hàng
        cartService.clearCart();

        // Chuyển hướng tới trang cảm ơn
        return "redirect:/order/thankyou"; // Đến trang cảm ơn
    }

    // Trang cảm ơn sau khi đặt hàng thành công
    @GetMapping("/thankyou")
    public String thankYouPage() {
        return "thankyou"; // Trả về trang thankyou
    }
}
