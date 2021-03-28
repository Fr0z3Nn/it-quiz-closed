package ru.project.quiz.handler.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Базовая сущность для вывода эксепшенов")
public class Response {
    @Schema(description = "Сообщение об ошибке")
    String message;
}
