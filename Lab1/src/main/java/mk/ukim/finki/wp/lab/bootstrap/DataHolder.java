package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {

    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {

        dishes.add(new Dish("D1", "Spaghetti Carbonara", "Italian", 25));
        dishes.add(new Dish("D2", "Sushi Roll", "Japanese", 40));
        dishes.add(new Dish("D3", "Tacos", "Mexican", 20));
        dishes.add(new Dish("D4", "Butter Chicken", "Indian", 35));
        dishes.add(new Dish("D5", "Beef Stroganoff", "Russian", 45));

        chefs.add(new Chef(1L, "Gordon", "Ramsay", "Expert in fine dining",
                new ArrayList<>(Arrays.asList(dishes.get(0), dishes.get(1)))));
        chefs.add(new Chef(2L, "Jamie", "Oliver", "Healthy food specialist",
                new ArrayList<>(Arrays.asList(dishes.get(2)))));
        chefs.add(new Chef(3L, "Nigella", "Lawson", "Home-style cooking",
                new ArrayList<>(Arrays.asList(dishes.get(3)))));
        chefs.add(new Chef(4L, "Heston", "Blumenthal", "Molecular gastronomy expert",
                new ArrayList<>(Arrays.asList(dishes.get(4)))));
        chefs.add(new Chef(5L, "Massimo", "Bottura", "Modern Italian cuisine",
                new ArrayList<>(Arrays.asList(dishes.get(0), dishes.get(4)))));

    }
    }

