package servlet;

import data.queries.RequestsForCompanies;
import lombok.SneakyThrows;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import servlet.command.CommandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/mainpage")
public class MainPage extends HttpServlet {

    private static TemplateEngine engine;

    private static CommandService commandService;

    public static TemplateEngine getEngine() {
        return engine;
    }

    public static CommandService getCommandService() {
        return commandService;
    }

    @Override
    public void init() throws ServletException {
        engine = new TemplateEngine();


        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("D:/java/GoItHW6/src/main/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(engine.getTemplateResolvers().size());
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);

        commandService = new CommandService();
    }



    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Context context = new Context(
                    req.getLocale(),
                    Map.of("requestsForCompanies", new RequestsForCompanies().selectAllCompanies())
            );

        engine.process("mainpage", context, resp.getWriter());
        resp.getWriter().close();
    }
}
