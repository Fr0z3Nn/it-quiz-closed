package ru.project.quiz.domain.dto.quiz;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    @Schema(description = "ID", accessMode = Schema.AccessMode.READ_ONLY)
    long id;

    String name;

    boolean isCorrectAnswer;
}
