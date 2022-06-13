package data.queries;

import data.connection.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsForDevelopers {
    private PreparedStatement insertDevelopers;
    private PreparedStatement selectDevelopers;
    private PreparedStatement geId;
    private PreparedStatement addToProjectDeveloper;

    public RequestsForDevelopers(Db db) throws SQLException {
        Connection connection = db.getConnection();

        insertDevelopers = connection.prepareStatement(
                "INSERT INTO developers (name, age, sex) VALUES (?, ?, ?)"
        );

        selectDevelopers = connection.prepareStatement(
                "SELECT name, age, sex FROM developers WHERE name = ?"
        );

        geId = connection.prepareStatement(
                "SELECT id FROM developers WHERE name = ?");

        addToProjectDeveloper = connection.prepareStatement(
                "INSERT INTO developers_projects (developers_id, projects_id) VALUES (?, ?)");
    }

    public boolean createDevelopers(String name, int age, String sex) {
        try {
            insertDevelopers.setString(1, name);
            insertDevelopers.setInt(2, age);
            insertDevelopers.setString(3, sex);
            return insertDevelopers.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> selectDevelopersByName(String name) {
        try {
            selectDevelopers.setString(1, name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<String> result = new ArrayList<>();
        try(ResultSet resultSet = selectDevelopers.executeQuery()) {
            while (resultSet.next()) {
                result.add(resultSet.getString("name") + " " + resultSet.getInt("age") + " " + resultSet.getString("sex"));
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //TODO remake return
    public int getIdByName(String name) {
        try {
            geId.setString(1, name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int result = 0;
        try(ResultSet resultSet = geId.executeQuery()) {
            while (resultSet.next()) {
                result = resultSet.getInt("id");
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public void addToProjectDeveloper(String developer, String project) {
        try {
            addToProjectDeveloper.setString(1, developer);
            addToProjectDeveloper.setString(2, project);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
