package com.javainterview.java_apple.repository;

import com.javainterview.java_apple.model.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends CrudRepository<Data, Integer> {
}
