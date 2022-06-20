package data.queries.hw;

import data.connection.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HwQuery {
    private PreparedStatement salaryDevelopers;
    private PreparedStatement listOfProjectDevelopers;
    private PreparedStatement devProgramLanguage;
    private PreparedStatement devSKillLevel;
    private PreparedStatement listProjects;

    public HwQuery(Db db) throws SQLException {
        Connection connection = db.getConnection();
        salaryDevelopers = connection.prepareStatement(
                "SELECT project_name , sum(salary) AS salarySum FROM developers JOIN developers_projects ON developers.id = developers_projects.developers_id JOIN projects ON projects_id = projects.id WHERE project_name = ? GROUP BY project_name"
        );
        listOfProjectDevelopers = connection.prepareStatement(
                "SELECT name , project_name FROM developers JOIN developers_projects ON developers.id = developers_projects.developers_id JOIN projects ON projects_id = projects.id WHERE project_name = ?"
        );
        devProgramLanguage = connection.prepareStatement(
                "SELECT name, positions FROM developers_skills JOIN developers ON developers_skills.developers_id = developers.id JOIN skills ON developers_skills.skills_id= skills.id WHERE positions = ?"
        );
        devSKillLevel = connection.prepareStatement(
                "SELECT name, skill_level FROM developers_skills JOIN developers ON developers_skills.developers_id = developers.id JOIN skills ON developers_skills.skills_id= skills.id WHERE skill_level = ?"
        );

        listProjects = connection.prepareStatement(
                "SELECT date_creation, project_name, count(name) AS count_developers FROM developers JOIN developers_projects ON developers.id = developers_projects.developers_id JOIN projects ON projects_id = projects.id GROUP BY date_creation, project_name "
        );
    }

    public void getSalary(String projects) throws SQLException {
        salaryDevelopers.setString(1, projects);

        ResultSet resultSet = salaryDevelopers.executeQuery();
        resultSet.next();
        String project_name = resultSet.getString("project_name");
        String salarySum = resultSet.getString("salarySum");

        System.out.println(project_name + " " + salarySum);
    }

    public void getDevelopersBYProject(String projects){
        try {
            listOfProjectDevelopers.setString(1, projects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ResultSet resultSet = listOfProjectDevelopers.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String project_name = resultSet.getString("project_name");
                System.out.println(name + " " + project_name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getDevelopersByProgrammingLanguage(String skills) {
        try {
            devProgramLanguage.setString(1, skills);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(ResultSet resultSet = devProgramLanguage.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String positions = resultSet.getString("positions");
                System.out.println(name + " " + positions);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getDevelopersBySkillLevel(String skillLevel) {
        try {
            devSKillLevel.setString(1, skillLevel);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try(ResultSet resultSet = devSKillLevel.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String skill_level = resultSet.getString("skill_level");
                System.out.println(name + " " + skill_level);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getListProjects() {
        try(ResultSet resultSet = listProjects.executeQuery()) {
            while (resultSet.next()) {
                String date_creation = resultSet.getString("date_creation");
                String project_name = resultSet.getString("project_name");
                int count_developers = resultSet.getInt("count_developers");
                System.out.println(date_creation + " " + project_name + " " + count_developers);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

}
