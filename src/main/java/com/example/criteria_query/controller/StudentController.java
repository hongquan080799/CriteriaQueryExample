package com.example.criteria_query.controller;

import com.example.criteria_query.dto.SortType;
import com.example.criteria_query.dto.StudentDTO;
import com.example.criteria_query.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
// this is for criteria query
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/search")
    public List<StudentDTO> searchStudent (@RequestParam String search, @RequestParam(name = "className", required = false) String className) {
        return StringUtils.hasText(className) ? studentService.searchStudentDndClass(search, className) : studentService.searchByStudentName(search);
    }
    @GetMapping()
    public Page<StudentDTO> findAllStudent (
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastname,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "isActive", required = false) Boolean isActive,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate,
            @RequestParam(name = "sortType", required = true) SortType sortType,
            @RequestParam(name = "pageSize", required = true) int pageSize,
            @RequestParam(name = "pageNum", required = true) int pageNum
            ) {
        return studentService.findAll(firstName, lastname, email, isActive, startDate, endDate, sortType, pageSize, pageNum);
    }
}
