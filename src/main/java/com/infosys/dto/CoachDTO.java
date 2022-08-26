package com.infosys.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CoachDTO {
    private String coachId;

    @NotNull(message = "{coach.password.notpresent}")
    @Size(min = 5, max = 10, message = "{coach.password.invalid}")
    private String password;

    @NotNull(message = "{coach.name.notpresent}")
    @Size(min = 3, max = 50, message = "{coach.name.invalid}")
    private String name;
    private LocalDate dateOfBirth;
    private char gender;
    @NotNull(message = "{coach.mobileNumber.notpresent}")
//    @Min(value = 10,  message = "{coach.mobileNumber.invalid}")
//    @Max(value = 10,  message = "{coach.mobileNumber.invalid}")
    private Long mobileNumber;

    @NotNull(message = "{coach.speciality.notpresent}")
    @Size(min = 3, max = 50, message = "{coach.speciality.invalid}")
    private String speciality;

    public CoachDTO() {
    }

    public CoachDTO(String coachId, String password, String name, LocalDate dateOfBirth, char gender, long mobileNumber, String speciality) {
        this.coachId = coachId;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.speciality = speciality;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
