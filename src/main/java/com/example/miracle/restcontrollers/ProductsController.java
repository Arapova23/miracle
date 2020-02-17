package com.example.miracle.restcontrollers;

import com.example.miracle.Statics;
import com.example.miracle.data.Response;
import com.example.miracle.entities.Order;
import com.example.miracle.entities.User;
import com.example.miracle.entities.product;
import com.example.miracle.repositories.OrderRepo;
import com.example.miracle.repositories.ProductRrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    ProductRrepository productsRepositatory;

    @Autowired
    private OrderRepo orderRepo;

    @RequestMapping("/products")
    public Iterable<product> getProducts() {
        return productsRepositatory.findAll();

    }

    @RequestMapping(value = "/add-to-cart/{prod_id}",method = RequestMethod.POST)

    public Response addToCart(
            @PathVariable("prod_id") int productId,
            @RequestBody User user
    ) {

        Response response = new Response();

        Optional<product> product = productsRepositatory.findById(productId);

        if (product.isPresent()) {
            Order order=new Order();
            order.userId=user.id;
            order.productId=productId;
            orderRepo.save(order);


            response.status = Statics.OK;
            response.data = product.get();
        } else {
            response.status = Statics.NOT_FOUND;
            response.message = "Product not found";
        }

        return response;

    }

}
