package ru.project.quiz.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.project.quiz.enums.Role;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String name;
    private boolean active;
    private String googleName;
    private String googleUsername;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return getRoles();
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive() == user.isActive() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getGoogleName(), user.getGoogleName()) &&
                Objects.equals(getGoogleUsername(), user.getGoogleUsername()) &&
                Objects.equals(getRoles(), user.getRoles());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getUsername(), getPassword(), getName(), isActive(), getGoogleName(), getGoogleUsername(), getRoles());
    }
}
