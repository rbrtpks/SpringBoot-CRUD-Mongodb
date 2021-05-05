package com.springboot.crud.services;

import com.springboot.crud.model.Product;
import com.springboot.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    //Método de paginação e filtro
    public Object[] getProducts(Integer start, Integer end, Optional<String> name) {
        Object[] products = this.repository.findAllByOrderByCreatedAtDesc().toArray();

        if(!name.isPresent()) {
            return Arrays.copyOfRange(products, start, end);
        } else {
            ArrayList<Object> filterList = new ArrayList<>();
            for(Object item:products){
                Product product = Product.class.cast(item);
                if(product.getNameProduct().contains(name.get())) filterList.add(item);
            }
            return Arrays.copyOfRange(filterList.toArray(), start, end);
        }
    }

    public Boolean uploadProduct(Product product){
        this.repository.save(product);
        return true;
    }

    public Boolean deleteProduct(String nameProduct){
        Optional<Product> product = this.repository.findByNameProduct(nameProduct);
        if(product.isPresent()){
            this.repository.delete(product.get());
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateProduct(Product product){
        Optional<Product> otherProduct = this.repository.findByNameProduct(product.getNameProduct());
        if(otherProduct.isPresent()){
            Product newProduct = otherProduct.get();

            newProduct.setNameProduct(product.getNameProduct());
            newProduct.setRealValue((product.getRealValue()));
            newProduct.setDolarValue((product.getDolarValue()));
            newProduct.setAmount((product.getAmount()));

            this.repository.save(newProduct);
            return true;
        } else {
            return false;
        }
    }
}
