package com.quizo.app.dao.repository;

import com.quizo.app.dao.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SolutionRepository extends JpaRepository<Solution, UUID> {
}
