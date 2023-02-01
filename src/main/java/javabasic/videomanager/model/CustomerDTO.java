package javabasic.videomanager.model;

import javabasic.model.UserDTO;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.Date;

public class CustomerDTO {
    private int customer_id;
    private int store_id;
    private String first_name;
    private String last_name;
    private String email;
    private int address_id;
    private int active;
    private Date create_date;
    private Date last_update;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public boolean equals(Object o) {
        if (o instanceof CustomerDTO) {
            CustomerDTO c = (CustomerDTO) o;
            return customer_id == c.customer_id;
        }
        return false;
    }

    public CustomerDTO(CustomerDTO origin) {
        customer_id = origin.customer_id;
        store_id = origin.store_id;
        first_name = origin.first_name;
        last_name = origin.last_name;
        email = origin.email;
        address_id = origin.address_id;
        active = origin.active;
        create_date = origin.create_date;
        last_update = origin.last_update;

    }

    public CustomerDTO() {

    }

    public CustomerDTO(int id) {
        this.customer_id = id;
    }

    public String toString() {
        return "{"+
                "customer_id: "+customer_id + ", "+
                "first_name: "+first_name + ", "+
                "last_name: "+last_name + ", "+
                "email: "+email +
                "}";
    }
}
