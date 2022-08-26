package com.infosys.repository;

import com.infosys.entity.CoachEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends CrudRepository<CoachEntity,String> {

    Optional<CoachEntity> findByCoachId(String coachId);
}
