package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.Product;
import com.product.model.Category;
public interface ProductRepository  extends JpaRepository<Product, Long>{
    List<Product> findByCategory(Category category);

    @Query(value = "SELECT * FROM product p where p.price < :maxPrice", nativeQuery = true)
    List<Product> findProductsBelowMaxPrice(@Param("maxPrice") double maxPrice);
    @Query(value = "SELECT * FROM Product p JOIN p.category c WHERE c.nome = :nameCategory", nativeQuery = true)
    List<Product> findProductsByCategoryName(@Param("nameCategory") String nameCategory);
}
