package aaa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static void main(String[] args) throws SQLException {

        String urlconnection = "jdbc:mysql://localhost/digital_innovation_one";

        try (Connection conn = DriverManager.getConnection(urlconnection,"root", "12345")) {

            System.out.println("SUCESSO!");

        } catch (Exception e) {

            System.out.println("FALHA!");

        }
    }
}






