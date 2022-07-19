package data.queries;

import data.connection.Db;
import data.entity.Companies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsForCompanies {
    Db db = Db.getInstance();

    private PreparedStatement insertCompanies;
    private PreparedStatement selectCompanies;
    private PreparedStatement deleteCompaniesByIdSt;
    private PreparedStatement updateCompaniesSt;
    private PreparedStatement selectCompaniesAll;

    public RequestsForCompanies() throws SQLException {
        Connection connection = db.getConnection();

        insertCompanies = connection.prepareStatement(
                "INSERT INTO companies (it_companies, company_description) VALUES (?, ?)"
        );

        selectCompanies = connection.prepareStatement(
                "SELECT id, it_companies, company_description FROM companies WHERE it_companies = ?"
        );
        deleteCompaniesByIdSt = connection.prepareStatement(
                "DELETE FROM companies WHERE id = ?"
        );
        updateCompaniesSt = connection.prepareStatement(
                "UPDATE companies SET it_companies = ?, company_description = ? WHERE id = ?"
        );
        selectCompaniesAll = connection.prepareStatement(
                "SELECT id, it_companies, company_description FROM companies");
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

    public void createCompaniesFromList(List<Companies> companiesList) {
        try {
            for (Companies companies : companiesList) {
                insertCompanies.setString(1, companies.getItCompanies());
                insertCompanies.setString(2, companies.getCompanyDescription());
                insertCompanies.addBatch();
            }
            insertCompanies.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Companies selectCompaniesByItCompanies(String itCompanies) {
        try {
            selectCompanies.setString(1, itCompanies);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Companies companies = new Companies();

        try(ResultSet resultSet = selectCompanies.executeQuery()) {
            resultSet.next();
            companies.setId(resultSet.getInt("id"));
            companies.setItCompanies(resultSet.getString("it_companies"));
            companies.setCompanyDescription(resultSet.getString("company_description"));

            return companies;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Companies> selectAllCompanies() {
        try(ResultSet resultSet = selectCompaniesAll.executeQuery()) {
            List<Companies> result = new ArrayList<>();
            while (resultSet.next()) {
                Companies companies = new Companies();

                companies.setId(resultSet.getInt("id"));
                companies.setItCompanies(resultSet.getString("it_companies"));
                companies.setCompanyDescription(resultSet.getString("company_description"));

                result.add(companies);
            }

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteCompaniesById(int id) throws SQLException {
        deleteCompaniesByIdSt.setInt(1, id);
        deleteCompaniesByIdSt.executeUpdate();
    }

    public void updateCompanies(int id, Companies companies) {
        try {
            updateCompaniesSt.setString(1, companies.getItCompanies());
            updateCompaniesSt.setString(2, companies.getCompanyDescription());
            updateCompaniesSt.setInt(3, id);
            updateCompaniesSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
