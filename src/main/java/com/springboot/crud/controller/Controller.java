package com.springboot.crud.controller;

import com.springboot.crud.model.Product;
import com.springboot.crud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private ProductService service;

    @GetMapping("/get") //Método de paginação e filtro
    public ResponseEntity<Object[]> getProducts(@RequestParam Integer start, @RequestParam Integer end, @RequestParam Optional<String> name) {
        return ResponseEntity.ok(this.service.getProducts(start, end, name));
    }

    @PostMapping("/upload")
    public ResponseEntity<Product> uploadProduct(@RequestBody Product product){
        product.setCreatedAt(new Date()); //Lombok
        Boolean valid = service.uploadProduct(product);
        if(valid == true){
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam String product){

        Boolean valid = service.deleteProduct(product);
        if (valid == true) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        product.setCreatedAt(new Date()); //Lombok
        Boolean valid = service.updateProduct(product);

        if(valid == true){
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
