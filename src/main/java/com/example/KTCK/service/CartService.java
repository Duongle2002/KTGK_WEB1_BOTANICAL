package com.example.KTCK.service;

import com.example.KTCK.model.Cart;
import com.example.KTCK.model.CartItem;
import com.example.KTCK.model.Products;
import com.example.KTCK.repository.CartItemRepository;
import com.example.KTCK.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(new Cart());
    }

    public void addToCart(Long cartId, Products product, String color, String size, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());

        // Kiểm tra xem sản phẩm cùng loại đã có trong giỏ hàng chưa
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()) && item.getColor().equals(color) && item.getSize().equals(size))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Cập nhật số lượng nếu sản phẩm đã tồn tại trong giỏ hàng
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Thêm mới nếu sản phẩm chưa có
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setColor(color);
            newItem.setSize(size);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
    }
}
