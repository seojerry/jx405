package javabasic.videomanager.model;

import java.sql.Date;

public class RentalDTO {
    private int rental_id;
    private Date rental_date;
    private int inventory_id;
    private int customer_id;
    private Date return_date;
    private int staff_id;
    private Date last_update;

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public Date getRental_date() {
        return rental_date;
    }

    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public boolean equals(Object o) {
        if (o instanceof RentalDTO) {
            RentalDTO c = (RentalDTO) o;
            return rental_id == c.rental_id;
        }
        return false;
    }

    public RentalDTO(RentalDTO origin) {
        rental_id = origin.rental_id;
        rental_date = origin.rental_date;
        inventory_id = origin.inventory_id;
        customer_id = origin.customer_id;
        return_date = origin.return_date;
        staff_id = origin.staff_id;
        last_update = origin.last_update;
    }

    public RentalDTO() {
    }
    public RentalDTO(int id) {
        this.rental_id = id;
    }

    public String toString() {
        return "{"+
                "rental_id: "+rental_id + ", "+
                "inventory_id: "+inventory_id + ", "+
                "customer_id: "+customer_id + ", "+
                "staff_id: "+staff_id +
                "}";
    }
}
