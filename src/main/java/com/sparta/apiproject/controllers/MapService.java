package com.sparta.apiproject.controllers;

import com.sparta.apiproject.dtos.CustomersDTO;
import com.sparta.apiproject.dtos.OrdersDTO;
import com.sparta.apiproject.dtos.ProductsDTO;
import com.sparta.apiproject.entities.*;
import com.sparta.apiproject.repositories.CustomerEntityRespository;
import com.sparta.apiproject.repositories.OrderRepository;
import com.sparta.apiproject.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {
    private final CustomerEntityRespository customerEntityRespository;
    private final ProductsRepository productsRepository;
    private final OrderRepository orderRepository;
    @Autowired
    public MapService(
            CustomerEntityRespository customerEntityRespository
            ,ProductsRepository productsRepository
            ,OrderRepository orderRepository){
        this.customerEntityRespository = customerEntityRespository;
        this.productsRepository = productsRepository;
        this.orderRepository = orderRepository;
    }


    public List<CustomersDTO> getAllCustomers(){
        return ((List<CustomersEntity>) customerEntityRespository
                .findAll())
                .stream()
                .map(this::convertToCustomersDTO)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> getAllProducts(){
        return ((List<ProductsEntity>) productsRepository
                .findAll())
                .stream()
                .map(this::convertToProductsDTO)
                .collect(Collectors.toList());
    }

    public List<OrdersDTO> getAllOrders(){
        return ((List<OrderEntity>) orderRepository
                .findAll())
                .stream()
                .map(this::convertToOrdersDTO)
                .collect(Collectors.toList());
    }
    private OrdersDTO convertToOrdersDTO(OrderEntity orderEntity){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrderDate(orderEntity.getOrderDate());
        ordersDTO.setOrderId(orderEntity.getId());
        CustomersEntity customersEntity = orderEntity.getCustomerID();
        ordersDTO.setCustomerName(customersEntity.getCompanyName());
        ordersDTO.setRequiredDate(orderEntity.getRequiredDate());
        ordersDTO.setShipAddress(orderEntity.getShipAddress());
        ordersDTO.setShipCity(orderEntity.getShipCity());
        ordersDTO.setShipCountry(orderEntity.getShipCountry());
        ordersDTO.setShippedDate(orderEntity.getShippedDate());
        ordersDTO.setShipRegion(orderEntity.getShipRegion());
        ordersDTO.setShipPostalCode(orderEntity.getShipPostalCode());
        return ordersDTO;
    }

    private ProductsDTO convertToProductsDTO(ProductsEntity productsEntity){
        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setProductID(productsEntity.getId());
        productsDTO.setProductName(productsEntity.getProductName());
        productsDTO.setDiscontinued(productsEntity.getDiscontinued());
        productsDTO.setReorderLevel(productsEntity.getReorderLevel());
        productsDTO.setUnitPrice(productsEntity.getUnitPrice());
        SupplierEntity supplierEntity = productsEntity.getSupplierID();
        productsDTO.setSupplierName(supplierEntity.getCompanyName());
        productsDTO.setQuantityPerUnit(productsEntity.getQuantityPerUnit());
        productsDTO.setUnitsInStock(productsEntity.getUnitsInStock());

        return productsDTO;
    }

    private CustomersDTO convertToCustomersDTO(CustomersEntity customersEntity){
        CustomersDTO customersDTO = new CustomersDTO();
        customersDTO.setCustomerID(customersEntity.getId());
        customersDTO.setCompanyName(customersEntity.getCompanyName());
        customersDTO.setAddress(customersEntity.getAddress());
        customersDTO.setCity(customersEntity.getCity());
        customersDTO.setContactName(customersEntity.getContactName());
        customersDTO.setCountry(customersEntity.getCountry());
        customersDTO.setContactTitle(customersEntity.getContactTitle());
        customersDTO.setPhone(customersEntity.getPhone());
        customersDTO.setPostCode(customersEntity.getPostalCode());
        customersDTO.setRegion(customersEntity.getRegion());
        return customersDTO;
    }
}
