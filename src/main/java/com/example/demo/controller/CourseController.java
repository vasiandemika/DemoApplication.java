package com.example.demo.controller;

import com.example.demo.dto.CourseDTO;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.services.CourseService;
import com.example.demo.services.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public List<CourseDTO> getCourse() {

        return courseService.getCourse();
    }

    @PostMapping("/add")
    public void createCourse(@RequestBody CourseDTO courseDTO) {

        courseService.createCourse(courseDTO);
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId) {

        courseService.deleteCourse(courseId);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int code){

        courseService.updateCourse(courseId, name, code);
    }
}
