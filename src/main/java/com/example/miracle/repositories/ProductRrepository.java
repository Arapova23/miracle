package com.example.miracle.repositories;

import com.example.miracle.entities.product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRrepository extends CrudRepository<product, Integer> {

    List<product> findByType(int type);
}
