package com.example.criteria_query.mapper;

import com.example.criteria_query.dto.ClassDTO;
import com.example.criteria_query.dto.StudentDTO;
import com.example.criteria_query.model.Class;
import com.example.criteria_query.model.Student;

public class StudentMapper {
    public static StudentDTO mapFromEntityToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setBirthDate(student.getBirthDate());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setActive(student.getActive());
        if(student.getStudentClass() != null) {
            Class classStudent = student.getStudentClass();
            ClassDTO classDTO = new ClassDTO();
            classDTO.setId(classStudent.getId());
            classDTO.setName(classStudent.getName());
            studentDTO.setStudentClass(classDTO);
        }
        return studentDTO;
    }
}
