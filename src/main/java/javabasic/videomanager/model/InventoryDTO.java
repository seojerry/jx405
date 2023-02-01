package javabasic.videomanager.model;

import lombok.Data;

import java.util.Date;

@Data
public class InventoryDTO {
    private int inventory_id;
    private int film_id;
    private int store_id;
    private Date last_update;
}
