import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by NCL on 2016-04-10.
 */
public class UserDao2Test {
    private UserDao userDao;

    @Before
    public void setUp(){
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext applicationContext = new GenericXmlApplicationContext("daoFactory.xml");
        //userDao = new DaoFactory().userDao();
        userDao = (UserDao) applicationContext.getBean("userDao");
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Long id = 1L;
        String name = "조원익";
        String password = "1234";

        User user = userDao.get(id);

        validate(id, name, password, user);


    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "원익";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        User resultUser = userDao.get(id);

        validate(id, name, password, resultUser);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "원익";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        userDao.delete(id);

        User resultUser = userDao.get(id);
        assertThat(resultUser, nullValue());
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "원익";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        name = "조원익";
        password = "1111";

        user.setName(name);
        user.setPassword(password);
        user.setId(id);

        userDao.update(user);

        User resultUser = userDao.get(id);
        validate(id, name, password, resultUser);
    }

    private void validate(Long id, String name, String password, User user) {
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
