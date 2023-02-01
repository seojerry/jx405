package javabasic.videomanager.model;

import lombok.Data;

import java.util.Date;

@Data

public class FilmDTO {
    private int film_id;
    private String title;
    private String description;
    private int release_year;
    private int language_id;
    private int original_language_id;
    private int rental_duration;
    private double rental_rate;
    private int length;
    private double replacement_cost;
    private String rating;
    private String special_features;
    private Date last_update;
}
