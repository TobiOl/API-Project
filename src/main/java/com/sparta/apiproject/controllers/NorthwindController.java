package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.CustomersEntity;
import com.sparta.apiproject.entities.ProductsEntity;
import com.sparta.apiproject.entities.SuppliersEntity;
import com.sparta.apiproject.repositories.CustomerEntityRespository;
import com.sparta.apiproject.repositories.ProductsRepository;
import com.sparta.apiproject.repositories.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// TODO: Break this poop down into other controller classes
@RestController
public class NorthwindController {

    private final CustomerEntityRespository customerEntityRespository;
    private final ProductsRepository productsRepository;
    private final SuppliersRepository suppliersRepository;

    @Autowired
    public NorthwindController(CustomerEntityRespository customerEntityRespository,
                               ProductsRepository productsRepository
    ,SuppliersRepository suppliersRepository){
        this.customerEntityRespository = customerEntityRespository;
        this.productsRepository = productsRepository;
        this.suppliersRepository = suppliersRepository;
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
    // TODO: Add finding product by stock greater than x (DONE)
    // TODO: Add finding product by unit price greater than x (DONE)
    // TODO: Add finding product by discontinued
    @GetMapping("/products")
    public List<ProductsEntity> getAllProducts(){
        return productsRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Optional<ProductsEntity> getProductsById(@PathVariable Integer id){
        return productsRepository.findById(id);
    }

    @GetMapping(value = "/products", params = {"lowerBoundStock"})
    @ResponseBody
    public List<ProductsEntity> getProductsInStockWithinBound(@RequestParam float lowerBoundStock){
        List<ProductsEntity> foundProducts = new ArrayList<>();
        for (ProductsEntity p: productsRepository.findAll()){
            if (p.getUnitsInStock() > lowerBoundStock ){
                foundProducts.add(p);
            }
        }
        if (foundProducts.size() == 0){
            System.out.println("Empty1");
        }
        return foundProducts;
    }

    @GetMapping(value = "/products", params = {"lowerBoundPrice"})
    @ResponseBody
    public List<ProductsEntity> getProductsPriceWithinBound(@RequestParam float lowerBoundPrice){
        List<ProductsEntity> foundProducts = new ArrayList<>();
        for (ProductsEntity p: productsRepository.findAll()){
            //this code makes puppies cry, find better way
            int result = p.getUnitPrice().compareTo(BigDecimal.valueOf(lowerBoundPrice));
            switch (result){
                case 1:
                    foundProducts.add(p);
                    break;
                default:
                    break;
            }
        }
        if (foundProducts.size() == 0){
            System.out.println("Empty2");
        }
        return foundProducts;
    }
    // TODO: Add finding supplier by ID and phone, grouping by country, city and region
    @GetMapping("/suppliers")
    public List<SuppliersEntity> getAllSuppliers(){
        return suppliersRepository.findAll();
    }

    // TODO: Add rest of entitys and repos


}
