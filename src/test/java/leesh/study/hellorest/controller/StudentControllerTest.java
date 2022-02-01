package leesh.study.hellorest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static leesh.study.hellorest.ApiDocumentUtils.getDocumentRequest;
import static leesh.study.hellorest.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
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
    public void oneTest() throws Exception {

        Long id = 1L;

        ResultActions result = mockMvc.perform(get("/api/students/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("firstName").exists())
                .andExpect(jsonPath("lastName").exists())
                .andExpect(jsonPath("age").exists())
                .andExpect(jsonPath("grade").exists())
                .andExpect(jsonPath("major").exists())
                .andExpect(jsonPath("_links.self.href").exists())
                .andExpect(jsonPath("_links.students.href").exists())
                .andDo(print());

        result.andDo(document("find-one",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("id").description("아이디")),
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("students").description("link to collection")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content-type")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("학생 아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("학생 전체 이름"),
                                fieldWithPath("firstName").type(JsonFieldType.STRING).description("학생 성"),
                                fieldWithPath("lastName").type(JsonFieldType.STRING).description("학생 이름"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("학생 나이"),
                                fieldWithPath("grade").type(JsonFieldType.NUMBER).description("학생 반"),
                                fieldWithPath("major").type(JsonFieldType.STRING).description("학생 전공"),
                                fieldWithPath("_links.self.href").type(JsonFieldType.STRING).description("link to self"),
                                fieldWithPath("_links.students.href").type(JsonFieldType.STRING).description("link to collection")
                        )));
    }
}