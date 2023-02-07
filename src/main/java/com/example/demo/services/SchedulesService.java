package com.example.demo.services;

import com.example.demo.dto.SchedulesDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Schedules;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchedulesService {

    private final SchedulesRepository schedulesRepository;
    private final CourseRepository courseRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public List<SchedulesDTO> getSchedules() {

        return schedulesRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public boolean doesItExist(Long studentId, Long courseId){
        return schedulesRepository.findByStudentIdAndCourseId(studentId, courseId) != null;
    }

    public boolean isCurseFull(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "course with id " + courseId + " does not exist"));

        return course.getStudentNo() >= 30;

    }

    public void incrementStudentNo(Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "course with id " + courseId + " does not exist"));

        course.setStudentNo(course.getStudentNo() + 1);
        courseRepository.save(course);
    }

    private SchedulesDTO convertEntityToDto(Schedules schedules){
        SchedulesDTO schedulesDTO = new SchedulesDTO();
        BeanUtils.copyProperties(schedules, schedulesDTO);

        return schedulesDTO;
    }

    public void addStudentToCourse(SchedulesDTO schedulesDTO)
    {
        if(!doesItExist(schedulesDTO.getStudentId(), schedulesDTO.getCourseId())) {

            if (!isCurseFull(schedulesDTO.getCourseId())) {

                Schedules schedule = new Schedules();

                schedule.setId(sequenceGeneratorService.generateSequence(Schedules.SEQUENCE_NAME));
                BeanUtils.copyProperties(schedulesDTO, schedule);

                incrementStudentNo(schedulesDTO.getCourseId());

              this.schedulesRepository.save(schedule);
            } else
                throw new IllegalStateException("The course is full!");
        }else
            throw new IllegalStateException("The student is enrolled in this course!");
    }

    public void updateGrade(Long studentId, Long courseId, int grade) {

        Schedules schedule = schedulesRepository.findByStudentIdAndCourseId(studentId, courseId);
        schedule.setGrade(grade);
        schedulesRepository.save(schedule);

    }

    public int getGrade(Long std, Long cs) {
        Schedules schedules = schedulesRepository.findByStudentIdAndCourseId(std, cs);
        return schedules.getGrade();
    }

}
