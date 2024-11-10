package com.example.KTCK.controller;

import com.example.KTCK.model.Products;
import com.example.KTCK.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addProducts(@RequestBody List<ProductRequest> productRequests) {
        // Xử lý các sản phẩm trong mảng
        for (ProductRequest productRequest : productRequests) {
            // Chuyển đổi ProductRequest thành thực thể Product và lưu vào cơ sở dữ liệu
            Products product = new Products();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setProductCode(productRequest.getProductCode());
            product.setQuantity(productRequest.getQuantity());
            product.setShippingTax(productRequest.getShippingTax());
            product.setRating(productRequest.getRating());
            product.setReviewCount(productRequest.getReviewCount());
            product.setBrand(productRequest.getBrand());
            product.setSizes(productRequest.getSizes());
            product.setColors(productRequest.getColors());
            product.setCategories(productRequest.getCategories());
            product.setTags(productRequest.getTags());
            product.setImageUrl(productRequest.getImageUrl());

            productService.saveProduct(product);
        }

        return new ResponseEntity<>("Sản phẩm đã được thêm thành công", HttpStatus.CREATED);
    }
}
