package servlet.command.projects;

import data.queries.RequestsForProjects;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteProjectsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String id = req.getParameter("deleteProject");
        new RequestsForProjects().deleteProjectsById(Integer.parseInt(id));

        resp.sendRedirect("/projects");
    }
}
