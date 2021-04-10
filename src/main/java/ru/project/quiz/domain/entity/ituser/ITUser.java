package ru.project.quiz.domain.entity.ituser;

import ru.project.quiz.domain.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class ITUser extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "roles_name", referencedColumnName = "name"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")

    })
    private Set<Role> roles;

    public ITUser(String username, String password, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public ITUser() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ITUser itUser = (ITUser) o;
        return Objects.equals(username, itUser.username) && Objects.equals(password, itUser.password) && Objects.equals(email, itUser.email) && Objects.equals(roles, itUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, roles);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

