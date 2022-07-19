package data.queries;

import data.connection.Db;
import data.entity.Developers;
import data.entity.Skills;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsForDevelopers {
    Db db = Db.getInstance();

    private PreparedStatement insertDevelopers;
    private PreparedStatement selectDevelopers;
    private PreparedStatement insertDevelopersSkills;
    private PreparedStatement insertDeveloperToProjects;
    private PreparedStatement updateDevelopers;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement getAllDevelopersSt;


    public RequestsForDevelopers() throws SQLException {


        Connection connection = db.getConnection();

        insertDevelopers = connection.prepareStatement(
                "INSERT INTO developers (name, age, sex, salary) VALUES (?, ?, ?, ?)"
        );

        selectDevelopers = connection.prepareStatement(
                "SELECT id, name, age, sex, salary FROM developers WHERE name = ?"
        );

        insertDevelopersSkills = connection.prepareStatement(
                "INSERT INTO developers_skills (developers_id, skills_id) VALUES (?, ?)");

        insertDeveloperToProjects = connection.prepareStatement(
                "INSERT INTO developers_projects (developers_id, projects_id) VALUES (?, ?)");

        updateDevelopers = connection.prepareStatement(
                "UPDATE developers SET name = ?, age = ?, sex = ?, salary = ? WHERE id = ?"
        );

        deleteByIdSt = connection.prepareStatement(
                "DELETE FROM developers WHERE id = ?"
        );

        getAllDevelopersSt = connection.prepareStatement(
                "SELECT id, name, age, sex, salary FROM developers");
    }

    public List<Developers> getAllDevelopers() {
        try(ResultSet resultSet = getAllDevelopersSt.executeQuery()) {
            List<Developers> developersList = new ArrayList<>();
            while (resultSet.next()) {
                Developers developers = new Developers();
                developers.setId(resultSet.getInt("id"));
                developers.setName(resultSet.getString("name"));
                developers.setAge(resultSet.getInt("age"));
                developers.setSex(Developers.Sex.valueOf(resultSet.getString("sex")));
                developers.setSalary(resultSet.getInt("salary"));

                developersList.add(developers);
            }
            return developersList;
        }catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateDevelopersById(Developers developers) {
        Developers findDeveloper = selectDevelopersByName(developers.getName());

        try {
            if (developers.getName() == null) {
                updateDevelopers.setString(1, findDeveloper.getName());
            } else {
                updateDevelopers.setString(1, developers.getName());
            }

            if (developers.getAge() == 0) {
                updateDevelopers.setInt(2, findDeveloper.getAge());
            } else {
                updateDevelopers.setInt(2, developers.getAge());
            }

            if (developers.getSex() == null) {
                updateDevelopers.setString(3, findDeveloper.getSex().toString());
            } else {
                updateDevelopers.setString(3, developers.getSex().toString());
            }

            if (developers.getSalary() == 0) {
                updateDevelopers.setInt(4, findDeveloper.getSalary());
            } else {
                updateDevelopers.setInt(4, developers.getSalary());
            }

            if (developers.getId() == 0) {
                updateDevelopers.setInt(5, findDeveloper.getId());
            }else {
                updateDevelopers.setInt(5, developers.getId());
            }

            updateDevelopers.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean createDeveloper(Developers developers) {
        try {
            insertDevelopers.setString(1, developers.getName());
            insertDevelopers.setInt(2, developers.getAge());
            insertDevelopers.setString(3, developers.getSex().toString());
            insertDevelopers.setInt(4, developers.getSalary());
            return insertDevelopers.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createDevelopersFromList(List<Developers> developers) {
        try {
            for (Developers developer : developers) {
                insertDevelopers.setString(1, developer.getName());
                insertDevelopers.setInt(2, developer.getAge());
                insertDevelopers.setString(3, developer.getSex().toString());
                insertDevelopers.setInt(4, developer.getSalary());
                insertDevelopers.addBatch();
            }
            insertDevelopers.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Developers selectDevelopersByName(String name) {
        try {
            selectDevelopers.setString(1, name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try (ResultSet resultSet = selectDevelopers.executeQuery()) {
            Developers developers = new Developers();
            while (resultSet.next()) {
                developers.setId(resultSet.getInt("id"));
                developers.setName(resultSet.getString("name"));
                developers.setAge(resultSet.getInt("age"));
                developers.setSex(Developers.Sex.valueOf(resultSet.getString("sex")));
                developers.setSalary(resultSet.getInt("salary"));
            }
            return developers;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public void addDevelopersSkills(Developers developer, Skills skills) {
        try {
            insertDevelopersSkills.setInt(1, developer.getId());
            insertDevelopersSkills.setInt(2, skills.getId());
            insertDevelopersSkills.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public void addDeveloperToProjects(int[] developer, int[] projects) {
        try {
            for (int i = 0; i < developer.length; i++) {
                insertDeveloperToProjects.setInt(1, developer[i]);
                insertDeveloperToProjects.setInt(2, projects[i]);
                insertDeveloperToProjects.addBatch();
            }
            insertDeveloperToProjects.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteById(int id) throws SQLException {
        deleteByIdSt.setInt(1, id);
        deleteByIdSt.executeUpdate();
    }

//    public void addDeveloperToProjects(Developers developer, Projects projects) {
//        try {
//            insertDeveloperToProjects.setInt(1, developer.getId());
//            insertDeveloperToProjects.setInt(2, projects.getId());
//            insertDeveloperToProjects.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

}
