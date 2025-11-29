package Grazioso.database;

import Grazioso.Animals.Dog;
import Grazioso.Animals.TrainingStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DogDAO {

	// Insert dog into database
    public void insertDog(Dog dog) {
        String sql = "INSERT INTO dogs (name, breed, gender, age, weight, acquisitionDate, acquisitionLocation, trainingStatus, reserved, serviceLocation) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dog.getName());
            stmt.setString(2, dog.getBreed());
            stmt.setString(3, dog.getGender());
            stmt.setInt(4, dog.getAge());
            stmt.setDouble(5, dog.getWeight());
            stmt.setString(6, dog.getAcquisitionDate().toString());
            stmt.setString(7, dog.getAcquisitionLocation());
            stmt.setString(8, dog.getTrainingStatus().toString());
            stmt.setInt(9, dog.getReserved() ? 1 : 0);
            stmt.setString(10, dog.getServiceLocation());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    // Retrieve all dogs
    public List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        String sql = "SELECT * FROM dogs";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dog dog = new Dog(
                        rs.getString("name"),
                        rs.getString("breed"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getDouble("weight"),
                        LocalDate.parse(rs.getString("acquisitionDate")),
                        rs.getString("acquisitionLocation"),
                        TrainingStatus.fromString(rs.getString("trainingStatus")),
                        rs.getInt("reserved") == 1,
                        rs.getString("serviceLocation")
                );
                dogs.add(dog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dogs;
    }
    
    // Update reservation for dogs
    public void updateReserved(String dogName, boolean reserved) {
        String sql = "UPDATE dogs SET reserved = ? WHERE name = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserved ? 1 : 0);
            stmt.setString(2, dogName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
