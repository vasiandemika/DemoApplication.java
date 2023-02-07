package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public List<Student> getAll(){

       return studentRepository.findAll();
    }

    public void createStudent(StudentDTO studentDTO, MultipartFile file) throws IOException {
        Student student = new Student();
        student.setId(sequenceGeneratorService.generateSequence(Student.SEQUENCE_NAME));
        BeanUtils.copyProperties(studentDTO, student);
        student.setCertificate(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        student = studentRepository.insert(student);
        ResponseEntity.status(201).body(this.studentRepository.save(student));
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteStudentById(studentId);
    }

    public void updateStudent(
            Long studentId,
            String name,
            String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        if(name != null &&
        name.length() > 0 &&
        !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

        studentRepository.save(student);
    }

}
