package com.purvish.ecom_proj.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonProperty("description")
    @JsonAlias("desc")
    private String desc;
    private String brand;
    private BigDecimal price;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;
    @JsonProperty("productAvailable")
    @JsonAlias("available")
    private boolean available;
    @JsonProperty("stockQuantity")
    @JsonAlias("quantity")
    private int quantity;

    private String imageName;
    private String imageType;
    @JsonIgnore
    @Lob
    private byte[] imageData;
}
