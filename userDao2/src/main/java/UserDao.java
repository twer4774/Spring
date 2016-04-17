
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;

/**
 * Created by NCL on 2016-04-10.
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;


    public User get(Long id)  {
        String sql = "select * from userinfo where id = ?";
        User user = null;
        try{
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<User>(){
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            });
        } catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return user;
    }

    public Long add(User user)  {
        String sql = "insert into userinfo (name, password) values (?,?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                return preparedStatement;
            }
        }, generatedKeyHolder);
        return (Long) generatedKeyHolder.getKey();
    }

    public void delete(Long id)  {
        String sql = "delete from userinfo where id = ?";
        Object[] objs = new Object[] {id};
        jdbcTemplate.update(sql, objs);
    }

    public void update(User user)  {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] objs = new Object[] {user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, objs);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
