package br.com.lucasmarinho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasmarinho.model.User;
import br.com.lucasmarinho.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/")
    public User read(@RequestParam int id){

        User user = service.read(id);

        System.out.println(user);

        return user;

    }

    public void create(){}

}
