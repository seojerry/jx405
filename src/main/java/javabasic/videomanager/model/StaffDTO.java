package javabasic.videomanager.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class StaffDTO {
    private int staff_id;
    private String first_name;
    private String last_name;
    private int address_id;
    private Blob picture;
    private String email;
    private int store_id;
    private int active;
    private String username;
    private String password;
    private Timestamp last_update;

    public StaffDTO() { }
    public StaffDTO(int id) {
        this.staff_id = id;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public boolean equals(Object o) {
        if (o instanceof StaffDTO) {
            StaffDTO u = (StaffDTO) o;
            return staff_id == u.staff_id;
        }
        return false;
    }




    public String toString() {
        return "{"+
                "staff_id: "+staff_id + ", "+
                "first_name: "+first_name + ", "+
                "last_name: "+last_name + ", "+
                "password: "+password + ", "+
                "email: "+email +
                "}";
    }
}
