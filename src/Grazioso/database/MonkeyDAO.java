package Grazioso.database;

import Grazioso.Animals.Monkey;
import Grazioso.Animals.TrainingStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonkeyDAO {

    public void insertMonkey(Monkey monkey) {
        String sql = "INSERT INTO monkeys (name, species, gender, age, weight, tailLength, height, bodyLength, acquisitionDate, acquisitionLocation, trainingStatus, reserved, serviceLocation) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, monkey.getName());
            stmt.setString(2, monkey.getSpecies());
            stmt.setString(3, monkey.getGender());
            stmt.setInt(4, monkey.getAge());
            stmt.setDouble(5, monkey.getWeight());
            stmt.setDouble(6, monkey.getTailLength());
            stmt.setDouble(7, monkey.getHeight());
            stmt.setDouble(8, monkey.getBodyLength());
            stmt.setString(9, monkey.getAcquisitionDate().toString());
            stmt.setString(10, monkey.getAcquisitionLocation());
            stmt.setString(11, monkey.getTrainingStatus().toString());
            stmt.setInt(12, monkey.getReserved() ? 1 : 0);
            stmt.setString(13, monkey.getServiceLocation());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public List<Monkey> getAllMonkeys() {
        List<Monkey> monkeys = new ArrayList<>();
        String sql = "SELECT * FROM monkeys";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Monkey monkey = new Monkey(
                        rs.getString("name"),
                        rs.getString("species"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getDouble("weight"),
                        rs.getDouble("tailLength"),
                        rs.getDouble("height"),
                        rs.getDouble("bodyLength"),
                        LocalDate.parse(rs.getString("acquisitionDate")),
                        rs.getString("acquisitionLocation"),
                        TrainingStatus.fromString(rs.getString("trainingStatus")),
                        rs.getInt("reserved") == 1,
                        rs.getString("serviceLocation")
                );
                monkeys.add(monkey);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monkeys;
    }
    
    public void updateReserved(String monkeyName, boolean reserved) {
        String sql = "UPDATE monkeys SET reserved = ? WHERE name = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserved ? 1 : 0);
            stmt.setString(2, monkeyName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
