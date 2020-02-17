package com.example.miracle.restcontrollers;

import com.example.miracle.data.Response;
import com.example.miracle.entities.User;
import com.example.miracle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepositatory;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(
            @RequestBody User user)
    {
        Response response = new Response();
        User userDb = userRepositatory.getByLoginAndPass(user.login,user.password);
        if (userDb != null) {
            response.status = 1000;
            response.data = userDb;
        } else {
            response.status = 5000;
            response.message = "User Not Found";
        }
        return response;
    }


}
