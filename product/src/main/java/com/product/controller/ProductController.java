package com.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import com.product.model.Product;
import com.product.model.Category;
import com.product.repository.ProductRepository;
import com.product.repository.CategoryRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> geProductsByCategory(@PathVariable Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category != null){
            return productRepository.findByCategory(category);
        }
        return new ArrayList<>();
    }

    @GetMapping("/below-peice/{maxPrice}")
    public List<Product> geProductsBelowMaxPrice(@PathVariable double maxPrice){
        return productRepository.findProductsBelowMaxPrice(maxPrice);
    }
}
