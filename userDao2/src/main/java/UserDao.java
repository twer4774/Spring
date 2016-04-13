import java.sql.*;

/**
 * Created by NCL on 2016-04-10.
 */
public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;

        try{
            connection = connectionMaker.getConnection();

            StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();
           if (resultSet.next()) {
               user = new User();
               user.setId(resultSet.getLong("id"));
               user.setName(resultSet.getString("name"));
               user.setPassword(resultSet.getString("password"));
           }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }

        if(preparedStatement != null){
            try{
                preparedStatement.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(connection != null){
            try{
                connection.close();
            } catch(SQLException e){

            }
        }

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long id = null;

        try {
            connection=connectionMaker.getConnection();

            StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();

            id = getLastInsertId(connection);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        } catch(SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                } catch(SQLException e){

                }
            }
        }
        return id;
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new UpdateUserStatementStrategy(user);
        jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }


    private void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection=connectionMaker.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        } catch(SQLException e){
            e.printStackTrace();
            throw e;
        } finally {
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                } catch(SQLException e){

                }
            }
        }
    }

    private Long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();

        return id;
    }
}
