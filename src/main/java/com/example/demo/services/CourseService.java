package com.example.demo.services;

import com.example.demo.dto.CourseDTO;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public void createCourse(CourseDTO courseDTO){

        Course course = new Course();
        course.setId(sequenceGeneratorService.generateSequence(Course.SEQUENCE_NAME));
        BeanUtils.copyProperties(courseDTO, course);
        this.courseRepository.save(course);
    }

    public List<CourseDTO> getCourse() {
        return courseRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    private CourseDTO convertEntityToDto(Course course){
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);

        return courseDTO;
    }

    public void deleteCourse(Long courseId) {
        boolean exist = courseRepository.existsById(courseId);
        if (!exist) {
            throw new IllegalStateException("course with id " + courseId + " does not exist");
        }
        courseRepository.deleteById(courseId);
    }

    public void updateCourse(
            Long courseId,
            String name,
            int code) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("course with id " + courseId + " does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(course.getName(), name)) {
            course.setName(name);
        }
        if (code != 0) {
            Optional<Course> courseOptional = courseRepository
                    .findCourseByCode(code);
            if (courseOptional.isPresent()) {
                throw new IllegalStateException("code taken");
            }
            course.setCode(code);
        }
        courseRepository.save(course);
    }

}
