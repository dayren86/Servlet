package data.queries;

import data.connection.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RequestsForSkills {
    private PreparedStatement insertSkills;
    private PreparedStatement selectSkills;


    public RequestsForSkills(Db db) throws SQLException {
        Connection connection = db.getConnection();

        insertSkills = connection.prepareStatement(
                "INSERT INTO skills (positions, skill_level) VALUES (?, ?)"
        );

        selectSkills = connection.prepareStatement(
                "SELECT positions, skill_level FROM skills WHERE positions = ?"
        );
    }

    public boolean createSkills(String position, String skill_level) {
        try {
            insertSkills.setString(1, position);
            insertSkills.setString(2, skill_level);
            return insertSkills.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> selectSkillsByPosition(String position) {
        try {
            selectSkills.setString(1, position);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> result = new ArrayList<>();

        try (ResultSet resultSet = selectSkills.executeQuery()) {
            while (resultSet.next()) {
                result.add(resultSet.getString("positions") + " " + resultSet.getString("skill_level"));
            }
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
