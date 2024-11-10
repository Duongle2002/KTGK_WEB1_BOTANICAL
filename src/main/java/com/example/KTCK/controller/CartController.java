package com.example.KTCK.controller;

import com.example.KTCK.model.Cart;
import com.example.KTCK.model.CartItem;
import com.example.KTCK.model.Products;
import com.example.KTCK.repository.CartItemRepository;
import com.example.KTCK.service.CartService;
import com.example.KTCK.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public  class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartService cartService;

    @PostMapping("/shop/cart/add")
    public String addToCart(
            @RequestParam("productId") Long productId,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("quantity") int quantity,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        // Lấy sản phẩm từ cơ sở dữ liệu
        Products product = productService.getProductById(productId).orElse(null);

        if (product != null) {
            // Kiểm tra nếu sản phẩm đã có trong giỏ hàng
            Optional<CartItem> existingCartItem = cartItemRepository.findByProductAndColorAndSize(product, color, size);

            if (existingCartItem.isPresent()) {
                // Nếu có, cập nhật số lượng sản phẩm
                CartItem cartItem = existingCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItemRepository.save(cartItem);
                redirectAttributes.addFlashAttribute("message", "Đã cập nhật số lượng sản phẩm trong giỏ hàng!");
            } else {
                // Nếu chưa có, thêm sản phẩm mới vào giỏ hàng
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setColor(color);
                cartItem.setSize(size);
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
                redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sản phẩm.");
        }

        // Quay về trang chi tiết sản phẩm với thông báo
        return "redirect:/shop/" + productId;
    }
    @GetMapping("/shop/cart")
    public String viewCart(Model model) {
        Long cartId = 1L; // Giả sử cartId của người dùng, có thể lấy từ session nếu cần
        Cart cart = cartService.getCartById(cartId);
        model.addAttribute("cart", cart);
        return "cart-page";
    }
}


