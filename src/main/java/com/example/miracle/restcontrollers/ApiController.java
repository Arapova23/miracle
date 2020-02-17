package com.example.miracle.restcontrollers;

import com.example.miracle.entities.User;
import com.example.miracle.entities.product;
import com.example.miracle.repositories.ProductRrepository;
import com.example.miracle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    UserRepository usersRepositatory;
    @RequestMapping("/users")
    public Iterable<User>getUsers(){
        return usersRepositatory.findAll();

    }
    @Autowired
    ProductRrepository productsRepositatory;
    @RequestMapping("/products")
    public Iterable<product>getProducts(){
        return productsRepositatory.findAll();

    }
}
