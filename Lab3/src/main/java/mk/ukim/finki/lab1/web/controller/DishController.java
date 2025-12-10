package mk.ukim.finki.lab1.web.controller;


import mk.ukim.finki.lab1.model.Dish;
import mk.ukim.finki.lab1.service.ChefServiceImpl;
import mk.ukim.finki.lab1.service.DishServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishServiceImpl dishService;
    private final ChefServiceImpl chefService;

    public DishController(DishServiceImpl dishService, ChefServiceImpl chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }


    @GetMapping()
    public String getDishesPage(@RequestParam(required = false) String error, Model model){
        List<Dish> dishList = dishService.listDishes();
        model.addAttribute("dishList", dishList);
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "listDishes.html";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId){
        dishService.create(dishId, name, cuisine, preparationTime, chefId);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model){
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form.html";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId){
        dishService.update(id, dishId, name, cuisine, preparationTime, chefId);
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model){
        if (dishService.findById(id)==null){
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("dish", dishService.findById(id));
        return "dish-form.html";
    }

    @PostMapping("/chefDetails")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId,
                                Model model){
        chefService.addDishToChef(chefId, dishId);
        model.addAttribute("chef", chefService.findById(chefId));
        return "chefDetails.html";
    }

}