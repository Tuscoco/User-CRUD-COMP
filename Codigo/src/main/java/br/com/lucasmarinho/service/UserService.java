package br.com.lucasmarinho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lucasmarinho.model.User;
import br.com.lucasmarinho.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void create(User user){

        repository.save(user);

    }

    public User read(int id){

        return repository.findById(id);

    }

    public List<User> readAll(){

        return repository.findAll();

    }

    public void update(int id, User user){}

    public void delete(int id){

        repository.deleteById(id);

    }

}
