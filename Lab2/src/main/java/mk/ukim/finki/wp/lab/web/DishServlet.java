package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import mk.ukim.finki.wp.lab.service.ChefServiceImpl;
import mk.ukim.finki.wp.lab.service.DishService;

import java.io.IOException;
import java.util.List;


@WebServlet (name = "dish", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    private final ChefServiceImpl chefService;
    private final SpringTemplateEngine templateEngine;
    private final DishService dishService;

    public DishServlet(ChefServiceImpl chefService, SpringTemplateEngine templateEngine, DishService dishService) {
        this.chefService = chefService;
        this.templateEngine = templateEngine;
        this.dishService = dishService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);
        Long chefId = Long.valueOf(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);
        List<Dish> dishes = dishService.listDishes();
        webContext.setVariable("chef", chef);
        webContext.setVariable("dishes", dishes);
        templateEngine.process("dishesList.html", webContext, resp.getWriter());



    }

}
