package utilityClasses.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostcodeApiDAO extends BaseDAO {

    public PostcodeApiDAO() {
        super("postcodeApi", "root", "admin");
    }

    List<PostcodeApiObject> execute(String query) throws SQLException {
       List<Object[]> result = super.executeQuery(query);
       List<PostcodeApiObject> res = new ArrayList<>();
       for (Object[] o : result) {
           res.add(new PostcodeApiObject(dataToInt(o[0]),dataToString(o[1]),dataToInt(o[2])));
       }
       return res;
    }

    public PostcodeApiObject selectById(int id) throws SQLException {
        return execute("Select id, text, number from postcodeApi where id="+id).get(0);
    }

    public List<PostcodeApiObject>  selectAll() throws SQLException {
        return execute("Select id, text, number from postcodeApi");
    }

}
