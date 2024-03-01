package com.example.Beststore3.repositories;

import com.example.Beststore3.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Là nơi để lấy data về (chứa các hàm lấy data về) (data chủ yếu nằm ở database)
// Long là kiểu của trường khoá chính
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
