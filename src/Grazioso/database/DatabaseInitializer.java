package Grazioso.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            String createDogsTable = """
                CREATE TABLE IF NOT EXISTS dogs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT UNIQUE,
                    breed TEXT,
                    gender TEXT,
                    age INTEGER,
                    weight REAL,
                    acquisitionDate TEXT,
                    acquisitionLocation TEXT,
                    trainingStatus TEXT,
                    reserved INTEGER,
                    serviceLocation TEXT
                );
            """;

            String createMonkeysTable = """
                CREATE TABLE IF NOT EXISTS monkeys (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT UNIQUE,
                    species TEXT,
                    gender TEXT,
                    age INTEGER,
                    weight REAL,
                    tailLength REAL,
                    height REAL,
                    bodyLength REAL,
                    acquisitionDate TEXT,
                    acquisitionLocation TEXT,
                    trainingStatus TEXT,
                    reserved INTEGER,
                    serviceLocation TEXT
                );
            """;

            stmt.execute(createDogsTable);
            stmt.execute(createMonkeysTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
