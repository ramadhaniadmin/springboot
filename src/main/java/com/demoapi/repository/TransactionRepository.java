package com.demoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demoapi.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query(value = "SELECT * FROM transaction WHERE user_id  = ?1 order by transaction_history_id desc", nativeQuery = true)
	List<Transaction> findByUsersId(long usersId);

}
