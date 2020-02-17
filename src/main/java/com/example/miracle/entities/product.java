package com.example.miracle.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class product {


    @Id
    public int id;
    public String name;
    public double price;
    @Column(name = "count", columnDefinition = "int DEFAULT 0")
    public int count;
    public String description;
    public String image;
    public int is_new;
    public int type;

}



