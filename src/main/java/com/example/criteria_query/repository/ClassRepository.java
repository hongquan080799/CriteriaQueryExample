package com.example.criteria_query.repository;

import com.example.criteria_query.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassRepository extends JpaRepository<Class, Long>, JpaSpecificationExecutor<Class> {
}
