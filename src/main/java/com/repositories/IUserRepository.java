package com.repositories;

import com.entities.UserEntity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
	@Query(value = "select * from users where name like concat('%', :name, '%') and status = :status and phone like concat('%', :phone, '%')", nativeQuery = true)
	Page<UserEntity> find (@Param("name") String name, Integer status, String phone, Pageable pageable);
}

