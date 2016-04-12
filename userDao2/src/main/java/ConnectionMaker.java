import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by NCL on 2016-04-11.
 */
    public interface ConnectionMaker {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}