import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by NCL on 2016-04-10.
 */
public class UserDao2Test {
//    @Test
//    public void get() throws SQLException, ClassNotFoundException {
//        UserDao userDao = new UserDao();
//
//        Long id = 1L;
//        String name = "조원익";
//        String password = "1234";
//
//        User user = userDao.get(id);
//
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//
//    }
//
//    @Test
//    public void add() throws SQLException, ClassNotFoundException {
//        User user = new User();
//
//        String name = "원익";
//        String password = "2222";
//
//        user.setName(name);
//        user.setPassword(password);
//
//        UserDao userDao = new UserDao();
//        Long id = userDao.add(user);
//
//       User resultUser = userDao.get(id);
//
//        assertThat(resultUser.getId(), is(id));
//        assertThat(resultUser.getName(), is(name));
//        assertThat(resultUser.getPassword(), is(password));
//
//    }
    /* jeju와 halla를 위한 get, add */
    @Test
    public void getForJeju() throws SQLException, ClassNotFoundException {
        UserDao userDao = new JejuUserDao();

        Long id = 1L;
        String name = "조원익";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void addForJeju() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "원익";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new JejuUserDao();
        Long id = userDao.add(user);

       User resultUser = userDao.get(id);

        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));

    }
    @Test
    public void getForHalla() throws SQLException, ClassNotFoundException {
        UserDao userDao = new HallaUserDao();

        Long id = 1L;
        String name = "조원익";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void addForHalla() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "원익";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new HallaUserDao();
        Long id = userDao.add(user);

       User resultUser = userDao.get(id);

        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));

    }
}
