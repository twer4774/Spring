import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
   private DataSource dataSource;

    public JdbcContext() {
    }

    User jdbcContextWithStatementStrategyForQuery(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
        return user;
    }

    Long jdbcContextWithStatementStrategyForInsert(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long id = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();

            id = getLastInsertId(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {

                }
            }
        }
        return id;
    }

    void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void update(String sql, Object[] objs) throws SQLException, ClassNotFoundException {
        StatementStrategy statementsStrategy = (Connection connection) ->{
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i=1; i<=objs.length; ++i){
                preparedStatement.setObject(i, objs[i-1]);
            }
            return preparedStatement;
        };
        jdbcContextWithStatementStrategyForUpdate(statementsStrategy);
    }

    Long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();

        return id;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}