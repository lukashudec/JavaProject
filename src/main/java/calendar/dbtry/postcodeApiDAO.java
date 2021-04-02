package calendar.dbtry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class postcodeApiDAO extends dbClass{

    public postcodeApiDAO() {
        super("postcodeApi", "root", "admin");
    }

    private List<postcodeApiObject> execute(String query) throws SQLException {
        ResultSet rs;
        List<postcodeApiObject> result = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:33066/"+database, user, pwd)) {
            rs=conn.prepareStatement(query).executeQuery();

            while (rs.next()) {
                result.add(new postcodeApiObject(rs.getInt("id"),
                        rs.getString("text"),rs.getInt("number")));
            }
            return result;
        }
    }

    public postcodeApiObject selectById(int id) throws SQLException {
        return execute("Select * from postcodeApi where id="+id).get(0);
    }

    public List<postcodeApiObject>  selectAll() throws SQLException {
        return execute("Select * from postcodeApi");
    }

}
