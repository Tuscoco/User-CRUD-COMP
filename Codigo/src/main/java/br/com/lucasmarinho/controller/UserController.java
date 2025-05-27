package br.com.lucasmarinho.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> create(@RequestBody User user){

        boolean success = service.create(user);

        if(success){

            return ResponseEntity.ok().body("Usuário criado com sucesso!");

        }

        return ResponseEntity.badRequest().body("Erro ao criar usuário!\nUsername existente!");

    }

    @GetMapping("/{id}")
    public User read(@PathVariable("id") int id){

        return service.read(id);

    }

    @GetMapping("/username/{user}")
    public User read(@PathVariable("user") String username){

        return service.read(username);

    }

    @GetMapping("/all")
    public List<User> readAll(){

        return service.readAll();

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody User user){

        boolean success = service.update(id, user);

        if(success){

            return ResponseEntity.ok().body("Usuário atualizado com sucesso!");

        }

        return ResponseEntity.badRequest().body("Erro ao atualizar usuário!\nUsuário não encontrado!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){

        boolean success = service.delete(id);

        if(success){

            return ResponseEntity.ok().body("Usuário deletado com sucesso!");

        }

        return ResponseEntity.badRequest().body("Erro ao deletar usuário!\nUsuário não encontrado!");

    }

}
