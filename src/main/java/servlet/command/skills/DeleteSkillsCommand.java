package servlet.command.skills;

import data.queries.RequestsForSkills;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteSkillsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String deleteSkill = req.getParameter("deleteSkill");

        new RequestsForSkills().deleteSkillsById(Integer.parseInt(deleteSkill));

        resp.sendRedirect("/skills");
    }
}
