package data.queries;

import data.connection.Db;
import data.entity.Skills;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestsForSkills {
    private PreparedStatement insertSkillsSt;
    private PreparedStatement selectSkillsSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement updateSkillsSt;


    public RequestsForSkills(Db db) throws SQLException {
        Connection connection = db.getConnection();

        insertSkillsSt = connection.prepareStatement(
                "INSERT INTO skills (positions, skill_level) VALUES (?, ?)"
        );

        selectSkillsSt = connection.prepareStatement(
                "SELECT id, positions, skill_level FROM skills WHERE positions LIKE ? AND skill_level LIKE ?"
        );
        deleteByIdSt = connection.prepareStatement(
                "DELETE FROM skills WHERE id = ?"
        );
        updateSkillsSt = connection.prepareStatement(
                "UPDATE skills SET positions = ?, skill_level = ? WHERE id = ?"
        );
    }

    public boolean createSkills(Skills skills) {
        try {
            insertSkillsSt.setString(1, skills.getPosition().toString());
            insertSkillsSt.setString(2, skills.getSkillLevel().toString());
            return insertSkillsSt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createSkillsFromList(List<Skills> skillsList) {
        try {
            for (Skills skills : skillsList) {
                insertSkillsSt.setString(1, skills.getPosition().toString());
                insertSkillsSt.setString(2, skills.getSkillLevel().toString());
                insertSkillsSt.addBatch();
            }
            insertSkillsSt.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Skills selectSkillsByPosition(String position, String skill_level) {
        try {
            selectSkillsSt.setString(1, position);
            selectSkillsSt.setString(2, skill_level);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet resultSet = selectSkillsSt.executeQuery()) {
            Skills skills = new Skills();
            while (resultSet.next()) {
                skills.setId(resultSet.getInt("id"));
                skills.setPosition(Skills.Position.valueOf(resultSet.getString("positions")));
                skills.setSkillLevel(Skills.SkillLevel.valueOf(resultSet.getString("skill_level")));
            }
            return skills;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteSkillsById(int id) throws SQLException {
        deleteByIdSt.setInt(1, id);
        deleteByIdSt.executeUpdate();
    }

    public void updateSkills(int id, Skills skills) {
        try {
            updateSkillsSt.setString(1, String.valueOf(skills.getPosition()));
            updateSkillsSt.setString(2, String.valueOf(skills.getSkillLevel()));
            updateSkillsSt.setInt(3, id);
            updateSkillsSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
