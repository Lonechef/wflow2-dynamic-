package com.workflow_management.workflow.repository;

import com.workflow_management.workflow.model.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
}
