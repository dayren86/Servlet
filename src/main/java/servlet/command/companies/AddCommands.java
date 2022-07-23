package servlet.command.companies;

import data.queries.RequestsForCompanies;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddCommands implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        String companyDescription = req.getParameter("companyDescription");


        new RequestsForCompanies().createCompanies(name, companyDescription);


        resp.sendRedirect("/companies");

    }
}
