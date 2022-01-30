package leesh.study.hellorest.service;

import leesh.study.hellorest.entity.Student;
import leesh.study.hellorest.entity.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;



}
