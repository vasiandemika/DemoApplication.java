package com.example.demo.services;

import com.example.demo.dto.TeacherDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public List<Teacher> getTeacher() {
        return teacherRepository.findAll();
    }

    public void createTeacher(TeacherDTO teacherDTO) {
        if(teacherRepository.existsByEmail(teacherDTO.getEmail())){
            throw new IllegalStateException("Teacher with this email already exist!");
        }
        Teacher teacher = new Teacher();
        teacher.setId(sequenceGeneratorService.generateSequence(Course.SEQUENCE_NAME));
        BeanUtils.copyProperties(teacherDTO, teacher);
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        boolean exist = teacherRepository.existsById(teacherId);
        if (!exist) {
            throw new IllegalStateException("teacher with id " + teacherId + " does not exist");
        }
        teacherRepository.deleteTeacherById(teacherId);
    }

    public void updateTeacher(
            Long teacherId,
            String name,
            String email) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalStateException("teacher with id " + teacherId + " does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(teacher.getName(), name)) {
            teacher.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(teacher.getEmail(), email)) {
            Optional<Teacher> teacherOptional = teacherRepository
                    .findTeacherByEmail(email);
            if (teacherOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            teacher.setEmail(email);
        }
        teacherRepository.save(teacher);
    }

}
