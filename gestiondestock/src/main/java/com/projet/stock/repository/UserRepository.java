package com.projet.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value="select u from User u  where  u.mail = :mail and u.password = :password")
	  User  findUser (String mail, String password );

}
