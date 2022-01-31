package leesh.study.hellorest.service;

import leesh.study.hellorest.entity.student.Student;
import leesh.study.hellorest.entity.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
