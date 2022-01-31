package leesh.study.hellorest;

import leesh.study.hellorest.entity.student.Student;
import leesh.study.hellorest.entity.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Slf4j
@Configuration
public class InitDb {

    @Bean
    CommandLineRunner loadInitialData(StudentRepository studentRepository) {
        return args -> {

            log.info("Init Db start...");

            String[] majors = {
                    "Computer Science", "Mechanical Engineering", "Electronic Engineering",
                    "Physics", "Society", "Business Administration", "Chemistry"
            };

            for (int i = 0; i < 50; i++) {
                Random random = new Random();
                studentRepository.save(Student.builder()
                        .name("test" + i)
                        .firstName("test")
                        .lastName(String.valueOf(i))
                        .age(20 + random.nextInt(15))
                        .major(majors[random.nextInt(majors.length)])
                        .grade(1 + random.nextInt(5))
                        .build());
            }

            log.info("Init Db done!");
        };
    }
}
