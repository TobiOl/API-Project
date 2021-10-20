package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.CustomersEntity;
import com.sparta.apiproject.entities.ProductsEntity;
import com.sparta.apiproject.repositories.CustomerEntityRespository;
import com.sparta.apiproject.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NorthwindController {

    private final CustomerEntityRespository customerEntityRespository;
    private final ProductsRepository productsRepository;

    @Autowired
    public NorthwindController(CustomerEntityRespository customerEntityRespository, ProductsRepository productsRepository){
        this.customerEntityRespository = customerEntityRespository;
        this.productsRepository = productsRepository;
    }

    @GetMapping("/customers")
    @ResponseBody
    public List<CustomersEntity> getAllCustomers(@RequestParam(required = false) String name){
        if (name == null){
            return customerEntityRespository.findAll();
        }
       List<CustomersEntity> foundCustomers = new ArrayList<>();
        for (CustomersEntity c:
             customerEntityRespository.findAll()) {
            if (c.getContactName().contains(name)){
                foundCustomers.add(c);
            }
        }
        return foundCustomers;
    }

    @GetMapping("/products")
    public List<ProductsEntity> getAllProducts(){
        return productsRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<ProductsEntity> getProductsById(@PathVariable Integer id){
        return productsRepository.findById(id);
    }
}
