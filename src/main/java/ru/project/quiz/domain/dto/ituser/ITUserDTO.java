package ru.project.quiz.domain.dto.ituser;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "Сущность пользователя")
public class ITUserDTO implements UserDetails {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Пароль")
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    @Email
    @NotBlank
    private String email;

    @Schema(description = "roles", accessMode = Schema.AccessMode.READ_ONLY)
    private Set<RoleDTO> roles;

    @Schema(description = "Authorities", accessMode = Schema.AccessMode.READ_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                .flatMap(roleDTO -> roleDTO.getPermissions().stream())
                .map(permissionType -> new SimpleGrantedAuthority(permissionType.name()))
                .collect(Collectors.toSet());
    }

    @Schema(description = "expired", accessMode = Schema.AccessMode.READ_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Schema(description = "locked", accessMode = Schema.AccessMode.READ_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Schema(description = "non_expired", accessMode = Schema.AccessMode.READ_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Schema(description = "is_enabled", accessMode = Schema.AccessMode.READ_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public ITUserDTO(String username, String password, String email, Set<RoleDTO> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public ITUserDTO() {
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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
