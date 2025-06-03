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
    public ResponseEntity<User> criarUsuario(@RequestBody User user){

        User criado = service.criarUsuario(user);

        if(criado != null){

            return ResponseEntity.ok().body(criado);

        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> lerUsuario(@PathVariable("id") int id){

        User user = service.lerUsuario(id);

        if(user != null){

            return ResponseEntity.ok().body(user);

        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/username/{user}")
    public ResponseEntity<User> lerUsuario(@PathVariable("user") String username){

        User user = service.lerUsuario(username);

        if(user != null){

            return ResponseEntity.ok().body(user);

        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/all")
    public List<User> lerTodosUsuarios(){

        return service.lerTodosUsuarios();

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable("id") int id, @RequestBody User user){

        boolean success = service.atualizarUsuario(id, user);

        if(success){

            return ResponseEntity.ok().body("Usuário atualizado com sucesso!");

        }

        return ResponseEntity.badRequest().body("Erro ao atualizar usuário!\nUsuário não encontrado!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") int id){

        boolean success = service.deletarUsuario(id);

        if(success){

            return ResponseEntity.ok().body("Usuário deletado com sucesso!");

        }

        return ResponseEntity.badRequest().body("Erro ao deletar usuário!\nUsuário não encontrado!");

    }

}
