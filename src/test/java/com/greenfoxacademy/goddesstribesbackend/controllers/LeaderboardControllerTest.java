package com.greenfoxacademy.goddesstribesbackend.controllers;


import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardBuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardByBuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardBySoldiersDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardSoldiersDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
import com.greenfoxacademy.goddesstribesbackend.services.LeaderboardService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LeaderboardController.class)
public class LeaderboardControllerTest {

  private static String username;
  private static String password;
  private static String jwtToken;
  private static User user;
  private static Kingdom kingdom;
  private static MediaType contentType;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LeaderboardService leaderboardService;

  @BeforeClass
  public static void init() {
    String username = "Agi";
    String password = "lujzi123";
    jwtToken = JWTUtility.generateToken(username);
    User user = new User(username, password);
    contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
  }

  @Test
  public void buildingListSholudReturnUnauthorizedIfNoTokenGiven() throws Exception {
    mockMvc.perform(get("/leaderboard/buildings"))
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void buildingListShouldReturnLeaderboardByBuildingsDTO() throws Exception {
    LeaderboardBuildingsDTO leaderboardBuildingsDTO = new LeaderboardBuildingsDTO("Agi's kingdom", 3);
    List<LeaderboardBuildingsDTO> buildingsList = new ArrayList<>(Arrays.asList(leaderboardBuildingsDTO));
    LeaderboardByBuildingsDTO leaderboardByBuildingsDTO = new LeaderboardByBuildingsDTO(buildingsList);
    when(leaderboardService.createLeaderboardByBuildings()).thenReturn(leaderboardByBuildingsDTO);

    mockMvc.perform(get("/leaderboard/buildings")
            .header("Authorization", "Bearer " + jwtToken))
            .andExpect(status().is(200))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.leaderboard[0].kingdomName", is("Agi's kingdom")))
            .andExpect(jsonPath("$.leaderboard[0].buildings", is(3)));
  }

  @Test
  public void solidierListSholudReturnUnauthorizedIfNoTokenGiven() throws Exception {
    mockMvc.perform(get("/leaderboard/soldiers"))
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void soldierListShouldReturnLeaderboardByBuildingsDTO() throws Exception {
    LeaderboardSoldiersDTO leaderboardSoldiersDTO = new LeaderboardSoldiersDTO("Agi's kingdom", 5);
    List<LeaderboardSoldiersDTO> soldiersList = new ArrayList<>(Arrays.asList(leaderboardSoldiersDTO));
    LeaderboardBySoldiersDTO leaderboardBySoldiersDTO = new LeaderboardBySoldiersDTO(soldiersList);
    when(leaderboardService.createLeaderboardBySoldiers()).thenReturn(leaderboardBySoldiersDTO);

    mockMvc.perform(get("/leaderboard/soldiers")
            .header("Authorization", "Bearer " + jwtToken))
            .andExpect(status().is(200))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.leaderboard[0].kingdomName", is("Agi's kingdom")))
            .andExpect(jsonPath("$.leaderboard[0].soldiers", is(5)));
  }

}
