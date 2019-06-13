package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingTypeDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Building;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Mine;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
import com.greenfoxacademy.goddesstribesbackend.services.BuildingService;
import com.greenfoxacademy.goddesstribesbackend.services.KingdomService;
import com.greenfoxacademy.goddesstribesbackend.services.ProductionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.Charset;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BuildingController.class)
public class BuildingControllerTest {

  private static String username;
  private static String password;
  private static String jwtToken;
  private static User user;
  private static Kingdom kingdom;
  private static MediaType contentType;
  private static BuildingTypeDTO buildingTypeDTO;

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private KingdomService kingdomServiceMock;
  @MockBean
  private ProductionService productionServiceMock;
  @MockBean
  private BuildingService buildingServiceMock;


  @BeforeClass
  public static void init() {
    username = "Juliska";
    password = "jancsi123";
    jwtToken = JWTUtility.generateToken(username);
    user = new User(username, password);
    kingdom = new Kingdom(username + "s kingdom", user);
    kingdom.setId(1L);
    buildingTypeDTO = new BuildingTypeDTO();
    contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));
  }


  @Test
  public void listOfBuildingsShouldReturnUnauthorized_when_noTokedisProvided() throws Exception {

    mockMvc.perform(get("/kingdom/buildings"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void listOfBuildingsShouldReturnUnauthorized_when_inValidTokenIsProvided() throws Exception {
    String badToken = "badToken";

    mockMvc.perform(get("/kingdom/buildings")
        .header("Authorization", "Bearer " + badToken))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void listOfBuildingsShouldReturnProperResult_when_validTokenIsProvided() throws Exception {


    mockMvc.perform(get("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken))
        .andExpect(status().isOk());
  }

  @Test
  public void createBuildingShouldReturnUnauthorized_when_noTokedisProvided() throws Exception {

    mockMvc.perform(post("/kingdom/buildings"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void createBuildingShouldReturnUnauthorized_when_inValidTokenIsProvided() throws Exception {
    String badToken = "badToken";

    mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + badToken))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void createBuildingShouldReturnError_when_typeIsMissing() throws Exception {
    buildingTypeDTO.setType(null);
    String buildingTypeDTOJson = objectMapper.writeValueAsString(buildingTypeDTO);
    String expectedErrorMessage = "Missing parameter(s): type!";

    mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(buildingTypeDTOJson))
        .andExpect(status().is(400))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void createBuildingShouldReturnError_when_typeIsInvalid() throws Exception {
    buildingTypeDTO.setType("invalid type");
    String buildingTypeDTOJson = objectMapper.writeValueAsString(buildingTypeDTO);
    String expectedErrorMessage = "Invalid building type";

    when(buildingServiceMock.isValidBuildingType(any())).thenReturn(false);

    ResultActions resultAction =  mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(buildingTypeDTOJson));
    System.out.println(resultAction);

    resultAction.andExpect(status().is(406))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void createBuildingShouldReturnError_when_notEnoughResource() throws Exception {
    buildingTypeDTO.setType("mine");
    String buildingTypeDTOJson = objectMapper.writeValueAsString(buildingTypeDTO);
    String expectedErrorMessage = "Not enough resource";

    when(buildingServiceMock.isValidBuildingType(anyString())).thenReturn(true);
    when(productionServiceMock.isEnoughMoneyToCreateBuilding(anyLong())).thenReturn(false);

    ResultActions resultAction =  mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(buildingTypeDTOJson));
    System.out.println(resultAction);

        resultAction.andExpect(status().is(409))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void createBuildingShouldReturnProperResult_when_UserIsAutorizedAndTypeAndResourceIsOK() throws Exception {
    buildingTypeDTO.setType("mine");
    String buildingTypeDTOJson = objectMapper.writeValueAsString(buildingTypeDTO);
    Building building = new Mine(kingdom);
    BuildingDTO buildingDTO = new BuildingDTO();
    buildingDTO.setId(2L);
    buildingDTO.setLevel(building.getLevel());
    buildingDTO.setType(building.getType());
    buildingDTO.setStartedAt(Timestamp.valueOf(building.getStartedAt()));
    buildingDTO.setFinishedAt(Timestamp.valueOf(building.getFinishedAt()));

    when(buildingServiceMock.isValidBuildingType(any())).thenReturn(true);
    when(productionServiceMock.isEnoughMoneyToCreateBuilding(any())).thenReturn(true);
    when(buildingServiceMock.createBuilding(any(), any())).thenReturn(building);
    when(buildingServiceMock.createBuildingDTO(any())).thenReturn(buildingDTO);

    mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(buildingTypeDTOJson))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.id", is(buildingDTO.getId())))
        .andExpect(jsonPath("$.type", is(buildingDTO.getType())))
        .andExpect(jsonPath("$.level", is(buildingDTO.getLevel())))
        .andExpect(jsonPath("$.startedAt", is(buildingDTO.getStartedAt())))
        .andExpect(jsonPath("$.finishedAt", is(buildingDTO.getFinishedAt())));
  }

  @Test
  public void findBuildingShouldReturnUnauthorized_when_noTokedisProvided() throws Exception {

    mockMvc.perform(get("/kingdom/buildings/{id}",1L))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void findBuildingShouldReturnUnauthorized_when_inValidTokenIsProvided() throws Exception {
    String badToken = "badToken";

    mockMvc.perform(get("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + badToken))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void indBuildingShouldReturnError_when_idNotFound() throws Exception {
    String expectedErrorMessage = "No building with such id found in your kingdom!";

    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(null);

    mockMvc.perform(get("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken))
        .andExpect(status().is(404))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void indBuildingShouldReturnProperResult_when_validTokenIsProvided() throws Exception {
    Building building = new Mine(kingdom);

    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(null);

    mockMvc.perform(get("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType));
  }

}
