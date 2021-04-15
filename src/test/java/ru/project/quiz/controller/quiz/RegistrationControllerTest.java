package ru.project.quiz.controller.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.project.quiz.controller.auth.RegistrationController;
import ru.project.quiz.domain.dto.ituser.ITUserDTO;
import ru.project.quiz.domain.dto.ituser.RoleDTO;
import ru.project.quiz.handler.response.Response;
import ru.project.quiz.service.ituser.ITUserService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"PROFILE=dev"})
public class RegistrationControllerTest {

    private final ObjectMapper om = new ObjectMapper();

    @Mock
    private ITUserService mockService;

    @Autowired
    private MockMvc mockMvc;

    private final RegistrationController registrationController = new RegistrationController(mockService);

    private ITUserDTO itUserDTO;

    @BeforeEach
    public void setUp() {
        itUserDTO = new ITUserDTO("test","test","email@test.ru", Collections.singleton(new RoleDTO("test", Collections.EMPTY_SET)));
    }
    @WithMockUser("test")
    @Test
    public void registration() throws Exception {
        MvcResult request = mockMvc.perform(post("/api/auth/register")
                .content(om.writeValueAsString(itUserDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String result = request.getResponse().getContentAsString();
        Response response = om.readValue(result, Response.class);
        assertEquals("Register success", response.getMessage());
    }
}

