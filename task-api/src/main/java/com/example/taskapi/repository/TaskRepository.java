package com.example.taskapi.repository;

import com.example.taskapi.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Task> findAll(){
        return tasks;
    }
    public Task findById(long id){
        return  tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Task save(Task task){
        if(task.getId() == 0){
            task.setId(idGenerator.incrementAndGet());
        } else {
            tasks.removeIf(t -> t.getId() == task.getId());
        }
        tasks.add(task);
        return task;
    }

    public void  delete(long id){
        tasks.removeIf(task -> task.getId() == id);
    }
}
