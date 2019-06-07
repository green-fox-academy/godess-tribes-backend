package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourceDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourcesDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ResourceController.class)
public class ResourceControllerTest {

  private static String username;
  private static String password;
  private static String jwtToken;
  private static User user;
  private static Kingdom kingdom;
  private static MediaType contentType;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private KingdomService kingdomService;
  @MockBean
  private ProductionService productionService;

  @BeforeClass
  public static void init() {
    username = "Juliska";
    password = "jancsi123";
    jwtToken = JWTUtility.generateToken(username);
    user = new User(username, password);
    kingdom = new Kingdom(username + "s kingdom", user);
    contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
  }

  @Test
  public void listResourcesShouldReturnUnauthorized_when_noTokenGiven() throws Exception {
    mockMvc.perform(get("/kingdom/resources"))
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void listResourcesShouldReturnProperResult_when_tokenIsGiven() throws Exception {
    ResourceDTO food = new ResourceDTO(ResourceTypeENUM.FOOD, 200, 10);
    ResourceDTO gold = new ResourceDTO(ResourceTypeENUM.GOLD, 500, 20);
    List<ResourceDTO> resourceList = new ArrayList<>(Arrays.asList(food, gold));
    ResourcesDTO resourcesDTO = new ResourcesDTO(resourceList);

    when(kingdomService.findKingdomByUsername(any())).thenReturn(kingdom);
    when(productionService.createResourcesDTO(any())).thenReturn(resourcesDTO);

    mockMvc.perform(get("/kingdom/resources")
            .header("Authorization", "Bearer " + jwtToken))
            .andExpect(status().is(200))
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.resources[0].type", is(ResourceTypeENUM.FOOD.name())))
            .andExpect(jsonPath("$.resources[0].amount", is(200)))
            .andExpect(jsonPath("$.resources[0].generationRate", is(10)))
            .andExpect(jsonPath("$.resources[1].type", is(ResourceTypeENUM.GOLD.name())))
            .andExpect(jsonPath("$.resources[1].amount", is(500)))
            .andExpect(jsonPath("$.resources[1].generationRate", is(20)));
  }

}
