package com.infosys.service;

import com.infosys.dto.BookingDTO;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Boolean rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, char slot) throws WecareException {
        Optional<BookingEntity> optionalBookingEntity = bookRepository.findById(bookingId);
        if (optionalBookingEntity.isPresent()) {
            BookingEntity bookingEntity = optionalBookingEntity.get();
            BookingEntity allBookings = bookRepository.findAllBookings(bookingEntity.getUserId(), appointmentDate, slot);
            if(null == allBookings) {
                bookRepository.save(new BookingEntity(bookingEntity.getUserId(), bookingEntity.getCoachId(), slot, appointmentDate));

                Optional<UserEntity> byUserId = userRepository.findByUserId(bookingEntity.getUserId());
                if (byUserId.isEmpty()) {
                    throw new WecareException("{USER_NOT_FOUND}");
                }
                Optional<CoachEntity> byCoachId = coachRepository.findByCoachId(bookingEntity.getCoachId());
                if (byCoachId.isEmpty()) {
                    throw new WecareException("{COACH_NOT_FOUND}");
                }

                mailUtility.sendReschedulingEmail(
                        byUserId.get().getName(),
                        byCoachId.get().getName(),
                        byUserId.get().getEmail(),
                        bookingId,
                        String.valueOf(bookingEntity.getSlot()),
                        bookingEntity.getAppointmentDate()
                );
            } else {
                throw new WecareException("{BOOKING_ALREADY_EXISTS}");
            }
        } else {
            throw new WecareException("{BOOKING_NOT_FOUND}");
        }
        return null;
    }

    public void cancelAppointment(Integer bookingId) throws WecareException {
        Optional<BookingEntity> optionalBookingEntity = bookRepository.findById(bookingId);
        if (optionalBookingEntity.isPresent()) {
            BookingEntity bookingEntity = optionalBookingEntity.get();
            bookRepository.deleteById(bookingId);
            Optional<UserEntity> byUserId = userRepository.findByUserId(bookingEntity.getUserId());
            if (byUserId.isEmpty()) {
                throw new WecareException("{USER_NOT_FOUND}");
            }
            Optional<CoachEntity> byCoachId = coachRepository.findByCoachId(bookingEntity.getCoachId());
            if (byCoachId.isEmpty()) {
                throw new WecareException("{COACH_NOT_FOUND}");
            }
            mailUtility.sendCancellingEmail(
                    byUserId.get().getName(),
                    byCoachId.get().getName(),
                    byUserId.get().getEmail(),
                    bookingId,
                    String.valueOf(bookingEntity.getSlot()),
                    bookingEntity.getAppointmentDate()
            );
        } else {
            throw new WecareException("{BOOKING_NOT_FOUND}");
        }
    }

    public BookingDTO findByBookingId(Integer bookingId) throws WecareException {
        Optional<BookingEntity> optionalBookingEntity = bookRepository.findById(bookingId);
        if (optionalBookingEntity.isPresent()) {
            return getBookingDTOFromEntity(optionalBookingEntity.get());
        } else {
            throw new WecareException("{BOOKING_NOT_FOUND}");
        }
    }

    public List<BookingDTO> findBookingByUserId(String userId) {
        return bookRepository.findBookingByUserId(userId, LocalDate.now())
                .stream().map(this::getBookingDTOFromEntity)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> findBookingByCoachId(String coachId) {
        return bookRepository.findBookingByCoachId(coachId, LocalDate.now())
                .stream().map(this::getBookingDTOFromEntity)
                .collect(Collectors.toList());
    }

    private BookingDTO getBookingDTOFromEntity(BookingEntity bookingEntity) {
        BookingDTO response = new BookingDTO();
        response.setBookingId(bookingEntity.getBookingId());
        response.setUserId(bookingEntity.getUserId());
        response.setCoachId(bookingEntity.getCoachId());
        response.setAppointmentDate(bookingEntity.getAppointmentDate());
        response.setSlot(bookingEntity.getSlot());
        return response;
    }
}
