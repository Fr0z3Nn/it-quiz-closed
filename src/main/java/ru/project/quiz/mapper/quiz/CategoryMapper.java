package ru.project.quiz.mapper.quiz;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.quiz.CategoryDTO;
import ru.project.quiz.domain.entity.quiz.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category CategoryFromCategoryDTO(CategoryDTO categoryDTO);
    CategoryDTO CategoryDTOFromCategory(Category category);
}
