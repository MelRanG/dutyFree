package com.asianaidt.dutyfree.domain.product.service;

import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.product.dto.CategoryDto;
import com.asianaidt.dutyfree.domain.product.dto.CategoryListDto;
import com.asianaidt.dutyfree.domain.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<CategoryListDto> getAllCategory() {
        List<Category> allCategory = categoryRepository.findAll();

        return allCategory.stream()
                .map(CategoryListDto::new)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategory(Long categoryNum) {
        Optional<Category> op = categoryRepository.findById(categoryNum);

        CategoryDto cate = new CategoryDto();

        cate.setId(op.get().getId());
        cate.setName(op.get().getName());

        System.out.println("cate = " + cate);
//        CategoryDto cate = categoryDto.toEntity(op.get());

        return cate;
    }


}
