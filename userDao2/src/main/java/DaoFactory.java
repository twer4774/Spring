/**
 * Created by NCL on 2016-04-12.
 */
public class DaoFactory {
    public UserDao getUserDao(){
        return new UserDao(new SimpleConnectionMaker());
    }
}
