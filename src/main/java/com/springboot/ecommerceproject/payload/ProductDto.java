package com.springboot.ecommerceproject.payload;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data //Lombok generates getter and setters
public class ProductDto {
    private long id;

    @NotEmpty
    private String latitude;
    @NotEmpty
    private String longitude;

    @NotEmpty
    private String turu;

    @NotEmpty
    private String cinsi;

    @NotEmpty
    private String dogum;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String childs;

    @NotEmpty
    private String irki;

    @NotEmpty
    private String vet;

    @NotEmpty
    private String hastalik;

    @NotEmpty
    private String geo;

    @NotEmpty
    private String lastlocationdate;





}