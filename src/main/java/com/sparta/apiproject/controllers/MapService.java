package com.sparta.apiproject.controllers;

import com.sparta.apiproject.dtos.CustomersDTO;
import com.sparta.apiproject.dtos.ProductsDTO;
import com.sparta.apiproject.entities.CategoryEntity;
import com.sparta.apiproject.entities.CustomersEntity;
import com.sparta.apiproject.entities.ProductsEntity;
import com.sparta.apiproject.entities.SupplierEntity;
import com.sparta.apiproject.repositories.CustomerEntityRespository;
import com.sparta.apiproject.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {
    private final CustomerEntityRespository customerEntityRespository;
    private final ProductsRepository productsRepository;
    @Autowired
    public MapService(
            CustomerEntityRespository customerEntityRespository
            ,ProductsRepository productsRepository){
        this.customerEntityRespository = customerEntityRespository;
        this.productsRepository = productsRepository;
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
    private ProductsDTO convertToProductsDTO(ProductsEntity productsEntity){
        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setProductID(productsDTO.getProductID());
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
        customersDTO.setCountry(customersEntity.getCountry());
        customersDTO.setPhone(customersEntity.getPhone());
        customersDTO.setPostCode(customersEntity.getPostalCode());
        customersDTO.setRegion(customersEntity.getRegion());
        return customersDTO;
    }
}
