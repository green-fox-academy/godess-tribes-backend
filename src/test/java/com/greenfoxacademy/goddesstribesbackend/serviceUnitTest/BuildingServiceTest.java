package com.greenfoxacademy.goddesstribesbackend.serviceUnitTest;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.*;
import com.greenfoxacademy.goddesstribesbackend.services.BuildingService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTest {

  @Mock
  private KingdomRepository kingdomRepositoryMock;
  @Mock
  private BuildingRepository buildingRepositoryMock;
  @Mock
  private FarmRepository farmRepositoryMock;
  @Mock
  private MineRepository mineRepositoryMock;
  @Mock
  private ResourceRepository resourceRepositoryMock;
  @Mock
  private TownhallRepository townhallRepositoryMock;
  @Mock
  private ProductionBuildingRepository productionBuildingRepositoryMock;

  @InjectMocks
  private BuildingService buildingService;

  private static User user;
  private static Kingdom kingdom;
  private static Townhall townhall;
  private static Mine mine;
  private static Farm farm;

  @BeforeClass
  public static void init() {
    user = new User("jancsi", "juliska123");
    kingdom = new Kingdom("Jancsi's kingdom", user);
    kingdom.setId(1L);
  }

  @Before
  public void initEachTime(){
    townhall = new Townhall(kingdom);
    townhall.setId(1L);
    farm = new Farm(kingdom);
    farm.setId(2L);
    mine = new Mine(kingdom);
    mine.setId(3L);
    List<Building> buildings = new ArrayList<>();
    buildings.add(townhall);
    buildings.add(mine);
    buildings.add(farm);
  }

  @Test
  public void buildingTypeCheckShouldReturnFalse_when_typeIsNotInENUM(){
    String type = "invalid";

    assertFalse(buildingService.isValidBuildingType(type));
  }

  @Test
  public void buildingTypeCheckShouldReturnTrue_when_typeIsInENUM() {
    String type = BuildingTypeENUM.MINE.name();

    assertTrue(buildingService.isValidBuildingType(type));
  }

  @Test
  public void saveTownhallShouldReturnProperResult_when_kindomIsGiven(){
    Townhall expected = new Townhall(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));

    when(buildingRepositoryMock.save(any())).thenReturn(expected);

    Townhall result = buildingService.saveTownhall(kingdom);

    assertEquals(expected, result);
  }

  @Test
  public void saveFarmShouldReturnProperResult_when_kindomIsGiven(){
    Farm expected = new Farm(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));

    when(buildingRepositoryMock.save(any())).thenReturn(expected);

    Farm result = buildingService.saveFarmAtStart(kingdom);

    assertEquals(expected, result);
  }

  @Test
  public void isValidLevelShouldReturnFalse_when_upgradedLevelIsHigherThenTownhallLevelInCaseOfNotTownhallUpgrade(){
    int askedUpgradeLevel = 2;
    int currentLevel = mine.getLevel();
    ArrayList<Townhall> townhalllist = new ArrayList<>(Arrays.asList(townhall));

    when(townhallRepositoryMock.findTownhallsByKingdom_Id(any())).thenReturn(townhalllist);

    assertFalse(buildingService.isValidLevel(askedUpgradeLevel, currentLevel, kingdom.getId(), mine.getType()));
  }

}
