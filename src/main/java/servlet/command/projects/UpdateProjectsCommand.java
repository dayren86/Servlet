package servlet.command.projects;

import data.entity.Projects;
import data.queries.RequestsForProjects;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateProjectsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Projects projects = new Projects();
        projects.setId(Integer.parseInt(req.getParameter("updateId")));
        projects.setProjectName(req.getParameter("updateProjectName"));
        projects.setProjectDescription(req.getParameter("updateProjectDescription"));
        projects.setDateCreation(LocalDate.parse(req.getParameter("updateDateCreation")));

        new RequestsForProjects().updateProjects(projects.getId(), projects);

        resp.sendRedirect("/projects");
    }
}
