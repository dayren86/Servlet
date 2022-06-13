package data.queries;

import data.connection.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsForCompanies {
    private PreparedStatement insertCompanies;
    private PreparedStatement selectCompanies;

    public RequestsForCompanies(Db db) throws SQLException {
        Connection connection = db.getConnection();

        insertCompanies = connection.prepareStatement(
                "INSERT INTO companies (it_companies, company_description) VALUES (?, ?)"
        );

        selectCompanies = connection.prepareStatement(
                "SELECT it_companies, company_description FROM companies WHERE it_companies = ?");
    }

    public boolean createCompanies(String itCompanies, String companyDescription) {
        try {
            insertCompanies.setString(1, itCompanies);
            insertCompanies.setString(2, companyDescription);
            return insertCompanies.executeUpdate() == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<String> selectCompaniesByItCompanies(String itCompanies) {
        try {
            selectCompanies.setString(1, itCompanies);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        List<String> result = new ArrayList<>();

        try(ResultSet resultSet = selectCompanies.executeQuery()) {
            while (resultSet.next()) {
                result.add(resultSet.getString("it_companies") + " " + resultSet.getString("company_description"));
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
