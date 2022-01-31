package leesh.study.hellorest.controller;

import leesh.study.hellorest.dto.StudentRequest;
import leesh.study.hellorest.entity.student.Student;
import leesh.study.hellorest.entity.student.StudentModelAssembler;
import leesh.study.hellorest.entity.student.StudentRepository;
import leesh.study.hellorest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final StudentModelAssembler assembler;

    @GetMapping("/api/students/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {

        Student student = studentService.findById(id);
        return ResponseEntity.ok(assembler.toModel(student));
    }

    @GetMapping("/api/students")
    public ResponseEntity<?> all() {
        List<Student> students = studentService.findAll();
        var entityModels = this.assembler.toCollectionModel(students);
        return ResponseEntity.ok(entityModels);
    }

    @PostMapping("/api/students")
    public ResponseEntity<?> save(@RequestBody StudentRequest request) {

        Student student = Student.builder()
                .name(request.getName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .major(request.getMajor())
                .grade(request.getGrade())
                .build();

        EntityModel<Student> entityModel = assembler.toModel(studentService.save(student));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/api/students/{id}")
    public ResponseEntity<?> replace(@PathVariable Long id, @RequestBody StudentRequest request) {

        Student updatedStudent = studentRepository.findById(id)
                .map(student -> {
                    student.setName(request.getName());
                    student.setLastName(request.getLastName());
                    student.setFirstName(request.getFirstName());
                    student.setAge(request.getAge());
                    student.setGrade(request.getGrade());
                    student.setMajor(request.getMajor());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    Student student = Student.builder()
                            .id(id)
                            .name(request.getName())
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .age(request.getAge())
                            .major(request.getMajor())
                            .grade(request.getGrade())
                            .build();
                    return studentRepository.save(student);
                });

        EntityModel<Student> entityModel = assembler.toModel(updatedStudent);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
