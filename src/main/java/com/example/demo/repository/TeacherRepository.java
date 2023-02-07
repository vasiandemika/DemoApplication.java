package com.example.demo.repository;

import com.example.demo.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TeacherRepository
        extends MongoRepository<Teacher, Long> {

    boolean existsByEmail(String email);

    Optional<Teacher> findTeacherByEmail(String email);

    @Query("{ 'id' : ?0 }")
    @Update("{ '$set' : { 'isActive' : 'false' } }")
    void deleteTeacherById(Long id);
}
