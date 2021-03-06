import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by NCL on 2016-03-25.
 */
public class userDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        //UserDao userDao = new UserDao(new SimpleConnectionMaker());
        UserDao userDao = new DaoFactory().getUserDao();

        Long id = 1L;
        String name = "조원익";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "원익";
        String password = "222";

        user.setName(name);
        user.setPassword(password);
        UserDao userDao = new DaoFactory().getUserDao();
        Long id = userDao.add(user);

        User resultUser = userDao.get(id);

        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }


}
