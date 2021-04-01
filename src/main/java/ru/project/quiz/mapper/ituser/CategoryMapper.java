package ru.project.quiz.mapper.ituser;

import org.mapstruct.Mapper;
import ru.project.quiz.domain.dto.ituser.CategoryDTO;
import ru.project.quiz.domain.entity.ituser.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category CategoryFromCategoryDTO(CategoryDTO categoryDTO);
    CategoryDTO CategoryDTOFromCategory(Category category);
}
