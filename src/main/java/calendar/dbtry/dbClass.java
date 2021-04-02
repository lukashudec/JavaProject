package calendar.dbtry;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dbClass {
    String database;
    String user;
    String pwd;

    public dbClass(String database, String user, String pwd) {
        this.database = database;
        this.user = user;
        this.pwd = pwd;
    }

    public List<Object[]> executeGenP(String query) throws SQLException {
    return executeGen(query);
    }

    private List<Object[]> executeGen(String query) throws SQLException {
        ResultSet rs;
        List<Object[]> model = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:33066/"+database, user, pwd)) {
            rs=conn.prepareStatement(query).executeQuery();

            final int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = rs.getObject(i);
                }
                model.add(values);
            }
            return model;
        }
    }
}
