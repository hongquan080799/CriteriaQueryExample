package com.example.criteria_query.service;

import com.example.criteria_query.dto.SortType;
import com.example.criteria_query.dto.StudentDTO;
import com.example.criteria_query.mapper.StudentMapper;
import com.example.criteria_query.model.Class;
import com.example.criteria_query.model.Student;
import com.example.criteria_query.repository.StudentRepository;
import com.example.criteria_query.specification.StudentSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private StudentRepository repository;

    public List<StudentDTO> searchByStudentName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        // Correcting the concatenation and like operation
        String pattern = "%" + name + "%";
        cq.select(root).where(
                cb.like(cb.concat(cb.concat(root.get("firstName"), " "), root.get("lastName")), pattern));

        List<Student> listStudent = entityManager.createQuery(cq).getResultList();

        return listStudent.stream().map(StudentMapper::mapFromEntityToDTO).collect(Collectors.toList());
    }
    public List<StudentDTO> searchStudentDndClass(String name, String className) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Join<Student, Class> join = root.join("studentClass");

        String patternName = "%" + name + "%";
        String patternClass = "%" + className + "%";

        query.select(root).where(
                cb.and(cb.like(cb.concat(cb.concat(root.get("firstName"), " "), root.get("lastName")), patternName), cb.like(join.get("name"), patternClass)));

        List<Student> students = entityManager.createQuery(query).getResultList();
        return students.stream().map(StudentMapper::mapFromEntityToDTO).collect(Collectors.toList());


    }
    public Page<StudentDTO> findAll(String firstName, String lastName, String email, Boolean isActive, LocalDate startDate, LocalDate endDate, SortType sortType, int pageSize, int pageNum) {
        Specification<Student> specification = Specification.where(null);

        if (StringUtils.hasText(firstName)) {
            specification = specification.and(StudentSpecification.hasFirstName(firstName));
        }
        if (StringUtils.hasText(lastName)) {
            specification = specification.and(StudentSpecification.hasLastName(lastName));
        }
        if (StringUtils.hasText(email)) {
            specification = specification.and(StudentSpecification.hasEmail(email));
        }
        if (isActive != null) {
            specification = specification.and(StudentSpecification.isActive(isActive));
        }
        if (startDate != null && endDate != null) {
            specification = specification.and(StudentSpecification.birthDateBetween(startDate, endDate));
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Order.asc("firstName"), Sort.Order.asc("lastName")));

        return repository.findAll(specification, pageable).map(StudentMapper::mapFromEntityToDTO);
    }
}
