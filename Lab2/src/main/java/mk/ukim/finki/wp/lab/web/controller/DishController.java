package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService,
                          ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping("/dishes")
    public String getDishesPage(@RequestParam(required = false) String error,
                                Model model) {
        model.addAttribute("dishes", dishService.listDishes());
        if (error != null) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "listDishes";
    }

    @GetMapping("/dishes/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", null);
        return "dish-form";
    }

    @GetMapping("/dishes/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        try {
            Dish dish = dishService.findById(id);
            model.addAttribute("dish", dish);
            return "dish-form";
        } catch (RuntimeException ex) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }

    @PostMapping("/dishes/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.create(dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/dishes/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        dishService.update(id, dishId, name, cuisine, preparationTime);
        return "redirect:/dishes";
    }

    @PostMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/listChefs")
    public String listChefs(Model model) {
        List<Chef> chefs = chefService.listChefs();
        model.addAttribute("chefs", chefs);
        return "listChefs";
    }

    @PostMapping("/dish")
    public String showDishesForChef(@RequestParam Long chefId,
                                    Model model) {
        Chef chef = chefService.findById(chefId);
        List<Dish> dishes = dishService.listDishes();

        model.addAttribute("chef", chef);
        model.addAttribute("chefId", chefId);
        model.addAttribute("dishes", dishes);

        return "dishesList";
    }

    @PostMapping("/chefDetails")
    public String showChefDetails(@RequestParam Long chefId,
                                  @RequestParam String dishId,
                                  Model model) {
        Chef chef = chefService.addDishToChef(chefId, dishId);
        model.addAttribute("chef", chef);
        model.addAttribute("dishes", chef.getDishes());
        return "chefDetails"; // chefDetails.html
    }
}
