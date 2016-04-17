import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
   private ConnectionMaker connectionMaker;

    public JdbcContext() {
    }

    User jdbcContextWithStatementStrategyForQuery(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;

        try {
            connection = connectionMaker.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
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
            connection = connectionMaker.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();

            id = getLastInsertId(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
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
            connection = connectionMaker.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
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

    Long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();

        return id;
    }

    public void setConnectionMaker(SimpleConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

}