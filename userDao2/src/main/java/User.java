/**
 * Created by NCL on 2016-04-10.
 */
public class User {

    //유저를 위한 변수
    private Long id;
    private String name;
    private String password;

    //get set 정의
    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
