package leesh.study.hellorest.controller;

import leesh.study.hellorest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentService studentService;

}
