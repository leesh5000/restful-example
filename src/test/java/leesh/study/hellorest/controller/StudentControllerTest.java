package leesh.study.hellorest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import leesh.study.hellorest.dto.StudentRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.hateoas.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
//@AutoConfigureRestDocs
@SpringBootTest
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    StudentController studentController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
    }

    @Test
    public void registerTest() throws Exception {

        StudentRequest content = StudentRequest.builder()
                .name("LSH")
                .age(20)
                .grade(3)
                .major("CS")
                .build();

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(content)))
                .andExpect(header().string("Location", "/api/students"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isCreated())
                .andDo(print())
//                .andDo(document("/api/students",
//                        ))
        ;

    }
}