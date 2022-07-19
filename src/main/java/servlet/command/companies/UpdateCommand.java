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
    public void process(HttpServletRequest req, HttpServletResponse resp)  {
        String id = req.getParameter("id");
        String updateName = req.getParameter("updateName");
        String updateCompanyDescription = req.getParameter("updateCompanyDescription");

        try {
            new RequestsForCompanies().updateCompanies(Integer.parseInt(id), new Companies(updateName,updateCompanyDescription));
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
