package com.example.miracle.Controllers;

import com.example.miracle.entities.Order;
import com.example.miracle.entities.User;
import com.example.miracle.entities.product;
import com.example.miracle.repositories.OrderRepo;
import com.example.miracle.repositories.ProductRrepository;
import com.example.miracle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    private UserRepository repositatory;

    @Autowired
    private ProductRrepository productRepository;

    @Autowired
    private OrderRepo orderRepo;

    @RequestMapping("/")

    public ModelAndView index(HttpSession session) {
        return getProducts(session);

    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView getProducts(HttpSession session
    ) {
        Iterable<product> products = productRepository.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("products", products);
        model.put("logedin", session.getAttribute("user_id") != null);
        model.put("isVideo", true);
        return new ModelAndView("products", model);

    }


    @RequestMapping(value = "/products/{type}", method = RequestMethod.GET)
    public ModelAndView getProductsByType(
            @PathVariable("type") String type
    ) {
        type = type.trim();
        Map<String, Object> model = new HashMap<>();
        Iterable<product> products = null;

        if (type.equals("man")) {
            products = productRepository.findByType(1);
            model.put("isVideo", false);
        } else if (type.equals("women")) {
            products = productRepository.findByType(2);
            model.put("isVideo", false);
        } else if (type.equals("children")) {
            products = productRepository.findByType(3);
            model.put("isVideo", false);
        } else {
            if (type.equals("products")) {
                products = productRepository.findByType(4);
            }
        }

        model.put("products", products);
        return new ModelAndView("products", model);

    }

    @RequestMapping(value = "/add-product", method = RequestMethod.GET)
    public ModelAndView addProduct() {
        return new ModelAndView("addProduct");
    }

    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public ModelAndView addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("image") String image,
            @RequestParam("is_new") int is_new,
            @RequestParam("type") int type
    ) {
        product product = new product();
        product.name = name;
        product.price = price;
        product.description = description;
        product.image = image;
        product.is_new = is_new;
        product.type = type;
        productRepository.save(product);
        return new ModelAndView("addProduct");

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView hello(

            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            Map<String, String> model,
            HttpSession session

    ) {
        User user = repositatory.getByLoginAndPass(login, password);
        model.put("message", "");
        if (user != null) {
            session.setAttribute("user_id", user.id);

            return getProducts(session);
        } else
            model.put("message", "Wrong login or password");
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public ModelAndView login(Map<String, Object> model) {
        model.put("message", "");
        return new ModelAndView("index", model);
    }


    @RequestMapping(value = "/add-to-card", method = RequestMethod.POST)
    public String addToCart(
            @RequestParam("p_id") int productId,
            Map<String, Object> model,
            HttpSession session

    ) {

        if (session.getAttribute("user_id") != null) {
            long userId = (long) session.getAttribute("user_id");


            product product = productRepository
                    .findById(productId).get();

            Order order = new Order();
            order.productId = productId;
            order.date = new Date();
            order.userId = userId;
            orderRepo.save(order);
            model.put("message", product.name + "added");
        } else {
            model.put("message", "Войдите на сайт");
        }
        return "ok";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public RedirectView logout(HttpSession session) {
        session.removeAttribute("user_id");
        return new RedirectView("/products");
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public ModelAndView checkIn(Map<String, Object> model,
                                HttpSession session) {
        model.put("message", "");
        return new ModelAndView("chekIn", model);


    }


}













