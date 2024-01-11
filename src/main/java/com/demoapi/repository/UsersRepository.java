package com.demoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demoapi.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	@Query(value = "SELECT * FROM Users WHERE username  = ?1 and password_hash = ?2", nativeQuery = true)
	Optional<Users> doLogin(String username,String password);
	Optional<Users> findByAccesToken(String token);
}