CREATE TABLE execution_flow (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    condition_type VARCHAR(255) NOT NULL,
    condition_value VARCHAR(255) NOT NULL,
    task_id BIGINT,
    workflow_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES task(id),
    FOREIGN KEY (workflow_id) REFERENCES workflow(id)
);
