import java.sql.*;

/**
 * Created by NCL on 2016-03-25.
 */
public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker){ this.connectionMaker = connectionMaker;  }



    public User get(Long id) throws SQLException, ClassNotFoundException {
        //DB는? Mysql
        //위치는?
        //table은 userinfo
        //Load Driver
        Connection connection = connectionMaker.getConnection();
        //쿼리만들고
        String sql = "select * from userinfo where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        //실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과를 오브젝트에 매핑하고
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //매핑된결과를 리턴한다.
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        Connection connection = connectionMaker.getConnection();

        String sql = "insert into userinfo (name, password) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        Long id = getLastInsertId(connection);
        preparedStatement.close();
        connection.close();

        return id;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        return connectionMaker.getConnection();
    }

    private Long getLastInsertId(Connection connection) throws SQLException {
     PreparedStatement preparedStatement2 = connection.prepareStatement(
             "select last_insert_id()");

        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();
        return id;


    }

}
