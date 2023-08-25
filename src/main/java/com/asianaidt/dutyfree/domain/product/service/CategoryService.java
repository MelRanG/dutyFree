package com.asianaidt.dutyfree.domain.product.service;

import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.product.dto.CategoryListDto;
import com.asianaidt.dutyfree.domain.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
