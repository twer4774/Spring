import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by NCL on 2016-04-13.
 */
public class AddUserStatementStrategy implements StatementStrategy{
    private User user;
    public AddUserStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        String sql = "insert into userinfo (name, password) values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement;
    }
}
