package com.phile.demo2.controller;

import com.phile.demo2.model.Product;
import com.phile.demo2.model.ResponseObject;
import com.phile.demo2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    public List<Product> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);

        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Query product successfully", foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Not Found", "Can not find product with id = " + id, "")
            );
        }
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseObject> insert(@RequestBody Product newProduct) {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("OK", "Insert Product successfully", repository.save(newProduct))
        );
    }
}
