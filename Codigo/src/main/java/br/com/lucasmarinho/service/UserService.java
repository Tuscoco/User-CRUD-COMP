package br.com.lucasmarinho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
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

        if(verificarExistencia != null){

            return false;

        }

        String senhaCriptografada = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(senhaCriptografada);

        repository.save(user);

        return true;

    }

    public User read(int id){

        return repository.findById(id);

    }

    public User read(String username){

        return repository.findByUser(username);

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

        String senhaCriptografada = BCrypt.withDefaults().hashToString(12, userAtualizado.getPassword().toCharArray());

        user.setPassword(senhaCriptografada);

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
