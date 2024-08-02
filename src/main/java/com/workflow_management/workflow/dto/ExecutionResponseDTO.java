package com.workflow_management.workflow.dto;

import java.util.ArrayList;
import java.util.List;

public class ExecutionResponseDTO {
    private List<String> steps;
    private String status;

    public ExecutionResponseDTO() {
        this.steps = new ArrayList<>();
    }

    public void addStep(String step) {
        this.steps.add(step);
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
