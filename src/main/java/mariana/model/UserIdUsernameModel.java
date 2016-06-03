package mariana.model;

/**
 * Created by mariana on 02.06.2016.
 */
public class UserIdUsernameModel {

    private Long id;

    private String username;

    public UserIdUsernameModel(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
