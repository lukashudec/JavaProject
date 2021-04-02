package calendar.dbtry;

import java.sql.*;

public class DbTry {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:33066/postcodeApi", "root", "admin")) {

            ResultSet rs = conn.prepareStatement("select text from postcodeApi").executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("text"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
}
