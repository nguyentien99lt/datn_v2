package com.repositories;

import com.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

@Query(value = "SELECT * FROM  users u WHERE u.email = ?",nativeQuery = true)
    UserEntity findByEmail(String email);

}