package ru.project.quiz.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность пользователя")
public class ITUserDTO {
    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Schema(description = "Имя пользователя")
    String username;

    @Schema(description = "Пароль")
    String password;
}
