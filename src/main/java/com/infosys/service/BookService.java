package com.infosys.service;

import com.infosys.controller.BookRestController;
import com.infosys.entity.BookingEntity;
import com.infosys.entity.CoachEntity;
import com.infosys.entity.UserEntity;
import com.infosys.exception.WecareException;
import com.infosys.repository.BookRepository;
import com.infosys.repository.CoachRepository;
import com.infosys.repository.UserRepository;
import com.infosys.utility.MailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private MailUtility mailUtility;

    public Boolean bookAppointment(String userId, String coachId, LocalDate appointmentDate, char slot) throws WecareException {
        BookingEntity allBookings = bookRepository.findAllBookings(userId, appointmentDate, slot);
        if(null == allBookings) {
            BookingEntity savedBooking = bookRepository.save(new BookingEntity(userId, coachId, slot, appointmentDate));
            Optional<UserEntity> byUserId = userRepository.findByUserId(userId);
            if (byUserId.isEmpty()) {
                throw new WecareException("{USER_NOT_FOUND}");
            }
            Optional<CoachEntity> byCoachId = coachRepository.findByCoachId(coachId);
            if (byCoachId.isEmpty()) {
                throw new WecareException("{COACH_NOT_FOUND}");
            }
            mailUtility.sendSchedulingEmail(
                    byUserId.get().getName(),
                    byCoachId.get().getName(),
                    byUserId.get().getEmail(),
                    savedBooking.getBookingId(),
                    String.valueOf(slot),
                    appointmentDate
                    );
            return true;
        } else {
            throw new WecareException("{BOOKING_ALREADY_EXISTS}");
        }
    }

    public Boolean rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, char slot) {

        return null;
    }
}
