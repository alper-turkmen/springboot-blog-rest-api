package com.springboot.ecommerceproject.controller;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.ecommerceproject.payload.ProductDto;
import com.springboot.ecommerceproject.payload.ProductResponse;
import com.springboot.ecommerceproject.service.ProductService;
import com.springboot.ecommerceproject.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/api/pets")
public class ProductController {
    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //create blog post rest api

    @PostMapping
    public ResponseEntity<ProductDto> createProduct( @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }
    //get all Products rest api
    @GetMapping
    public ProductResponse getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
            ){
        return productService.getAllProducts(pageNo, pageSize, sortBy, sortDir);
    }

    //get post by id
    @GetMapping("/getsingle/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }


    //update post by id rest api
    @GetMapping("/send/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @RequestParam(value = "lat", required = false) String lat,
            @RequestParam(value = "lon", required = false) String lon,
            @PathVariable(name = "id") long id
){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT+03:00"));
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");;
        String formattedString = now.format(customFormat);
        ProductDto productDto = new ProductDto();

        productDto.setLatitude(lat);
        productDto.setLongitude(lon);
        productDto.setLastlocationdate(formattedString);

        ProductDto productResponse = productService.updateProduct(productDto, id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product entity deleted succesfully", HttpStatus.OK);
    }
}