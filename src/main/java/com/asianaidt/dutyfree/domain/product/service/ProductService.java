package com.asianaidt.dutyfree.domain.product.service;


import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.dto.ProductListDto;
import com.asianaidt.dutyfree.domain.product.repository.CategoryRepository;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ProductListDto> getProductByCategoryId(Long categoryId, String sort, Pageable pageable) {
        /*
        카테고리 아이디로 해당 카테고리에 속하는 아이템을 불러온다
        아이템의 아이디를 찾는다
        찾은 아이템의 아이디를 가지고 사진을 불러온다
         */
        System.out.println("sort = " + sort);
//        Page<Product> res = productRepository.findByCategoryIdOrderByPriceAsc(categoryId);

//        Page<ProductListDto> page = (ArrayList<ProductListDto>) list;
        // Slice<ConsultingListDto> result = page.map(ConsultingListDto::new);
//        if (sort.equals("Asc")) {
//            Page<Product> products = productRepository.findByIdOrderByPriceAsc(categoryId,pageable);
//
//        } else {
//            Page<Product> products = productRepository.findByIdOrderByPriceDesc(categoryId, pageable);
//        }

        return null;
    }
}
