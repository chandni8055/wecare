package com.infosys.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity(name = "bookingtable")
@Table(name = "bookingtable")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int	bookingId;
    private String	userId;
    private String	coachId;
    private char slot;
    private LocalDate appointmentDate;

    public BookingEntity() {
    }

    public BookingEntity(String userId, String coachId, char slot, LocalDate appointmentDate) {
        this.userId = userId;
        this.coachId = coachId;
        this.slot = slot;
        this.appointmentDate = appointmentDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public char getSlot() {
        return slot;
    }

    public void setSlot(char slot) {
        this.slot = slot;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
