package br.com.lucasmarinho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lucasmarinho.model.User;
import br.com.lucasmarinho.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository repository;

    public UserService(UserRepository repository){

        this.repository = repository;
        
    }

    public boolean create(User user){

        User verificarExistencia = repository.findByUser(user.getUser());

        if(verificarExistencia == null){

            repository.save(user);

            return true;

        }

        return false;

    }

    public User read(int id){

        return repository.findById(id);

    }

    public List<User> readAll(){

        return repository.findAll();

    }

    public boolean update(int id, User userAtualizado){

        User user = repository.findById(id);

        if(user == null){

            return false;

        }

        user.setName(userAtualizado.getName());
        user.setUser(userAtualizado.getUser());
        user.setPassword(userAtualizado.getPassword());

        repository.save(user);

        return true;

    }

    public boolean delete(int id){

        User user = repository.findById(id);

        if(user != null){

            return false;

        }

        repository.deleteById(id);

        return true;

    }

}
