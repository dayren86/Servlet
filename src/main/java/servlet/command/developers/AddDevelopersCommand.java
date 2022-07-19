package servlet.command.developers;

import data.entity.Developers;
import data.queries.RequestsForDevelopers;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddDevelopersCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Developers developers = new Developers();
        developers.setName(req.getParameter("name"));
        developers.setAge(Integer.parseInt(req.getParameter("age")));
        developers.setSex(Developers.Sex.valueOf(req.getParameter("sex")));
        developers.setSalary(Integer.parseInt(req.getParameter("salary")));


        new RequestsForDevelopers().createDeveloper(developers);

        resp.sendRedirect("/app/developers");
    }
}
