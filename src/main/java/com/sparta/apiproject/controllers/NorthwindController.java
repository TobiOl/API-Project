package com.sparta.apiproject.controllers;

import com.sparta.apiproject.dtos.CustomersDTO;
import com.sparta.apiproject.dtos.ProductsDTO;
import com.sparta.apiproject.entities.*;
import com.sparta.apiproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// TODO: Break this poop down into other controller classes
@RestController
public class NorthwindController {

    private final ProductsRepository productsRepository;
    private final SuppliersRepository suppliersRepository;
    private final ShipperRepository shipperRepository;
    private final OrderRepository orderRepository;
    private final CategoryRespository categoryRespository;
    private final MapService mapService;

    @Autowired
    public NorthwindController(ProductsRepository productsRepository
                            ,SuppliersRepository suppliersRepository
                            ,OrderRepository orderRepository
                            ,ShipperRepository shipperRepository
                            ,CategoryRespository categoryRespository
                            ,MapService mapService){
        this.productsRepository = productsRepository;
        this.suppliersRepository = suppliersRepository;
        this.orderRepository = orderRepository;
        this.shipperRepository = shipperRepository;
        this.categoryRespository = categoryRespository;
        this.mapService = mapService;
    }

    @GetMapping("/customers")
    @ResponseBody
    public List<CustomersDTO> getAllCustomers(@RequestParam(required = false) String name){
        List<CustomersDTO> allCustomers = mapService.getAllCustomers();
        if (name==null){
            return allCustomers;
        }
        List<CustomersDTO> foundCustomers = new ArrayList<>();
        for (CustomersDTO customersDTO:
             allCustomers) {
            if (customersDTO.getContactName().contains(name)){
                foundCustomers.add(customersDTO);
            }
        }
        return foundCustomers;
    }

    // TODO: Add finding product by stock greater than x (DONE)
    // TODO: Add finding product by unit price greater than x (DONE)
    // TODO: Add finding product by discontinued
    @GetMapping("/products")
    @ResponseBody
    public List<ProductsDTO> getAllProducts(){
        return mapService.getAllProducts();
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
            int result = p.getUnitPrice().compareTo(BigDecimal.valueOf(lowerBoundPrice));
            if (result >= 1) {
                foundProducts.add(p);
            }
        }
        if (foundProducts.size() == 0){
            System.out.println("Empty2");
        }
        return foundProducts;
    }
    // TODO: Add finding supplier by ID and phone, grouping by country, city and region
    @GetMapping("/suppliers")
    public List<SupplierEntity> getAllSuppliers(){
        return suppliersRepository.findAll();
    }


    // TODO: Add rest of entitys and repos
    @GetMapping("/shippers")
    public List<ShipperEntity> getAllShippers(){
        return shipperRepository.findAll();
    }

    @GetMapping("/orders")
    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/category")
    public List<CategoryEntity> getAllCategories(){
        return categoryRespository.findAll();
    }

}
