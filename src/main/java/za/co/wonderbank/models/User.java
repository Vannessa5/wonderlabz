package za.co.wonderbank.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Getter
@Setter

@Entity
@Table(name = "user")
public class User  extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String username;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> authorities = new ArrayList<>();
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    @Column(name = "failed_attempt")
    private Short failedAttempt;

    @Column(name = "user_locked")
    private Boolean userLocked;

    public User() {
    }

    public User(Long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public User(String password, String username, List<Role> authorities) {
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    public User(String password, String username, LocalDateTime lastLoginDate) {
        this.password = password;
        this.username = username;
        this.lastLoginDate = lastLoginDate;
    }
}
