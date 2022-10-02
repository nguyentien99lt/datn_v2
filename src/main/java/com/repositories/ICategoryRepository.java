package com.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entities.CategoryEntity;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	
	@Query(value = "select * from categories where name like concat('%', :name, '%') and status = :status", nativeQuery = true)
	Page<CategoryEntity> findByNameLikeAndStatus (@Param("name") String name, Integer status, Pageable pageable);
}
