package com.example.jolebefood.DTO;

import java.util.Date;

public class UserDTO {
    private String Password, Name, Email, Address, Phone;

    public UserDTO(String password, String name, String email, String address, String phone) {
        Password = password;
        Name = name;
        Email = email;
        Address = address;
        Phone = phone;
    }

    public UserDTO() {

    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
