package com.infosys.service;


import com.infosys.dto.LoginDTO;
import com.infosys.dto.UserDTO;
import com.infosys.entity.UserEntity;
import com.infosys.exception.WecareException;
import com.infosys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public String createUser(UserDTO userDTO) {
        UserEntity userEntity = getUserEntityFromDto(userDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return savedUser.getUserId();
    }

    public boolean loginUser(LoginDTO loginDTO) throws WecareException{
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(loginDTO.getId());
        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get().getPassword().equals(loginDTO.getPassword());
        } else {
            throw new WecareException("USER.NOT.FOUND");
        }
    }

    public UserDTO getUserProfile(String userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            return getUserDTOFromEntity(userEntity);
        }else {
            return null;
        }
    }

    private UserEntity getUserEntityFromDto(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setGender(userDTO.getGender());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setMobileNumber(userDTO.getMobileNumber());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPincode(userDTO.getPincode());
        userEntity.setCity(userDTO.getCity());
        userEntity.setState(userDTO.getState());
        userEntity.setCountry(userDTO.getCountry());
        return userEntity;
    }

    private UserDTO getUserDTOFromEntity(UserEntity userEntity) {
        UserDTO response = new UserDTO();
        response.setUserId(userEntity.getUserId());
        response.setName(userEntity.getName());
        response.setPassword(userEntity.getPassword());
        response.setGender(userEntity.getGender());
        response.setDateOfBirth(userEntity.getDateOfBirth());
        response.setMobileNumber(userEntity.getMobileNumber());
        response.setPincode(userEntity.getPincode());
        response.setCity(userEntity.getCity());
        response.setState(userEntity.getState());
        response.setCountry(userEntity.getCountry());
        return response;
    }


}
