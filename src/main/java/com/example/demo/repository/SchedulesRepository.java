package com.example.demo.repository;

import com.example.demo.model.Schedules;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchedulesRepository extends MongoRepository<Schedules, Long> {

    Schedules findByStudentIdAndCourseId(Long studentId, Long courseId);

}