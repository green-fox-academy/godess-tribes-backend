package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LoginRequestDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.RegisterRequestDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.services.KingdomService;
import com.greenfoxacademy.goddesstribesbackend.services.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  private static MediaType contentType;

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserService userServiceMock;
  @MockBean
  private KingdomService kingdomServiceMock;

  @BeforeClass
  public static void init() {
    contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_noUsernameAndPasswordIsGiven() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = "Username and password are required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_UsernameAndPasswordAreEmptyString() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setUsername("");
    registerRequestDTO.setPassword("");
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = "Username and password are required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_noUsernameIsGiven() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setPassword("jancsi123");
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = "Username is required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_EmptyUsernameIsGiven() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setUsername("");
    registerRequestDTO.setPassword("jancsi123");
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = "Username is required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_noPasswordIsGiven() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setUsername("Juliska");
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = "Password is required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_EmptyPasswordIsGiven() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setUsername("Juliska");
    registerRequestDTO.setPassword("");
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = "Password is required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_UsernameIsTaken() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    String username = "Juliska";
    registerRequestDTO.setUsername(username);
    registerRequestDTO.setPassword("jancsi123");
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = username + " as username is already taken.";

    when(userServiceMock.checkUserByName(any())).thenReturn(true);

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(409))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnErrorMessage_when_PasswordIsTooShort() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setUsername("Juliska");
    registerRequestDTO.setPassword("jancsi");
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    String expectedErrorMessage = "Password must be at least 8 characters.";

    when(userServiceMock.checkUserByName(any())).thenReturn(false);
    when(userServiceMock.checkPassword(any())).thenReturn(false);

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void registerShouldReturnProperResult_when_UsernameAndPasswordAreCorrect() throws Exception {
    String username = "Juliska";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    int userId = 10;
    User user = new User();
    user.setId((long) userId);
    user.setUsername(username);
    user.setPassword(password);
    Kingdom kingdom = new Kingdom(kingdomName, user);

    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setUsername(username);
    registerRequestDTO.setPassword(password);
    String registerRequestDTOJson = objectMapper.writeValueAsString(registerRequestDTO);

    when(userServiceMock.checkUserByName(any())).thenReturn(false);
    when(userServiceMock.checkPassword(any())).thenReturn(true);
    when(userServiceMock.saveUser(any(), any())).thenReturn(user);
    when(kingdomServiceMock.saveKingdom(any(), any())).thenReturn(kingdom);

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(registerRequestDTOJson))
            .andExpect(status().is(200))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.userId", is(userId)))
            .andExpect(jsonPath("$.username", is(username)))
            .andExpect(jsonPath("$.kingdomName", is(kingdomName)));
  }

  @Test
  public void loginShouldReturnErrorMessage_when_noUsernameAndPasswordIsGiven() throws Exception {
    LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
    String loginRequestDTOJson = objectMapper.writeValueAsString(loginRequestDTO);

    String expectedErrorMessage = "Username and password are required.";

    mockMvc.perform(post("/login")
            .contentType(contentType)
            .content(loginRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void loginShouldReturnErrorMessage_when_noUsernameIsGiven() throws Exception {
    LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
    loginRequestDTO.setPassword("jancsi123");
    String loginRequestDTOJson = objectMapper.writeValueAsString(loginRequestDTO);

    String expectedErrorMessage = "Username is required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(loginRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void loginShouldReturnErrorMessage_when_EmptyUsernameIsGiven() throws Exception {
    LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
    loginRequestDTO.setUsername("");
    loginRequestDTO.setPassword("jancsi123");
    String loginRequestDTOJson = objectMapper.writeValueAsString(loginRequestDTO);

    String expectedErrorMessage = "Username is required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(loginRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void loginShouldReturnErrorMessage_when_noPasswordIsGiven() throws Exception {
    LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
    loginRequestDTO.setUsername("Juliska");
    String loginRequestDTOJson = objectMapper.writeValueAsString(loginRequestDTO);

    String expectedErrorMessage = "Password is required.";

    mockMvc.perform(post("/register")
            .contentType(contentType)
            .content(loginRequestDTOJson))
            .andExpect(status().is(400))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.status", is("error")))
            .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

}
