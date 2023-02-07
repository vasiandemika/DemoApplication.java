package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {

    Optional<Course> findCourseByCode(int code);

}
