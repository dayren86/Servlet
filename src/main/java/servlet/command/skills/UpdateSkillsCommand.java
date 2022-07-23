package servlet.command.skills;

import data.entity.Skills;
import data.queries.RequestsForSkills;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateSkillsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Skills skills = new Skills();
        skills.setId(Integer.parseInt(req.getParameter("updateId")));
        skills.setPosition(Skills.Position.valueOf(req.getParameter("updatePosition")));
        skills.setSkillLevel(Skills.SkillLevel.valueOf(req.getParameter("updateSkillLevel")));

        new RequestsForSkills().updateSkills(skills.getId(),skills);

        resp.sendRedirect("/skills");
    }
}
