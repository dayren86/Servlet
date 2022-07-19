package servlet.command.companies;

import data.queries.RequestsForCompanies;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCommands implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        try {
            new RequestsForCompanies().deleteCompaniesById(Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resp.sendRedirect("/app/companies");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
