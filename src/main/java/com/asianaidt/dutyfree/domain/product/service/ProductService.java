package com.asianaidt.dutyfree.domain.product.service;


import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.repository.CategoryRepository;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Category addCategory(){
        Category category = Category.builder()
                .name("category1")
                .build();

        return categoryRepository.save(category);
    }


    public Product addProduct() {
        Product product = Product.builder()
                .name("bag")
                .price(10000)
                .brand("aa")
                .category(addCategory())
                .build();

        return productRepository.save(product);
    }
}
