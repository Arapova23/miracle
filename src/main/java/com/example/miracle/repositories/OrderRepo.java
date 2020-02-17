package com.example.miracle.repositories;

import com.example.miracle.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order,Integer> {

    @Query("SELECT o FROM Order o WHERE o.userId = ?1")
    List<Order> findByUserId(long userId);
}
