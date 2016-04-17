
import java.sql.*;

/**
 * Created by NCL on 2016-04-10.
 */
public class UserDao {
    private JdbcContext jdbcContext;


    public User get(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = (Connection connection) -> {
            String sql = "select * from userinfo where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForQuery(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy= (Connection connection) -> {
          String sql = "insert into userinfo (name, password) values (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from userinfo where id = ?";
        Object[] objs = new Object[] {id};
        jdbcContext.update(sql, objs);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] objs = new Object[] {user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, objs);
    }

    public void setJdbcContext(JdbcContext jdbcContext){
        this.jdbcContext = jdbcContext;
    }
}
