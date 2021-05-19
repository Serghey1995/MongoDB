package com.example.demo.controller;


import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.document.Address;
import com.example.demo.document.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;


@Controller
public class MainController {

    @Resource
    private StudentRepository studentRepository;
     @Resource
     private AddressRepository addressRepository;


    @GetMapping("/students")
    public String students(Model model) {
        Date date1 = new Date();
        int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
        model.addAttribute("students", studentRepository.findAll());
        Date date2 = new Date();
        int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
        int time3 = time2-time1;
        System.out.println("На запрос было потрачено " + time3 + " сек.");
        return "students-list";
    }
   @GetMapping("/students/add")
   public String studentAdd (Model model){
       model.addAttribute("student",new Student());
       return "student-add";
    }
    @PostMapping("/students/add")
    public String studentSubmit(Student student, Model model, Address address) {
       addressRepository.save(address);
       studentRepository.save(student);
        model.addAttribute("students",studentRepository.findAll());
        return "students-list";
    }

     @GetMapping("/students/edit/{id}")
    public String StudentEdit(@PathVariable(name = "id") String id , Model model){
        model.addAttribute("student",studentRepository.findById(id));
        return "students-edit";
    }
    @PostMapping("/students/edit/{id}")
    public String StudentEditSubmit(String id ,Model model,Address address,Student student){
        addressRepository.save(address);
        studentRepository.save(student);
        studentRepository.findById(id);
        model.addAttribute("student" ,studentRepository.findAll());
        return "students-edited";
    }
    @GetMapping("/students/delete/{id}")
    public String StudentDelete(@PathVariable(name = "id") String id , Model model) {
        model.addAttribute("student", studentRepository.findById(id));
        return "students-delete";
    }
    @PostMapping("/students/delete/{id}")
    public String studentDelete (String id ,Model model,Address address,Student student) {
        addressRepository.delete(address);
        studentRepository.delete(student);
        studentRepository.findById(id);
       model.addAttribute("student" ,studentRepository.findAll());
        return "students-deleted";
    }
    @GetMapping("/students/search")
    public String StudentSearch(Model model){
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("student", new Student());
        return "students-search";
    }
    @PostMapping("students/search")
    public String StudentSearchSubmit(Model model,String name,String surname,Student student,String groupName){
        if(!surname.isEmpty()) {
            Date date1 = new Date();
           int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
            model.addAttribute("students", studentRepository.findAllBySurname(surname));
            Date date2 = new Date();
            int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
            int time3 = time2-time1;
           System.out.println("Время потраченное на запрос " + time3 + " сек");
        }
        if(!name.isEmpty()) {
            Date date1 = new Date();
            int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
           model.addAttribute("students",studentRepository.findAllByName(name));
            Date date2 = new Date();
            int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
            int time3 = time2-time1;
            System.out.println("Время потраченное на запрос " + time3 + " сек");
        }
        if(!name.isEmpty() && !surname.isEmpty()) {
            Date date1 = new Date();
            int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
            model.addAttribute("students",studentRepository.findAllByNameAndSurname(name,surname));
            Date date2 = new Date();
            int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
           int time3 = time2-time1;
            System.out.println("Время потраченное на запрос " + time3 + " сек");
        }
        if (!student.getAddress().getCountry().isEmpty()){
            Date date1 = new Date();
            int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
           model.addAttribute("students",studentRepository.findAllByAddress_Country(student.getAddress().getCountry()));
            Date date2 = new Date();
            int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
            int time3 = time2-time1;
            System.out.println("Время потраченное на запрос " + time3 + " сек");
        }
        if (!student.getAddress().getCity().isEmpty()){
            Date date1 = new Date();
            int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
            model.addAttribute("students",studentRepository.findAllByAddress_City(student.getAddress().getCity()));
            Date date2 = new Date();
            int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
            int time3 = time2-time1;
           System.out.println("Время потраченное на запрос " + time3 + " сек");
        }
        if (!student.getAddress().getStreet().isEmpty()){
            Date date1 = new Date();
            int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
            model.addAttribute("students",studentRepository.findAllByAddress_Street(student.getAddress().getStreet()));
           Date date2 = new Date();
            int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
            int time3 = time2-time1;
            System.out.println("Время потраченное на запрос " + time3 + " сек");
        }
        if (!groupName.isEmpty()){
            Date date1 = new Date();
            int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
            model.addAttribute("students",studentRepository.findAllByGroupName(groupName));
            Date date2 = new Date();
            int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
            int time3 = time2-time1;
            System.out.println("Время потраченное на запрос " + time3 + " сек");
        }
        if (!student.getAddress().getCountry().isEmpty() && !student.getAddress().getCity().isEmpty()){
            Date date1 = new Date();
            int time1 = date1.getHours() + date1.getMinutes() +date1.getSeconds();
            model.addAttribute("students",studentRepository.findAllByAddress_CountryAndAddress_City(student.getAddress().getCountry(),student.getAddress().getCity()));
            Date date2 = new Date();
           int time2 = date2.getHours() + date2.getMinutes() +date2.getSeconds();
            int time3 = time2-time1;
            System.out.println("Время потраченное на запрос " + time3 + " сек");
        }

        return "students-searched";

    }


}


