package com.example.demo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Getter
@Setter
public class Address {
    @Id
    private String id;
    @Indexed(name = "country")
    private String country;
    @Indexed(name = "city")
    private String city;
    @Indexed(name = "street")
    private String street;
}
