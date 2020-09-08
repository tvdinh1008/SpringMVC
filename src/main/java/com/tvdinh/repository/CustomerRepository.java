package com.tvdinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvdinh.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>{
	CustomerEntity findByUsername(String username);
}
