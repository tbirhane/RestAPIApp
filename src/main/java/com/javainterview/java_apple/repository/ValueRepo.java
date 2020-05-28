package com.javainterview.java_apple.repository;

import com.javainterview.java_apple.model.Value;
import org.springframework.data.repository.CrudRepository;

public interface ValueRepo extends CrudRepository<Value, Integer> {
}
