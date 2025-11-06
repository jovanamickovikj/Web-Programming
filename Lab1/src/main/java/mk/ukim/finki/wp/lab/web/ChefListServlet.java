package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import mk.ukim.finki.wp.lab.service.ChefServiceImpl;
import mk.ukim.finki.wp.lab.service.DishService;

import java.io.IOException;
import java.util.List;


@WebServlet (name = "listChefs", urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {
    private final ChefServiceImpl chefService;
    private final SpringTemplateEngine templateEngine;
    public ChefListServlet(ChefServiceImpl chefService, ChefServiceImpl chefService1, DishService dishService, SpringTemplateEngine templateEngine) {
        this.chefService = chefService1;

        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);
        List<Chef> chefs = chefService.listChefs();
        webContext.setVariable("chefs", chefs);
        templateEngine.process("listChefs.html", webContext, resp.getWriter());



    }

}
