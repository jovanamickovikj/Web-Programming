package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Chef;
import mk.ukim.finki.lab1.model.Dish;
import mk.ukim.finki.lab1.repository.ChefRepository;
import mk.ukim.finki.lab1.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;
    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = findById(chefId);
        Dish dish = dishRepository.findByDishId(dishId);

        if (dish != null && chef != null && !chef.getDishes().contains(dish)) {
            chef.getDishes().add(dish);
            chefRepository.save(chef);
        }
        return chef;
    }

    public Chef famousChef(){
        List<Chef> chefs = chefRepository.findAll();
        int maxDishes = chefs.get(0).getDishes().size();
        Chef famousChef=chefs.get(0);
        for (Chef chef : chefs) {
            if(maxDishes < chef.getDishes().size()){
                maxDishes = chef.getDishes().size();
                famousChef = chef;
            }
        }
        return famousChef;
    }
}