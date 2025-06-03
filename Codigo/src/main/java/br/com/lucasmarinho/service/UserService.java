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

    public User criarUsuario(User user){

        User verificarExistencia = repository.findByUsername(user.getUsername());

        if(verificarExistencia != null){

            return null;

        }

        String senhaCriptografada = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(senhaCriptografada);

        return repository.save(user);

    }

    public User lerUsuario(int id){

        return repository.findById(id);

    }

    public User lerUsuario(String username){

        return repository.findByUsername(username);

    }

    public List<User> lerTodosUsuarios(){

        return repository.findAll();

    }

    public boolean atualizarUsuario(int id, User userAtualizado){

        User user = repository.findById(id);

        if(user == null){

            return false;

        }

        user.setName(userAtualizado.getName());
        user.setUsername(userAtualizado.getUsername());

        String senhaCriptografada = BCrypt.withDefaults().hashToString(12, userAtualizado.getPassword().toCharArray());

        user.setPassword(senhaCriptografada);

        repository.save(user);

        return true;

    }

    public boolean deletarUsuario(int id){

        User user = repository.findById(id);

        if(user == null){

            return false;

        }

        repository.deleteById(id);

        return true;

    }

}
