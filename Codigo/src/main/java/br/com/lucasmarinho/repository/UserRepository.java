package br.com.lucasmarinho.repository;

import br.com.lucasmarinho.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 
    public User findById(int id);

    public User findByUser(String user);
    
}
