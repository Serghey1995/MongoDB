package com.example.demo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "students")
@Getter
@Setter
public class Student {
    @Id
    private String id;

    @Indexed(name = "name")
    private String name;

    @Indexed(name= "surname")
    private String surname;

    @Indexed(name = "groupName")
    private String groupName;


    private Address address;

}
