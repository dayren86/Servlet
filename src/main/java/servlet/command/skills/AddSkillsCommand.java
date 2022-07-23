package servlet.command.skills;

import data.entity.Skills;
import data.queries.RequestsForSkills;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddSkillsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Skills skills = new Skills();
        skills.setPosition(Skills.Position.valueOf(req.getParameter("position")));
        skills.setSkillLevel(Skills.SkillLevel.valueOf(req.getParameter("skillLevel")));

        new RequestsForSkills().createSkills(skills);

        resp.sendRedirect("/skills");
    }
}
