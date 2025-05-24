package br.com.lucasmarinho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasmarinho.model.User;
import br.com.lucasmarinho.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User read(int id){

        return repository.findById(id);

    }

}
