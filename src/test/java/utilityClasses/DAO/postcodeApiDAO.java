package utilityClasses.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class postcodeApiDAO extends baseDAO{

    public postcodeApiDAO() {
        super("postcodeApi", "root", "admin");
    }


    List<postcodeApiObject> execute(String query) throws SQLException {
       List<Object[]> result = super.executeQuery(query);
       List<postcodeApiObject> res = new ArrayList<>();
       for (Object[] o : result) {
           res.add(new postcodeApiObject(dataToInt(o[0]),dataToString(o[1]),dataToInt(o[2])));
       }
       return res;
    }

    public postcodeApiObject selectById(int id) throws SQLException {
        return execute("Select * from postcodeApi where id="+id).get(0);
    }

    public List<postcodeApiObject>  selectAll() throws SQLException {
        return execute("Select * from postcodeApi");
    }

}
