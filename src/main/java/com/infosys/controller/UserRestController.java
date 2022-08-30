package com.infosys.controller;

import com.infosys.dto.BookingDTO;
import com.infosys.dto.CoachDTO;
import com.infosys.dto.LoginDTO;
import com.infosys.dto.UserDTO;
import com.infosys.exception.WecareException;
import com.infosys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        String savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@Valid @RequestBody LoginDTO loginDTO) throws WecareException {
        boolean loginUser = userService.loginUser(loginDTO);
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) {
        UserDTO   userProfile = userService.getUserProfile(userId);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/booking/{userId}")
    public List<BookingDTO> showMyAppointments(@PathVariable String userId) {
        //TODO: fetch the bookingdto from service and return the list
        return Collections.emptyList();
    }

}
