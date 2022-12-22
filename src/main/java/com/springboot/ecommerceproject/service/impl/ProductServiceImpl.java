package com.springboot.ecommerceproject.service.impl;

import com.springboot.ecommerceproject.entity.Product;
import com.springboot.ecommerceproject.exception.ResourceNotFoundException;
import com.springboot.ecommerceproject.payload.ProductDto;
import com.springboot.ecommerceproject.payload.ProductResponse;
import com.springboot.ecommerceproject.repository.ProductRepository;
import com.springboot.ecommerceproject.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //indicates that is a service. The class is service class. We will Inject class
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper){
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        // convert DTO to entity
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);

        // convert entity to DTO
        ProductDto postResponse = mapToDTO(newProduct);
        return postResponse;
    }

    @Override
    public ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> posts = productRepository.findAll(pageable);

        //get content from page object
        List<Product> listOfProducts = posts.getContent();
        List<ProductDto> content = listOfProducts.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(posts.getNumber());
        productResponse.setPageSize(posts.getSize());
        productResponse.setTotalElements(posts.getTotalElements());
        productResponse.setTotalPages(posts.getTotalPages());
        productResponse.setLast(posts.isLast());

        return productResponse;
    }

    @Override
    public ProductDto getProductById(long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, long id) {
        //get post by id from the db
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        product.setLatitude(productDto.getLatitude());
        product.setLongitude(productDto.getLongitude());
        product.setLastlocationdate(productDto.getLastlocationdate());
        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        productRepository.delete(product);
    }

    //convert entity to dto
    private ProductDto mapToDTO(Product product){
        ProductDto productDto = mapper.map(product, ProductDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return productDto;
    }

    // convert DTO to entity

    private Product mapToEntity(ProductDto productDto){
        Product product = mapper.map(productDto, Product.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return product;
    }
}