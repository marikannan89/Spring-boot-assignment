package com.aleph.assignment.persistance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aleph.assignment.persistance.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
	
	@Query(value = "SELECT * FROM customers where name = :name",nativeQuery=true) 
	Optional<List<CustomerEntity>> findByName(String name);
	
	@Query(value = "SELECT * FROM customers where age = :age",nativeQuery=true) 
	Optional<List<CustomerEntity>> findByAge(String age);
	
	@Query(value = "SELECT * FROM customers where ic = :ic",nativeQuery=true) 
	Optional<CustomerEntity> findByIC(String ic);
	
	@Query(value = "SELECT * FROM customers where id = :id",nativeQuery=true)
	Optional<CustomerEntity> findById(Long id);
}
