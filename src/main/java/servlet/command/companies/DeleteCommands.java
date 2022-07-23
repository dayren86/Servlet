package servlet.command.companies;

import data.queries.RequestsForCompanies;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCommands implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String id = req.getParameter("id");

        new RequestsForCompanies().deleteCompaniesById(Integer.parseInt(id));


        resp.sendRedirect("/companies");

    }
}
