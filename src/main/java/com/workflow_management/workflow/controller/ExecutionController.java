package com.workflow_management.workflow.controller;

import com.workflow_management.workflow.dto.ExecutionRequestDTO;
import com.workflow_management.workflow.dto.ExecutionResponseDTO;
import com.workflow_management.workflow.model.Execution;
import com.workflow_management.workflow.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/execution")
public class ExecutionController {

    @Autowired
    private ExecutionService executionService;

    @PostMapping("/create/{workflowId}")
    public Execution createExecution(@PathVariable Long workflowId) {
        return executionService.createExecution(workflowId);
    }
    @PostMapping("/execute")
    public ExecutionResponseDTO executeWorkflow(@RequestBody ExecutionRequestDTO executionRequestDTO) {
        return executionService.executeWorkflow(executionRequestDTO);
    }
    @GetMapping("/all")
    public List<Execution> getAllExecutions() {
        return executionService.getAllExecutions();
    }


}
