package com.springboot.ecommerceproject.service;

import com.springboot.ecommerceproject.payload.ProductDto;
import com.springboot.ecommerceproject.payload.ProductResponse;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDto getProductById(long id);

    ProductDto updateProduct(ProductDto productDto, long id);

    void deleteProductById(long id);

}
