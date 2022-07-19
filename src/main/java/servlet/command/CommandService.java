package servlet.command;

import servlet.command.companies.AddCommands;
import servlet.command.companies.DeleteCommands;
import servlet.command.companies.GetCommands;
import servlet.command.companies.UpdateCommand;
import servlet.command.developers.AddDevelopersCommand;
import servlet.command.developers.DeleteDevelopersCommand;
import servlet.command.developers.GetDevelopersCommand;
import servlet.command.developers.UpdateDevelopersCommand;
import servlet.command.projects.AddProjectsCommand;
import servlet.command.projects.DeleteProjectsCommand;
import servlet.command.projects.GetProjectsCommand;
import servlet.command.projects.UpdateProjectsCommand;
import servlet.command.skills.AddSkillsCommand;
import servlet.command.skills.DeleteSkillsCommand;
import servlet.command.skills.GetSkillsCommand;
import servlet.command.skills.UpdateSkillsCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandService {
    private Map<String, Command> commands;

    public CommandService() {
        commands = new HashMap<>();

        commands.put("GET /app/companies", new GetCommands());
        commands.put("POST /app/companies/delete", new DeleteCommands());
        commands.put("POST /app/companies", new AddCommands());
        commands.put("POST /app/companies/update", new UpdateCommand());
        commands.put("GET /app/developers", new GetDevelopersCommand());
        commands.put("POST /app/developers/add", new AddDevelopersCommand());
        commands.put("POST /app/developers/delete", new DeleteDevelopersCommand());
        commands.put("POST /app/developers/update", new UpdateDevelopersCommand());
        commands.put("GET /app/skills", new GetSkillsCommand());
        commands.put("POST /app/skills/add", new AddSkillsCommand());
        commands.put("POST /app/skills/update", new UpdateSkillsCommand());
        commands.put("POST /app/skills/delete", new DeleteSkillsCommand());
        commands.put("GET /app/projects", new GetProjectsCommand());
        commands.put("POST /app/projects/add", new AddProjectsCommand());
        commands.put("POST /app/projects/update", new UpdateProjectsCommand());
        commands.put("POST /app/projects/delete", new DeleteProjectsCommand());
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String requestUri = req.getRequestURI();
        String commandKey = req.getMethod() + " " + requestUri;

        try {
            commands.get(commandKey).process(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
