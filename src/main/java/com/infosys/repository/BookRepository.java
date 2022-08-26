package com.infosys.repository;

import com.infosys.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<BookingEntity, String> {
//    Optional<BookingEntity> findByUserId(String UserId);
//    List<BookingEntity>findBookingByUserId(String userId, LocalDate today);
//    List<BookingEntity>findBookingByCoachId(String coachId, LocalDate today);
//    BookingEntity findAllBookings(String userId, LocalDate appointmentDate, String slot);
}
