package com.asianaidt.dutyfree.domain.product.service;


import com.asianaidt.dutyfree.domain.product.domain.Category;
import com.asianaidt.dutyfree.domain.product.domain.Product;
import com.asianaidt.dutyfree.domain.product.dto.ProductDto;
import com.asianaidt.dutyfree.domain.product.dto.ProductListDto;
import com.asianaidt.dutyfree.domain.product.repository.CategoryRepository;
import com.asianaidt.dutyfree.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                    ProductDto productDto = new ProductDto(product);
                    productDto.setChangeMoney(changeMoney(product.getPrice()));
                   dto.add(productDto);
                }
        );
        return dto;
    }

    public List<ProductDto> getSaleProducts() {
        List<ProductDto> dto = new ArrayList<>();
        productRepository.findTop4ByOrderByName().stream().forEach(
                product -> {
                    ProductDto productDto = new ProductDto(product);
                    productDto.setChangeMoney(changeMoney(product.getPrice()));
                    dto.add(productDto);
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
            return new PageImpl<>(toPage(page));
//            return page.map(ProductListDto::new);
        } else {
            System.out.println("내림차 입니다");
            Page<Product> page = productRepository.findByCategoryIdOrderByPriceDesc(categoryId, pageable);
            return new PageImpl<>(toPage(page));
//            return page.map(ProductListDto::new);
        }
    }

    public Page<ProductListDto> getProductByPrice(Long categoryId, Integer min, Integer max, Pageable pageable) {

        Page<Product> page = productRepository.findByCategoryIdAndPriceBetween(categoryId, min, max, pageable);

        return new PageImpl<>(toPage(page));
    }
    public ProductDto getProductDetail(Long productId) {
        ProductDto pro = new ProductDto();
        Optional<Product> byId = productRepository.findById(productId);

        Integer usd = getUsd();


        Product op = byId.get();

        pro.setId(op.getId());
        pro.setName(op.getName());
        pro.setPrice(op.getPrice());
        pro.setDescription(op.getDescription());
        pro.setBrand(op.getBrand());
        pro.setPath(op.getPath());
        pro.setChangeMoney(changeMoney(op.getPrice() * usd));

        return pro;
    }

    public Page<ProductListDto> searchProduct(String productName, Pageable pageable) {
        Page<Product> page = productRepository.findAllByNameContaining(productName, pageable);

        return new PageImpl<>(toPage(page));
    }

    public Integer getUsd() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate(factory);
        // Define the base URL
        String baseUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON";
        String authKey = "NqiDZPM4w1iuTJB9P5xxZjd6hWjPugMl";

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime previousDay = localDateTime.minusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMdd");
        String datetime = previousDay.format(formatter);


        System.out.println("datetime = " + datetime);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("authkey",authKey)
                .queryParam("searchdate", datetime)
                .queryParam("data", "AP01")
                .build(false);

        ResponseEntity<String> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, new HttpEntity<>(headers), String.class);
//        System.out.println("response result: "+response);

        String body = response.getBody();
//        System.out.println("body = " + body);
//        JSONObject obj = new JSONObject(body);
        JSONArray obj = new JSONArray(body);
        int objsize = obj.length();

        JSONObject us = (JSONObject) obj.get(objsize - 1);

        String mon = (String) us.get("tts");

        mon = mon.replace(",", ""); // Remove commas

        int indexOfDot = mon.indexOf('.');
        if (indexOfDot >= 0) {
            mon = mon.substring(0, indexOfDot); // Extract substring before the '.'
        }
        System.out.println("mon = " + mon);
        Integer money = Integer.parseInt(mon);
        System.out.println("money = " + money);
//        System.out.println("Float value: " + floatValue);

//        Float money = Float.parseFloat(mon);

        return money;
    }

    public String changeMoney(int price) {
//        Integer usd = getUsd();
        Integer usd = getUsd();
        int cal = usd * price;
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(cal);

        System.out.println("formattedNumber = " + formattedNumber);

        return formattedNumber;
    }

    public List<ProductListDto> toPage(Page<Product> page) {
        List<ProductListDto> productDtoList = new ArrayList<>();
        page.forEach(
                p -> {
                    ProductListDto dto = ProductListDto.builder()
                            .id(p.getId())
                            .price(p.getPrice())
                            .path(p.getPath())
                            .changeMoney(changeMoney(p.getPrice()))
                            .name(p.getName())
                            .brand(p.getBrand())
                            .build();
                    productDtoList.add(dto);
                }
        );
        return productDtoList;
    }
}
