package servlet.command.skills;

import data.queries.RequestsForSkills;
import org.thymeleaf.context.Context;
import servlet.MainPage;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class GetSkillsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        resp.setContentType("text/html");
        Context context = new Context(
                req.getLocale(),
                Map.of(
                        "requestsForSkills",
                        new RequestsForSkills().getAllSkills())
        );
        MainPage.getEngine().process("skills", context, resp.getWriter());
        resp.getWriter().close();
    }
}
