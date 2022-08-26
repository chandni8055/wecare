package com.infosys.service;

import com.infosys.dto.CoachDTO;
import com.infosys.dto.LoginDTO;
import com.infosys.entity.CoachEntity;
import com.infosys.exception.WecareException;
import com.infosys.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CoachService {
     @Autowired
     private CoachRepository coachRepository;

    //createCoach method
    public String createCoach(CoachDTO coachDTO){
        CoachEntity coachEntity = getCoachEntityFromDto(coachDTO);
        CoachEntity savedCoach = coachRepository.save(coachEntity);
        return savedCoach.getCoachId();
    }

    public boolean loginCoach(LoginDTO loginDTO) throws WecareException {
        Optional<CoachEntity> coachEntityOptional = coachRepository.findById(loginDTO.getId());
        if (coachEntityOptional.isPresent()) {
            return coachEntityOptional.get().getPassword().equals(loginDTO.getPassword());
        } else {
            throw new WecareException("coach.not.found");
        }
    }

    public CoachDTO getCoachProfile(String coachId) {
        Optional<CoachEntity> coachEntityOptional = coachRepository.findByCoachId(coachId);
        if(coachEntityOptional.isPresent()){
            CoachEntity coachEntity = coachEntityOptional.get();
            return getCoachDTOFromEntity(coachEntity);
        }else {
        return null;
        }
    }

    public List<CoachDTO> showAllCoaches() {
        Iterable<CoachEntity> coachEntities = coachRepository.findAll();
        return StreamSupport.stream(coachEntities.spliterator(), false)
                .map(this::getCoachDTOFromEntity)
                .collect(Collectors.toList());
    }

    private CoachEntity getCoachEntityFromDto(CoachDTO coachDTO) {
        CoachEntity coachEntity = new CoachEntity();
        coachEntity.setName(coachDTO.getName());
        coachEntity.setPassword(coachDTO.getPassword());
        coachEntity.setGender(coachDTO.getGender());
        coachEntity.setDateOfBirth(coachDTO.getDateOfBirth());
        coachEntity.setMobileNumber(coachDTO.getMobileNumber());
        coachEntity.setSpeciality(coachDTO.getSpeciality());
        return coachEntity;
    }

    private CoachDTO getCoachDTOFromEntity(CoachEntity coachEntity) {
        CoachDTO response = new CoachDTO();
        response.setCoachId(coachEntity.getCoachId());
        response.setPassword(coachEntity.getPassword());
        response.setName(coachEntity.getName());
        response.setDateOfBirth(coachEntity.getDateOfBirth());
        response.setGender(coachEntity.getGender());
        response.setMobileNumber(coachEntity.getMobileNumber());
        response.setSpeciality(coachEntity.getSpeciality());
        return response;
    }
}
