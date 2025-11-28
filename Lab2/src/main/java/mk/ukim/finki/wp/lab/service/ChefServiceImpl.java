package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public ChefServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
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

}
