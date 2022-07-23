package servlet.command.companies;

import data.entity.Companies;
import data.queries.RequestsForCompanies;
import servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String id = req.getParameter("id");
        String updateName = req.getParameter("updateName");
        String updateCompanyDescription = req.getParameter("updateCompanyDescription");


        new RequestsForCompanies().updateCompanies(Integer.parseInt(id), new Companies(updateName, updateCompanyDescription));


        resp.sendRedirect("/companies");

    }
}
