package com.workflow_management.workflow.service;

import com.workflow_management.workflow.dto.ExecutionRequestDTO;
import com.workflow_management.workflow.dto.ExecutionResponseDTO;
import com.workflow_management.workflow.model.Execution;
import com.workflow_management.workflow.model.Workflow;
import com.workflow_management.workflow.model.WorkflowStep;
import com.workflow_management.workflow.repository.ExecutionRepository;
import com.workflow_management.workflow.repository.WorkflowRepository;
import com.workflow_management.workflow.repository.WorkflowStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private WorkflowStepRepository workflowStepRepository;

    @Transactional
    public Execution createExecution(Long workflowId) {
        Workflow workflow = workflowRepository.findById(workflowId).orElseThrow(() -> new RuntimeException("Workflow not found"));

        Execution execution = new Execution();
        execution.setWorkflowName(workflow.getName());
        execution.setWorkflowId(workflowId);

        List<WorkflowStep> steps = workflowStepRepository.findByWorkflowId(workflowId);

        execution.setTask1(steps.get(0).getTask().getName());
        execution.setTask2(steps.get(1).getTask().getName());
        execution.setTask3(steps.get(2).getTask().getName());

        return executionRepository.save(execution);
    }

    public ExecutionResponseDTO executeWorkflow(ExecutionRequestDTO executionRequestDTO) {
        ExecutionResponseDTO responseDTO = new ExecutionResponseDTO();
        responseDTO.setStatus("START");

        if (executionRequestDTO.getDob() != null) {
            int age = calculateAge(executionRequestDTO.getDob());

            if (age > 30) {
                responseDTO.addStep("Age is Greater then 30");
                if ("F".equalsIgnoreCase(executionRequestDTO.getGender())) {
                    responseDTO.addStep("Loan Approved");
                } else {
                    responseDTO.addStep("Check Pincode starts with '40'?");
                    if (executionRequestDTO.getPincode() != null && executionRequestDTO.getPincode().startsWith("40")) {
                        responseDTO.addStep("Update DB: status = 'loan_approval_required'");
                    } else {
                        responseDTO.addStep("Update DB: status = 'loan_approved'");
                    }
                }
            } else {
                responseDTO.addStep("Update DB: status = 'loan_approved and dob was less then 30");
            }
        }

        responseDTO.addStep("End");
        responseDTO.setStatus("END");
        return responseDTO;
    }

    private int calculateAge(String dob) {
        // Assume the dob is in format "YYYY-MM-DD"
        int birthYear = Integer.parseInt(dob.substring(0, 4));
        int currentYear = java.time.Year.now().getValue();
        return currentYear - birthYear;
    }
}
