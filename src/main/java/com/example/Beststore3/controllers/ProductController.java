package com.example.Beststore3.controllers;


import com.example.Beststore3.models.Product;
import com.example.Beststore3.models.ResponseObject;
import com.example.Beststore3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    //DI = Dependency Injection
    @Autowired // đối tuong dược tạo ra ngay khi app đuợc tạo
    private ProductRepository repository;

    @GetMapping("")
    // this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts(){
        //return List.of("iphone", "ipad", "nokia");
        //return List.of(
                //new Product(1L, "Macbook pro 16 inch", 2020, 2400.0, ""),
                //new Product(2L, "Ipad air green", 2021, 599.0, ""),
                //new Product(3L, "Ipad air blue", 2022, 756.0, "")
        //);
        return repository.findAll(); // where is data ?
    }
    // Get detail product
    @GetMapping("/{id}") //http://localhost:8080/api/v1/Products/3
    // Let's return an object with : data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id)
    {
        Optional<Product> foundProduct = repository.findById(id); //Optional = may be null
        return foundProduct.isPresent() ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "Query product successfully", foundProduct)
                    ):
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new ResponseObject("failed", "Cannot find product with id = "+id, "")
                    );
//        if (foundProduct.isPresent()) // isPresent() nghĩa là data bên trong của Optional có phải là Object ( có khác null không)
//        {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Query product successfully", foundProduct)
//            );
//        } else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Cannot find product with id = "+id, "")
//            );
//        }
    }
    // insert new Product with POST method
    // Postman : Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // 2 product must not have the same name ! (Xử lý ở ProductRepository)
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim()); // Tìm những bản ghi có name theo yêu cầu
        if(foundProducts.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "") // Thông báo name bị trùng
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert product successfully", repository.save(newProduct))
        );
    }
}
