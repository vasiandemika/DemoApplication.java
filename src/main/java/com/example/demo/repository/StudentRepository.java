package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);

    @Query("{ 'id' : ?0 }")
    @Update("{ '$set' : { 'isActive' : 'false' } }")
    void deleteStudentById(Long id);
}
