package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Chef;

import java.util.List;

public interface ChefService {

    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
    Chef famousChef();
}
