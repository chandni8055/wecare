package com.infosys.controller;

import com.infosys.dto.BookingDTO;
import com.infosys.dto.CoachDTO;
import com.infosys.dto.LoginDTO;
import com.infosys.exception.WecareException;
import com.infosys.service.BookService;
import com.infosys.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/coaches")
public class CoachRestController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<String> createCoach(@Valid @RequestBody CoachDTO coachDTO) {
        String savedCoach = coachService.createCoach(coachDTO);
        return new ResponseEntity<>(savedCoach,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginCoach(@Valid @RequestBody LoginDTO loginDTO) throws WecareException {
        boolean loginCoach = coachService.loginCoach(loginDTO);
        return new ResponseEntity<>(loginCoach, HttpStatus.OK);
    }

    @GetMapping("/{coachId}")
    public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable String coachId) {
        CoachDTO coachProfile = coachService.getCoachProfile(coachId);
        return new ResponseEntity<>(coachProfile, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CoachDTO> showAllCoaches() {
        List<CoachDTO> coachDTOList = coachService.showAllCoaches();
        return coachDTOList;
    }

    @GetMapping("/booking/{coachId}")
    public List<BookingDTO> showMySchedule(@PathVariable String coachId) {
        return bookService.findBookingByCoachId(coachId);
    }
}
