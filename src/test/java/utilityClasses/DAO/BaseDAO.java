package utilityClasses.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO {
    String database;
    String user;
    String pwd;

    public BaseDAO(String database, String user, String pwd) {
        this.database = database;
        this.user = user;
        this.pwd = pwd;
    }


    List<Object[]> executeQuery(String query) throws SQLException {
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

    public static List<String[]> freeExecuteQuery(String db, String user, String pwd, String query) throws SQLException{
        ResultSet rs;
        List<String[]> model = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:33066/"+db, user, pwd)) {
            rs=conn.prepareStatement(query).executeQuery();

            final int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String[] values = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = rs.getString(i);
                }
                model.add(values);
            }
            return model;
        }
    }

    protected String dataToString(Object o) {
        return o.toString();
    }

    protected int dataToInt(Object o) {
        return Integer.parseInt(o.toString());
    }
}
