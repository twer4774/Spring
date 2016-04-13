import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by NCL on 2016-04-13.
 */
public interface StatementStrategy {
    PreparedStatement makeStatement(Connection connection) throws SQLException;
}
