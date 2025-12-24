package mk.ukim.finki.lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1.model.Chef;
import mk.ukim.finki.lab1.model.Dish;
import mk.ukim.finki.lab1.repository.ChefRepository;
import mk.ukim.finki.lab1.repository.DishRepository;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {

    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DataHolder(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @PostConstruct
    public void init() {

        // Only insert if DB is empty (avoid duplicate inserts)
        if (chefRepository.count() == 0 && dishRepository.count() == 0) {
            Chef chef1 = new Chef("Stefan", "Sotirovski", "2 years experience");
            Chef chef2 = new Chef("Filip", "Stoilkov", "1 year experience");
            Chef chef3 = new Chef("Damjan", "Manojlovski", "13 years experience");
            Chef chef4 = new Chef("Maja", "Sotirovska", "5 years experience");
            Chef chef5 = new Chef("Andrea", "Manasievska", "3 years experience");

            chefRepository.save(chef1);
            chefRepository.save(chef2);
            chefRepository.save(chef3);
            chefRepository.save(chef4);
            chefRepository.save(chef5);

            // Create dishes and assign chefs
            dishRepository.save(new Dish("1", "Cesar Salad", "Salad", 4, chef1));
            dishRepository.save(new Dish("2", "Benedict", "Egg Toast", 5, chef2));
            dishRepository.save(new Dish("3", "Lasagna", "Italian", 9, chef3));
            dishRepository.save(new Dish("4", "ChickenParm", "Meat", 12, chef4));
            dishRepository.save(new Dish("5", "Wok", "Asian", 69, chef5));


        }
    }
}