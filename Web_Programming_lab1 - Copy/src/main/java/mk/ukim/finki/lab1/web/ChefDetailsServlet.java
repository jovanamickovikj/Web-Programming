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
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "chefDetails", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final ChefServiceImpl chefService;
    private final DishServiceImpl dishService;
    private final TemplateEngine templateEngine;


    public ChefDetailsServlet(ChefServiceImpl chefService, DishServiceImpl dishService, TemplateEngine templateEngine) {
        this.chefService = chefService;
        this.dishService = dishService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Long chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");
        Chef chef = chefService.addDishToChef(chefId, dishId);

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("chef",chef);
        templateEngine.process("chefDetails.html", webContext, resp.getWriter());

    }
}
