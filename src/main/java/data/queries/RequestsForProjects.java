package data.queries;

import data.connection.Db;
import data.entity.Developers;
import data.entity.Projects;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestsForProjects {
    private PreparedStatement insertProjectsSt;
    private PreparedStatement selectProjectsSt;
    private PreparedStatement deleteProjectByIdSt;
    private PreparedStatement updateProjectSt;

    public RequestsForProjects(Db db) throws SQLException {
        Connection connection = db.getConnection();
        insertProjectsSt = connection.prepareStatement(
                "INSERT INTO projects (project_name, project_description, date_creation) VALUES (?, ?, ?)"
        );

        selectProjectsSt = connection.prepareStatement("" +
                "SELECT id, project_name, project_description, date_creation FROM projects WHERE project_name = ?"
        );

        deleteProjectByIdSt = connection.prepareStatement(
                "DELETE FROM projects WHERE id = ?"
        );
        updateProjectSt = connection.prepareStatement(
                "UPDATE projects SET project_name = ?, project_description = ?, date_creation = ? WHERE id = ?"
        );
    }

    public boolean createProjects(String projectName, String projectDescription, LocalDate localDate) {
        try {
            insertProjectsSt.setString(1, projectName);
            insertProjectsSt.setString(2, projectDescription);
            insertProjectsSt.setDate(3, Date.valueOf(localDate));
            return insertProjectsSt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createProjectsFromList(List<Projects> listProjects) {
        try {
            for (Projects listProject : listProjects) {
                insertProjectsSt.setString(1, listProject.getProjectName());
                insertProjectsSt.setString(2, listProject.getProjectDescription());
                insertProjectsSt.setDate(3, Date.valueOf(listProject.getDateCreation()));
                insertProjectsSt.addBatch();
            }
            insertProjectsSt.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Projects selectProjectsByProjectName(String projectName) {
        try {
            selectProjectsSt.setString(1, projectName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Projects projects = new Projects();
        try (ResultSet resultSet = selectProjectsSt.executeQuery()) {
            resultSet.next();
            projects.setId(resultSet.getInt("id"));
            projects.setProjectName(resultSet.getString("project_name"));
            projects.setProjectDescription(resultSet.getString("project_description"));
            projects.setDateCreation(LocalDate.parse(resultSet.getString("date_creation")));
            return projects;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteProjectsById(int id) throws SQLException {
        deleteProjectByIdSt.setInt(1, id);
        deleteProjectByIdSt.executeUpdate();
    }


    public void updateProjects(int id, Projects projects) {
        try {
            updateProjectSt.setString(1, projects.getProjectName());
            updateProjectSt.setString(2, projects.getProjectDescription());
            updateProjectSt.setDate(3, Date.valueOf(projects.getDateCreation()));
            updateProjectSt.setInt(4, id);
            updateProjectSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
