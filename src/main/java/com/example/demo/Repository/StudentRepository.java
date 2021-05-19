package com.example.demo.Repository;

import com.example.demo.document.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface StudentRepository extends MongoRepository<Student,String> {
    List<Student> findAllBySurname(String surname);
    List<Student> findAllByName(String name);
    List<Student> findAllByNameAndSurname(String name,String surname);
    List<Student> findAllByAddress_Country(String country);
    List<Student> findAllByAddress_City(String city);
    List<Student> findAllByAddress_Street(String street);
    List<Student> findAllByAddress_CountryAndAddress_City(String country,String city);
    List<Student> findAllByGroupName(String groupName);


}
