package com.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoapi.model.TrxType;

public interface TrxTypeRepository extends JpaRepository<TrxType, Long>{
	
}
