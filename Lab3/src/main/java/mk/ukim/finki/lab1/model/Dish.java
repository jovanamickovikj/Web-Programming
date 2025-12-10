package mk.ukim.finki.lab1.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="dishes")
public class Dish {

    @Id
    @GeneratedValue
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;
    @ManyToOne
    private Chef chef;

    public Dish(String dishId, String name, String cuisine, int preparationTime, Chef chef) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.chef = chef;
    }

    public Dish(){
    }

    public String getDishId() {
        return dishId;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
}