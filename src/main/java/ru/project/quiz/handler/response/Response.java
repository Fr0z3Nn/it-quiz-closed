package ru.project.quiz.handler.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Базовая сущность для вывода эксепшенов")
public class Response {
    @Schema(description = "Сообщение об ошибке")
    private String message;

    public Response(String message) {
        this.message = message;
    }

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
