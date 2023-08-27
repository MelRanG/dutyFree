package com.asianaidt.dutyfree.domain.product.service;


import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.dto.ProductDto;
import com.asianaidt.dutyfree.domain.product.dto.ProductListDto;
import com.asianaidt.dutyfree.domain.product.repository.CategoryRepository;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ProductDto> getRecentProduct() {
        List<ProductDto> dto = new ArrayList<>();
        productRepository.findTop5ByOrderByIdDesc().stream().forEach(
                product -> {
                   dto.add(new ProductDto(product));
                }
        );
        return dto;
    }

    public List<ProductDto> getSaleProducts() {
        List<ProductDto> dto = new ArrayList<>();
        productRepository.findTop4ByOrderByName().stream().forEach(
                product -> {
                    dto.add(new ProductDto(product));
                }
        );
        return dto;
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

    public Page<ProductListDto> getProductByCategoryId(Long categoryId, String sort, Pageable pageable) {
        /*
        카테고리 아이디로 해당 카테고리에 속하는 아이템을 불러온다
        아이템의 아이디를 찾는다
        찾은 아이템의 아이디를 가지고 사진을 불러온다
         */
        System.out.println("sort = " + sort);
//        Page<Product> page = productRepository.findByCategoryIdOrderByPriceAsc(categoryId, pageable);
//
//        return page.map(ProductListDto::new);
//        page.stream().map(ProductListDto::new)
//                .collect(Collectors.toList())

//        Page<ProductListDto> page = (ArrayList<ProductListDto>) list;
        // Slice<ConsultingListDto> result = page.map(ConsultingListDto::new);
        if (sort.equals("Asc")) {
            System.out.println("오름차 입니다");
            Page<Product> page = productRepository.findByCategoryIdOrderByPriceAsc(categoryId,pageable);
            return page.map(ProductListDto::new);
        } else {
            System.out.println("내림차 입니다");
            Page<Product> page = productRepository.findByCategoryIdOrderByPriceDesc(categoryId, pageable);
            return page.map(ProductListDto::new);
        }
    }

    public Page<ProductListDto> getProductByPrice(Long categoryId, Integer min, Integer max, Pageable pageable) {

        Page<Product> page = productRepository.findByCategoryIdAndPriceBetween(categoryId, min, max, pageable);

        return page.map(ProductListDto::new);
    }

    public ProductDto getProductDetail(Long productId) {
        Optional<Product> byId = productRepository.findById(productId);
        return new ProductDto(byId.get());
    }

    public Page<ProductListDto> searchProduct(String productName, Pageable pageable) {
        Page<Product> page = productRepository.findAllByNameContaining(productName, pageable);

        return page.map(ProductListDto::new);
    }
}
