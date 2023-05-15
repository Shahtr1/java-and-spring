package com.example.jpa_specification.controller;

import com.example.jpa_specification.domain.Student;
import com.example.jpa_specification.dto.PageRequestDto;
import com.example.jpa_specification.dto.RequestDto;
import com.example.jpa_specification.service.FilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FilterSpecification<Student> studentFilterSpecification;

    @GetMapping("/{name}")
    public Student getStdByName(@PathVariable(name = "name") String name){
        return studentRepository.findByName(name);
    }

    @GetMapping("/city/{city}")
    public List<Student> getStdByCity(@PathVariable(name = "city") String city){
        return studentRepository.findByAddressCity(city);
    }

    @GetMapping("/subject/{subject}")
    public List<Student>  getStdBySubject(@PathVariable(name = "subject") String subject){
        return studentRepository.findBySubjectsName(subject);
    }

    @PostMapping("/specification")
    public List<Student> getStudents(@RequestBody RequestDto requestDto){
        Specification<Student> searchSpecification =
                studentFilterSpecification.getSearchSpecification(
                        requestDto.getSearchRequestDtoList(),requestDto.getGlobalOperator());
        return studentRepository.findAll(searchSpecification);
    }

    @PostMapping("/specification/pagination")
    public Page<Student> getStudentsPage(@RequestBody RequestDto requestDto){
        Specification<Student> searchSpecification =
                studentFilterSpecification.getSearchSpecification(
                        requestDto.getSearchRequestDtoList(),requestDto.getGlobalOperator());

        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageDto());

        return studentRepository.findAll(searchSpecification, pageable);
    }
}
