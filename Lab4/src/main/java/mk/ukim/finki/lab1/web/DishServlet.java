package mk.ukim.finki.lab1.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab1.model.Chef;
import mk.ukim.finki.lab1.model.Dish;
import mk.ukim.finki.lab1.service.ChefServiceImpl;
import mk.ukim.finki.lab1.service.DishServiceImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "dish",urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final DishServiceImpl dishService;
    private final ChefServiceImpl chefService;
    private final TemplateEngine templateEngine;

    public DishServlet(DishServiceImpl dishService, ChefServiceImpl chefService, TemplateEngine templateEngine) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long chefId = Long.parseLong(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);
        List<Dish> dishes = dishService.listDishes();

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("dishes", dishes);
        webContext.setVariable("chef", chef);

        templateEngine.process("dishList.html",webContext, resp.getWriter());

    }
}
