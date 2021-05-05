package com.springboot.crud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor //Lombok
@Document("products")
public class Product {
    @Id
    private String id;
    private String nameProduct;
    private Double realValue;
    private Double dolarValue;
    private Integer amount;
    private Date createdAt;
}
