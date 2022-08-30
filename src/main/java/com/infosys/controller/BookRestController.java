package com.infosys.controller;

import com.infosys.dto.BookingDTO;
import com.infosys.dto.LoginDTO;
import com.infosys.exception.WecareException;
import com.infosys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/booking")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping("/users/{userId}/coaches/{coachId}")
    public ResponseEntity<Boolean> bookAppointment(@PathVariable String userId, @PathVariable String coachId, @Valid @RequestBody BookingDTO bookingDTO) throws WecareException {
        Boolean isBooked = bookService.bookAppointment(userId, coachId, bookingDTO.getAppointmentDate(), bookingDTO.getSlot());
        return new ResponseEntity<>(isBooked, HttpStatus.OK);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Boolean> rescheduleAppointment(@PathVariable Integer bookingId, @Valid @RequestBody BookingDTO bookingDTO) {
        Boolean isRescheduled = bookService.rescheduleAppointment(bookingId, bookingDTO.getAppointmentDate(), bookingDTO.getSlot());
        return new ResponseEntity<>(isRescheduled, HttpStatus.OK);
    }
}
