package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllByChef_Id(Long chefId);

    Dish findByDishId(String dishId);
}
