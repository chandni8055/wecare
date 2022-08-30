package com.infosys.repository;

import com.infosys.entity.BookingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookingEntity, String> {
    Optional<BookingEntity> findByUserId(String UserId);

    @Query(value = "select b from bookingtable b where b.userId = :userId and b.appointmentDate = :today")
    List<BookingEntity> findBookingByUserId(String userId, LocalDate today);

    @Query(value = "select b from bookingtable b where b.coachId = :coachId and b.appointmentDate = :today")
    List<BookingEntity> findBookingByCoachId(String coachId, LocalDate today);

    @Query(value = "select b from bookingtable b where b.userId = :userId and b.appointmentDate = :appointmentDate and b.slot = :slot")
    BookingEntity findAllBookings(String userId, LocalDate appointmentDate, char slot);
}
