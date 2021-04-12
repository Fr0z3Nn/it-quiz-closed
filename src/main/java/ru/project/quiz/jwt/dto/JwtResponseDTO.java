package ru.project.quiz.jwt.dto;

public class JwtResponseDTO {
    private final String jwttoken;

    public JwtResponseDTO(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
