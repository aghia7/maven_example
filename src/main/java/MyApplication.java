import models.User;

import java.sql.*;
import java.util.ArrayList;

public class MyApplication {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        ArrayList<User> users = new ArrayList<>();
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionString, "postgres", "0000");

            String sql = "SELECT id, name, surname, gender FROM users ORDER BY id;";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                boolean gender = rs.getBoolean("gender");

                User user = new User(id, name, surname, gender);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("driver error: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("could not close the connection: " + e.getMessage());
                }
            }
        }

        for (User user : users) {
            System.out.println(user);
        }
    }
}
