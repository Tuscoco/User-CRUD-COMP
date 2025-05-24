package br.com.lucasmarinho.repository;

import java.util.UUID;
import br.com.lucasmarinho.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 
    public User findById(int id);
    
}
