package ru.project.quiz.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность пользователя")
public class ITUserDTO implements UserDetails {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Schema(description = "Имя пользователя")
    String username;

    @Schema(description = "Пароль")
    @NotBlank(message = "Пароль не должен быть пустым")
    String password;

    @Email
    @NotBlank
    String email;

    private Set<RoleDTO> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                .map(roleDTO -> new SimpleGrantedAuthority(roleDTO.getRole().name()))
                .collect(Collectors.toList());
        //test
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
