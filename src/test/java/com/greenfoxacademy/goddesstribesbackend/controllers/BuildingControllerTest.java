package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingTypeDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LevelDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
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

import java.nio.charset.Charset;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);

    mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(buildingTypeDTOJson))
        .andExpect(status().is(400))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
        .andDo(print());
  }

  @Test
  public void createBuildingShouldReturnError_when_typeIsInvalid() throws Exception {
    buildingTypeDTO.setType("invalid type");
    String buildingTypeDTOJson = objectMapper.writeValueAsString(buildingTypeDTO);
    String expectedErrorMessage = "Invalid building type";

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.isValidBuildingType(any())).thenReturn(false);

    mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(buildingTypeDTOJson))
        .andExpect(status().is(406))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
        .andDo(print());
  }

  @Test
  public void createBuildingShouldReturnError_when_notEnoughResource() throws Exception {
    buildingTypeDTO.setType("mine");
    String buildingTypeDTOJson = objectMapper.writeValueAsString(buildingTypeDTO);
    String expectedErrorMessage = "Not enough resource";

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.isValidBuildingType(anyString())).thenReturn(true);
    when(productionServiceMock.isEnoughMoneyToCreateBuilding(anyLong())).thenReturn(false);

    mockMvc.perform(post("/kingdom/buildings")
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(buildingTypeDTOJson))
        .andExpect(status().is(409))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)))
        .andDo(print());

  }

  @Test
  public void createBuildingShouldReturnProperResult_when_UserIsAutorizedAndTypeAndResourceIsOK() throws Exception {
    buildingTypeDTO.setType("mine");
    String buildingTypeDTOJson = objectMapper.writeValueAsString(buildingTypeDTO);
    Building building = new Mine(kingdom);
    int id = 2;
    BuildingTypeENUM type = BuildingTypeENUM.MINE;
    int level = building.getLevel();
    Timestamp startedAt = Timestamp.valueOf(building.getStartedAt());
    Timestamp finishedAt = Timestamp.valueOf(building.getFinishedAt());
    BuildingDTO buildingDTO = new BuildingDTO();
    buildingDTO.setId((long)id);
    buildingDTO.setLevel(level);
    buildingDTO.setType(type);
    buildingDTO.setStartedAt(startedAt);
    buildingDTO.setFinishedAt(finishedAt);

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
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
        .andExpect(jsonPath("$.id", is(id)))
        .andExpect(jsonPath("$.type", is(type.name())))
        .andExpect(jsonPath("$.level", is(level)))
        .andExpect(jsonPath("$.startedAt", is(startedAt.getTime())))
        .andExpect(jsonPath("$.finishedAt", is(finishedAt.getTime())));
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
  public void findBuildingShouldReturnError_when_idNotFound() throws Exception {
    String expectedErrorMessage = "No building with such id found in your kingdom!";

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(null);

    mockMvc.perform(get("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken))
        .andExpect(status().is(404))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void findBuildingShouldReturnProperResult_when_validTokenIsProvided() throws Exception {
    Building building = new Mine(kingdom);
    int id = 2;
    BuildingTypeENUM type = BuildingTypeENUM.MINE;
    int level = building.getLevel();
    Timestamp startedAt = Timestamp.valueOf(building.getStartedAt());
    Timestamp finishedAt = Timestamp.valueOf(building.getFinishedAt());
    BuildingDTO buildingDTO = new BuildingDTO();
    buildingDTO.setId((long)id);
    buildingDTO.setLevel(level);
    buildingDTO.setType(type);
    buildingDTO.setStartedAt(startedAt);
    buildingDTO.setFinishedAt(finishedAt);


    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(building);
    when(buildingServiceMock.createBuildingDTO(any())).thenReturn(buildingDTO);

    mockMvc.perform(get("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.id", is(id)))
        .andExpect(jsonPath("$.type", is(type.name())))
        .andExpect(jsonPath("$.level", is(level)))
        .andExpect(jsonPath("$.startedAt", is(startedAt.getTime())))
        .andExpect(jsonPath("$.finishedAt", is(finishedAt.getTime())));
  }

  @Test
  public void buildingUpgradeShouldReturnUnauthorized_when_noTokedisProvided() throws Exception {

    mockMvc.perform(put("/kingdom/buildings/{id}", 1L))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void buildingUpgradeShouldReturnUnauthorized_when_inValidTokenIsProvided() throws Exception {
    String badToken = "badToken";

    mockMvc.perform(put("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + badToken))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void buildingUpgradeShouldReturnError_when_levelIsNull() throws Exception {
    LevelDTO levelDTO = new LevelDTO();
    levelDTO.setLevel(null);
    String levelDTOJson = objectMapper.writeValueAsString(levelDTO);
    Building buildingToUpgrade = new Mine(kingdom);
    String expectedErrorMessage = "Missing parameter(s): <level>!";

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(buildingToUpgrade);

    mockMvc.perform(put("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(levelDTOJson))
        .andExpect(status().is(400))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void buildingUpgradeShouldReturnError_when_noBuildingWithId() throws Exception {
    LevelDTO levelDTO = new LevelDTO();
    levelDTO.setLevel(2);
    String levelDTOJson = objectMapper.writeValueAsString(levelDTO);
    String expectedErrorMessage = "No building with such id found in your kingdom!";

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(null);

    mockMvc.perform(put("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(levelDTOJson))
        .andExpect(status().is(404))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void buildingUpgradeShouldReturnError_when_levelIsInvalid() throws Exception {
    LevelDTO levelDTO = new LevelDTO();
    levelDTO.setLevel(4);
    String levelDTOJson = objectMapper.writeValueAsString(levelDTO);
    Building buildingToUpgrade = new Mine(kingdom);
    String expectedErrorMessage = "Invalid building level: can upgrade only 1 grade at a time, and other buildings level must be less than or equal with townhall level!";

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(buildingToUpgrade);
    when(buildingServiceMock.isValidLevel(anyInt(), anyInt(), anyLong(), any())).thenReturn(false);

    mockMvc.perform(put("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(levelDTOJson))
        .andExpect(status().is(406))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void buildingUpgradeShouldReturnError_when_notEnoughResource() throws Exception {
    LevelDTO levelDTO = new LevelDTO();
    levelDTO.setLevel(2);
    String levelDTOJson = objectMapper.writeValueAsString(levelDTO);
    Building buildingToUpgrade = new Mine(kingdom);
    String expectedErrorMessage = "Not enough resource!";

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(buildingToUpgrade);
    when(buildingServiceMock.isValidLevel(anyInt(), anyInt(), anyLong(), any())).thenReturn(true);
    when(productionServiceMock.isEnoughMoneyToUpgradeBuilding(anyLong(), anyLong())).thenReturn(false);

    mockMvc.perform(put("/kingdom/buildings/{id}", 1L)
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(levelDTOJson))
        .andExpect(status().is(409))
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.status", is("error")))
        .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
  }

  @Test
  public void mineUpgradeShouldReturnProperResult_when_levelIsValidHaveEnoughResource() throws Exception {
    LevelDTO levelDTO = new LevelDTO();
    levelDTO.setLevel(2);
    String levelDTOJson = objectMapper.writeValueAsString(levelDTO);
    Building buildingToUpgrade = new Mine(kingdom);
    int id = 2;
    ProductionBuilding upgradedProductionBuilding = new Mine(kingdom);
    Townhall upgradedTownhall = new Townhall(kingdom);
    BuildingTypeENUM type = BuildingTypeENUM.MINE;
    int level = levelDTO.getLevel();
    Timestamp startedAt = Timestamp.valueOf(upgradedProductionBuilding.getStartedAt());
    Timestamp finishedAt = Timestamp.valueOf(upgradedProductionBuilding.getFinishedAt());
    BuildingDTO buildingDTO = new BuildingDTO();
    buildingDTO.setId((long)id);
    buildingDTO.setLevel(level);
    buildingDTO.setType(type);
    buildingDTO.setStartedAt(startedAt);
    buildingDTO.setFinishedAt(finishedAt);

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(buildingToUpgrade);
    when(buildingServiceMock.isValidLevel(anyInt(), anyInt(), anyLong(), any())).thenReturn(true);
    when(productionServiceMock.isEnoughMoneyToUpgradeBuilding(anyLong(), anyLong())).thenReturn(true);
    when(buildingServiceMock.upgradeTownhall(anyLong(), anyLong(), anyInt())).thenReturn(upgradedTownhall);
    when(buildingServiceMock.upgradeProductionBuilding(anyLong(), anyLong(), anyInt())).thenReturn(upgradedProductionBuilding);
    when(buildingServiceMock.upgradeBuilding(anyLong(), anyLong(), anyInt())).thenReturn(upgradedProductionBuilding);
    when(buildingServiceMock.createBuildingDTO(any())).thenReturn(buildingDTO);

    mockMvc.perform(put("/kingdom/buildings/{id}", (long)id)
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(levelDTOJson))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.id", is(id)))
        .andExpect(jsonPath("$.type", is(type.name())))
        .andExpect(jsonPath("$.level", is(level)))
        .andExpect(jsonPath("$.startedAt", is(startedAt.getTime())))
        .andExpect(jsonPath("$.finishedAt", is(finishedAt.getTime())));
  }

  @Test
  public void townhallUpgradeShouldReturnProperResult_when_levelIsValidHaveEnoughResource() throws Exception {
    LevelDTO levelDTO = new LevelDTO();
    levelDTO.setLevel(2);
    String levelDTOJson = objectMapper.writeValueAsString(levelDTO);
    Building buildingToUpgrade = new Townhall(kingdom);
    int id = 2;
    ProductionBuilding upgradedProductionBuilding = new Mine(kingdom);
    Townhall upgradedTownhall = new Townhall(kingdom);
    BuildingTypeENUM type = BuildingTypeENUM.MINE;
    int level = levelDTO.getLevel();
    Timestamp startedAt = Timestamp.valueOf(upgradedProductionBuilding.getStartedAt());
    Timestamp finishedAt = Timestamp.valueOf(upgradedProductionBuilding.getFinishedAt());
    BuildingDTO buildingDTO = new BuildingDTO();
    buildingDTO.setId((long)id);
    buildingDTO.setLevel(level);
    buildingDTO.setType(type);
    buildingDTO.setStartedAt(startedAt);
    buildingDTO.setFinishedAt(finishedAt);

    when(kingdomServiceMock.findKingdomByUsername(any())).thenReturn(kingdom);
    when(buildingServiceMock.findBuildingByKingdomAndBuildingId(anyLong(), anyLong())).thenReturn(buildingToUpgrade);
    when(buildingServiceMock.isValidLevel(anyInt(), anyInt(), anyLong(), any())).thenReturn(true);
    when(productionServiceMock.isEnoughMoneyToUpgradeBuilding(anyLong(), anyLong())).thenReturn(true);
    when(buildingServiceMock.upgradeTownhall(anyLong(), anyLong(), anyInt())).thenReturn(upgradedTownhall);
    when(buildingServiceMock.upgradeProductionBuilding(anyLong(), anyLong(), anyInt())).thenReturn(upgradedProductionBuilding);
    when(buildingServiceMock.upgradeBuilding(anyLong(), anyLong(), anyInt())).thenReturn(upgradedProductionBuilding);
    when(buildingServiceMock.createBuildingDTO(any())).thenReturn(buildingDTO);

    mockMvc.perform(put("/kingdom/buildings/{id}", (long)id)
        .header("Authorization", "Bearer " + jwtToken)
        .contentType(contentType)
        .content(levelDTOJson))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.id", is(id)))
        .andExpect(jsonPath("$.type", is(type.name())))
        .andExpect(jsonPath("$.level", is(level)))
        .andExpect(jsonPath("$.startedAt", is(startedAt.getTime())))
        .andExpect(jsonPath("$.finishedAt", is(finishedAt.getTime())));
  }

}
