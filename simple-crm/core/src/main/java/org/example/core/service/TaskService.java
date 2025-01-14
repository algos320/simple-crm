package org.example.core.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.core.dto.TaskDTO;
import org.example.core.entity.Task;
import org.example.core.mapper.TaskMapper;
import org.example.core.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.dtoToEntity(taskDTO);
        Task savedTask = repository.save(task);
        return taskMapper.entityToDTO(savedTask);
    }

    @Transactional
    public TaskDTO updateTask(TaskDTO taskDTO) {
        Task existingTask = repository.findById(taskDTO.getId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setCreatedAt(taskDTO.getCreatedAt());
        existingTask.setEndAt(taskDTO.getEndAt());

        Task updatedTask = repository.save(existingTask);

        return taskMapper.entityToDTO(updatedTask);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        if (!repository.existsById(taskId)) {
            throw new EntityNotFoundException("Task not found");
        }
        repository.deleteById(taskId);
    }
}
