package com.example.criteria_query.mapper;

import com.example.criteria_query.dto.ClassDTO;
import com.example.criteria_query.model.Class;

import java.util.stream.Collectors;

public class ClassMapper {
    public static ClassDTO mapFromEntityToDTO(Class studentClass) {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(studentClass.getId());
        classDTO.setName(studentClass.getName());
        if(studentClass.getStudents() != null) {
            classDTO.setStudents(studentClass.getStudents().stream().map(StudentMapper::mapFromEntityToDTO).collect(Collectors.toList()));
        };
        return classDTO;
    }
}
