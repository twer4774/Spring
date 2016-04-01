/**
 * Created by NCL on 2016-03-25.
 */
public class User {
    private Long id;
    private String name;
    private String password;

    public Long getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
}
