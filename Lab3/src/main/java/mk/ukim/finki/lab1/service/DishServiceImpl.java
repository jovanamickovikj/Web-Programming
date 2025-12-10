package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Chef;
import mk.ukim.finki.lab1.model.Dish;
import mk.ukim.finki.lab1.repository.ChefRepository;
import mk.ukim.finki.lab1.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;
    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        if(dishId == null) return null;
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        if(id == null) return null;
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Chef chef = chefRepository.findById(chefId).orElse(null);

        Dish dish = new Dish(dishId, name, cuisine, preparationTime, chef);
        dishRepository.save(dish);

        assert chef != null;
        chef.getDishes().add(dish);
        chefRepository.save(chef);

        return dish;

    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Dish d = findById(id);
        d.setName(name);
        d.setCuisine(cuisine);
        d.setPreparationTime(preparationTime);
        d.setDishId(dishId);
        d.setChef(chefRepository.findById(chefId).orElse(null));
        return dishRepository.save(d);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> findAllByChef_Id(Long chefId){
        return dishRepository.findAllByChef_Id(chefId);
    }
}