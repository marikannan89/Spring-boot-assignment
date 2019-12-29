package com.aleph.assignment.persistance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aleph.assignment.persistance.entity.CusomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CusomerEntity,Long> {
	
	@Query(value = "SELECT * FROM customers where name = :name",nativeQuery=true) 
	Optional<List<CusomerEntity>> findByName(String name);
	
	@Query(value = "SELECT * FROM customers where age = :age",nativeQuery=true) 
	Optional<List<CusomerEntity>> findByAge(String age);
	
	@Query(value = "SELECT * FROM customers where ic = :ic",nativeQuery=true) 
	Optional<CusomerEntity> findByIC(String ic);
	
	@Query(value = "SELECT * FROM customers where id = :id",nativeQuery=true)
	Optional<CusomerEntity> findById(Long id);
}
