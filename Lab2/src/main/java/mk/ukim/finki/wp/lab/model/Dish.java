package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Dish {

    private static long counter = 1L;

    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.id = (long) (Math.random() * 1000);
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}
