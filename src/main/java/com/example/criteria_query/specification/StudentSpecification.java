package com.example.criteria_query.specification;

import com.example.criteria_query.model.Student;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class StudentSpecification {

    public static Specification<Student> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"), firstName);
    }
    public static Specification<Student> hasLastName(String lastName) {
        return (root, query, cb) -> cb.equal(root.get("lastName"), lastName);
    }
    public static Specification<Student> birthDateBetween(LocalDate startDate, LocalDate endDate) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("birthDate"), startDate, endDate));
    }
    public static Specification<Student> hasEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email));
    }
    public static Specification<Student> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);
    }
}
