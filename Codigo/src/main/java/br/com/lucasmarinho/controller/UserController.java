package br.com.lucasmarinho.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasmarinho.model.User;
import br.com.lucasmarinho.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/")
    public void create(@RequestBody User user){

        service.create(user);

    }

    @GetMapping("/{id}")
    public User read(@PathVariable("id") int id){

        return service.read(id);

    }

    @GetMapping("/all")
    public List<User> readAll(){

        return service.readAll();

    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody User user){

        service.update(id, user);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){

        service.delete(id);

    }

}
