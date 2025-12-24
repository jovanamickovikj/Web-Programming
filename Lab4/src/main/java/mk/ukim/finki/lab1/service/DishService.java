package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Chef;
import mk.ukim.finki.lab1.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
    Dish findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId);
    void delete(Long id);

    List<Dish> findAllByChef_Id(Long chefId);
}