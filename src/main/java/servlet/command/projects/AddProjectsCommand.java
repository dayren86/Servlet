package servlet.command.projects;

import data.entity.Projects;
import data.queries.RequestsForProjects;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddProjectsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Projects projects = new Projects();
        projects.setProjectName(req.getParameter("projectName"));
        projects.setProjectDescription(req.getParameter("projectDescription"));
        projects.setDateCreation(LocalDate.parse(req.getParameter("dateCreation")));

        new RequestsForProjects().createProjects(projects.getProjectName(), projects.getProjectDescription(),projects.getDateCreation());
        resp.sendRedirect("/app/projects");
    }
}
