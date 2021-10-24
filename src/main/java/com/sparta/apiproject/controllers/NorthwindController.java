package com.sparta.apiproject.controllers;

import com.sparta.apiproject.dtos.CustomersDTO;
import com.sparta.apiproject.dtos.OrdersDTO;
import com.sparta.apiproject.dtos.ProductsDTO;
import com.sparta.apiproject.entities.*;
import com.sparta.apiproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/customers/location")
    @ResponseBody
    public List<CustomersDTO> getCustomersByLocation(@RequestParam(required = false) String country, @RequestParam(required = false) String city, @RequestParam(required = false) String region){
        List<CustomersDTO> allCustomers = mapService.getAllCustomers();
        if (country==null&&city==null&&region==null){
            return allCustomers;
        }
        List<CustomersDTO> foundCustomers = new ArrayList<>();
        for (CustomersDTO customersDTO:
                allCustomers) {
            if (country != null){
                if (customersDTO.getCountry()==null){
                    System.out.println("skip");
                }
                else if (customersDTO.getCountry().contains(country)){
                    foundCustomers.add(customersDTO);
                }
            }
            if (city != null){
                if (customersDTO.getCity()==null){
                    System.out.println("skip");
                }
                else if (customersDTO.getCity().contains(city)){
                    foundCustomers.add(customersDTO);
                }
            }
            if (region != null){
                if (customersDTO.getRegion()==null){
                    System.out.println("skip");
                }
                else if (customersDTO.getRegion().contains(region)){
                    foundCustomers.add(customersDTO);
                }
            }

        }
        if (foundCustomers.size() == 0){
            throw new ResourceNotFoundException();
        }
        return foundCustomers;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<ProductsDTO> getAllProducts(){
        return mapService.getAllProducts();
    }

    @GetMapping(value = "/products", params = {"id"})
    public List<ProductsDTO> getProductsById(@RequestParam Integer id){
        List<ProductsDTO> foundProducts = new ArrayList<>();
        for (ProductsDTO p: mapService.getAllProducts()){
            if (p.getProductID()==id){
                foundProducts.add(p);
            }
        }
        if (foundProducts.size()==0){
            throw new ResourceNotFoundException();
        }
        return foundProducts;
    }

    @GetMapping(value = "/products", params = {"lowerBoundStock"})
    @ResponseBody
    public List<ProductsDTO> getProductsInStockWithinBound(@RequestParam float lowerBoundStock){
        List<ProductsDTO> foundProducts = new ArrayList<>();
        for (ProductsDTO p: mapService.getAllProducts()){
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
    public List<ProductsDTO> getProductsPriceWithinBound(@RequestParam float lowerBoundPrice){
        List<ProductsDTO> foundProducts = new ArrayList<>();
        for (ProductsDTO p: mapService.getAllProducts()){
            int result = p.getUnitPrice().compareTo(BigDecimal.valueOf(lowerBoundPrice));
            if (result >= 1) {
                foundProducts.add(p);
            }
        }
        return foundProducts;
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrdersDTO> getAllOrders(){
        return mapService.getAllOrders();
    }

    @GetMapping(value = "/orders", params = {"id"})
    public List<OrdersDTO> getOrdersById(@RequestParam Integer id){
        List<OrdersDTO> foundOrders = new ArrayList<>();
        for (OrdersDTO ordersDTO: mapService.getAllOrders()){
            if (ordersDTO.getOrderId()==id){
                foundOrders.add(ordersDTO);
            }
        }
        if (foundOrders.size()==0){
            throw new ResourceNotFoundException();
        }
        return foundOrders;
    }
@GetMapping(value = "/orders", params = {"city"})
@ResponseBody
    public List<OrdersDTO> getOrdersByCity(@RequestParam String city){
    List<OrdersDTO> foundOrders = new ArrayList<>();
    for (OrdersDTO ordersDTO: mapService.getAllOrders()){
        if (city != null){
            if (ordersDTO.getShipCity()==null){
                System.out.println("skip");
            }
            else if (ordersDTO.getShipCity().contains(city)){
                foundOrders.add(ordersDTO);
            }
        }
    }
    if (foundOrders.size() == 0){
        throw new ResourceNotFoundException();
    }
        return foundOrders;
    }

    @GetMapping(value = "/orders", params = {"country"})
    @ResponseBody
    public List<OrdersDTO> getOrdersByCountry(@RequestParam String country){
        List<OrdersDTO> foundOrders = new ArrayList<>();
        for (OrdersDTO ordersDTO: mapService.getAllOrders()){
            if (country != null){
                if (ordersDTO.getShipCountry()==null){
                    System.out.println("skip");
                }
                else if (ordersDTO.getShipCountry().contains(country)){
                    foundOrders.add(ordersDTO);
                }
            }
        }
        if (foundOrders.size() == 0){
            throw new ResourceNotFoundException();
        }
        return foundOrders;
    }

    @GetMapping(value = "/orders", params = {"region"})
    @ResponseBody
    public List<OrdersDTO> getOrdersByRegion(@RequestParam String region){
        List<OrdersDTO> foundOrders = new ArrayList<>();
        for (OrdersDTO ordersDTO: mapService.getAllOrders()){
            if (region != null){
                if (ordersDTO.getShipRegion()==null){
                    System.out.println("skip");
                }
                else if (ordersDTO.getShipRegion().contains(region)){
                    foundOrders.add(ordersDTO);
                }
            }
        }
        if (foundOrders.size() == 0){
            throw new ResourceNotFoundException();
        }
        return foundOrders;
    }

}
