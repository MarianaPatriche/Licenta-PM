package mariana.entity;

import javax.persistence.*;

/**
 * Created by mariana on 11.09.2016.
 */
@Entity
@Table(name = "USER_ADMIN")
public class UserAdmin {
        @Id
        @GeneratedValue(generator = "USER_ADMIN_SEQ_GEN", strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(name = "USER_ADMIN_SEQ_GEN", sequenceName = "USER_ADMIN_SEQ")
        private Long id;

        @Column(name = "username", nullable = false)
        private String username;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "enabled")
        private Boolean enabled;

        @Column(name = "picture")
        private String picture;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public UserAdmin(String username, String password, Boolean enabled) {
            this.password = password;
            this.username = username;
            this.enabled = enabled;
        }

        public UserAdmin() {
        }
}
