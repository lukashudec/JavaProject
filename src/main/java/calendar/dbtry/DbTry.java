package calendar.dbtry;

import java.sql.*;
import java.util.List;

public class DbTry {

    public static void main(String[] args) throws SQLException {

      /*  try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:33066/postcodeApi", "root", "admin")) {

            ResultSet rs = conn.prepareStatement("select text from postcodeApi").executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("text"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/

        postcodeApiDAO pc = new postcodeApiDAO();
        pc.selectById(1).toConsole();

        System.out.println(" BREAK ");

        pc.selectAll().forEach(postcodeApiObject::toConsole);

        System.out.println(" BREAK ");

        pc.executeGenP("Select * from postcodeApi").forEach(o -> new postcodeApiObject(Integer.parseInt(o[0].toString()),
                o[1].toString(),
                Integer.parseInt(o[2].toString())).toConsole());
    }
}
