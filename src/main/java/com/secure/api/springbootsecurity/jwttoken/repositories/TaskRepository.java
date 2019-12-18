package com.secure.api.springbootsecurity.jwttoken.repositories;

import com.secure.api.springbootsecurity.jwttoken.entites.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
