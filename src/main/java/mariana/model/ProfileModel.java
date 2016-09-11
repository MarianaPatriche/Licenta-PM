package mariana.model;

/**
 * Created by mariana on 20.08.2016.
 */
public class ProfileModel {
    private Long id;
    private String newPassword;
    private String repeatPassword;
    private String oldPassword;
    private String picturePath;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "id=" + id +
                ", newPassword='" + newPassword + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
