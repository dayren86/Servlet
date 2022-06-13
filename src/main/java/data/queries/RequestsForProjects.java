package data.queries;

import data.connection.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsForProjects {
    private PreparedStatement insertProjects;
    private PreparedStatement selectProjects;

    public RequestsForProjects(Db db) throws SQLException {
        Connection connection = db.getConnection();
        insertProjects = connection.prepareStatement(
                "INSERT INTO projects (project_name, project_description) VALUES (?, ?)"
        );

        selectProjects = connection.prepareStatement("" +
                "SELECT project_name, project_description FROM projects WHERE project_name = ?"
        );
    }

    public boolean createProjects(String projectName, String projectDescription) {
        try {
            insertProjects.setString(1, projectName);
            insertProjects.setString(2, projectDescription);
            return insertProjects.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> selectProjectsByProjectName(String projectName) {
        try {
           selectProjects.setString(1, projectName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<String> result = new ArrayList<>();
        try (ResultSet resultSet = selectProjects.executeQuery()){
            while (resultSet.next()) {
                result.add(resultSet.getString("project_name") + " " + resultSet.getString("project_description"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
