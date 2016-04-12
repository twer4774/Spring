import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by NCL on 2016-04-12.
 */
public class SimpleConnectionMaker implements ConnectionMaker {
    public SimpleConnectionMaker(){

    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/userinfo?characterEncoding=utf8","ncl","1234");
    }

}
