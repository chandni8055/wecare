package com.infosys.repository;

import com.infosys.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

   Optional<UserEntity> findByUserId(String userId);
}
