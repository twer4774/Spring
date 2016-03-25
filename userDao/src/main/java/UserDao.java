import java.sql.*;

/**
 * Created by NCL on 2016-03-25.
 */
public class UserDao {
    public User get(Long id) throws SQLException, ClassNotFoundException {
        //DB는? Mysql
        //위치는?
        //table은 userinfo
        //Load Driver
        Class.forName("com.mysql.jdbc.Driver");
        //Connection 맺고
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userinfo","ncl","1234");
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
}
