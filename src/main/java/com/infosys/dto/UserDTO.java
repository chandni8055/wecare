package com.infosys.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserDTO {
    private String coachId;

    @NotNull(message = "{user.password.notpresent}")
    @Size(min = 5, max = 10, message = "{user.password.invalid}")
    private String password;

    @NotNull(message = "{user.name.notpresent}")
    @Size(min =3 ,max= 50, message = "{user.name.invalid}")
    private String name;
    private LocalDate dateOfBirth;
    private char gender;

    @NotNull(message = "{user.mobileNumber.notpresent}")
    @Size(min = 10, max = 10, message = "{user.mobileNumber.invalid}")
    private long mobileNumber;

    @Email(message = "{user.email.invalid}")
    private String email;

    @NotNull(message = "{user.pincode.notpresent}")
    @Size(min = 6, max = 6, message = "{user.pincode.invalid}")
    private int pincode;

    @NotNull(message = "{user.city.notpresent}")
    @Size(min = 3, max = 20, message = "{user.city.invalid}")
    private String city;

    @NotNull(message = "{user.state.notpresent}")
    @Size(min = 3, max = 20, message = "{user.state.invalid}")
    private String state;

    @NotNull(message = "{user.country.notpresent}")
    @Size(min = 3, max = 20, message = "{user.country.invalid}")
    private String country;

    public UserDTO() {
    }

    public UserDTO(String coachId, String password, String name, LocalDate dateOfBirth, char gender, long mobileNumber,
                   String email, int pincode, String city, String state, String country) {
        this.coachId = coachId;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
