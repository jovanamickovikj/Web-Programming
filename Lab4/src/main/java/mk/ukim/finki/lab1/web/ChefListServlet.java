package mk.ukim.finki.lab1.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab1.model.Chef;
import mk.ukim.finki.lab1.service.ChefServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "chefList",urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {

    private final ChefServiceImpl chefService;
    private final SpringTemplateEngine templateEngine;

    public ChefListServlet(ChefServiceImpl chefService,SpringTemplateEngine templateEngine) {
        this.chefService = chefService;
        this.templateEngine = templateEngine;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request,response);

        WebContext context = new WebContext(webExchange);
        List<Chef> chefList = chefService.listChefs();
        context.setVariable("chefList",chefList);
        templateEngine.process("listChefs.html", context, response.getWriter());
    }
}

