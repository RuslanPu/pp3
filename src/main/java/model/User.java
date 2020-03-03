package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name="User")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public User() {};
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public User(Long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPass(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPass() { return password; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }


}
