package leesh.study.hellorest.entity.student;

import leesh.study.hellorest.controller.StudentController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {

    @Override
    public EntityModel<Student> toModel(Student student) {
        return EntityModel.of(student,
                linkTo(methodOn(StudentController.class).one(student.getId())).withSelfRel(),
                linkTo(methodOn(StudentController.class).all()).withRel("students"));
    }

    public CollectionModel<EntityModel<Student>> toCollectionModel(List<Student> students) {

        List<EntityModel<Student>> entityModels = students.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels,
                linkTo(methodOn(StudentController.class).all()).withSelfRel());
    }
}
