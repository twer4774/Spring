
import java.sql.*;

/**
 * Created by NCL on 2016-04-10.
 */
public class UserDao {
    private JdbcContext jdbcContext;


    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementStrategyForQuery(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new UpdateUserStatementStrategy(user);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void setJdbcContext(JdbcContext jdbcContext){
        this.jdbcContext = jdbcContext;
    }
}
