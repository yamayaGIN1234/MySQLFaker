package test;
import javafaker.test.Faker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Seeder {
    public static void main(String[] args) {
        Faker faker = new Faker();
        String url = "jdbc:mysql://localhost:3306/TestDB"; // Thay bằng thông tin DB của bạn
        String username = "root"; // Thay bằng username của DB
        String password = ""; // Thay bằng password của DB

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO users (email, first_name, last_name, address, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            for (int i = 0; i < 10; i++) {
                stmt.setString(1, faker.randomEmail());
                stmt.setString(2, faker.randomFirstName());
                stmt.setString(3, faker.randomLastName());
                stmt.setString(4, faker.randomAddress());
                stmt.setString(5, faker.randomPhoneNumber());
                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("Dữ liệu nhập thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
