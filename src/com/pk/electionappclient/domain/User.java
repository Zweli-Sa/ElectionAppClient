package com.pk.electionappclient.domain;

import java.math.BigInteger;
import java.util.List;

public class User {

    private Long id;
    private String name;
    private String lastname;
    private String street;
    private long number;
    private long flatNumber;
    private String postcode;
    private String pesel;
    private BigInteger idNumber;
    private String email;
    private String phoneNumber;
    private City city;
    private String password;
    private Boolean isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(long flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public BigInteger getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(BigInteger idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String name, String lastname, String street, long number, long flatNumber, String postcode, String pesel, BigInteger idNumber, String email, String phoneNumber, City city, String password, Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.street = street;
        this.number = number;
        this.flatNumber = flatNumber;
        this.postcode = postcode;
        this.pesel = pesel;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
