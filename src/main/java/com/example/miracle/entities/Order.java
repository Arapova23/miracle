package com.example.miracle.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(name = "product_id")
    public int productId;
    @Column(name = "user_id")
    public long userId;

    public Date date;


}





