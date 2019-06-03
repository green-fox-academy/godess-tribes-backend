package com.greenfoxacademy.goddesstribesbackend.models;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

  public static final int COST_OF_NEW_BUILDING = 250;

  public static BuildingDTO townhall = new BuildingDTO(1L, BuildingTypeENUM.TOWNHALL, 1,
      Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusMinutes(1L)));;
  public static BuildingDTO farm = new BuildingDTO(2L, BuildingTypeENUM.FARM, 1,
      Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusMinutes(1L)));
  public static BuildingDTO mine = new BuildingDTO(3L, BuildingTypeENUM.MINE, 1,
      Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusMinutes(1L)));
  public static BuildingDTO barrack = new BuildingDTO(4L, BuildingTypeENUM.BARRACK, 1,
      Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusMinutes(1L)));

  public static List<BuildingDTO> buildingList = new ArrayList<>(Arrays.asList(farm, mine, barrack));

  public static BuildingsDTO buildingsDTO = new BuildingsDTO(buildingList);

  public static LocationDTO locationDTO = new LocationDTO(15,15);

  public static ResourceDTO food = new ResourceDTO(ResourceTypeENUM.FOOD, 200, 10);
  public static ResourceDTO gold = new ResourceDTO(ResourceTypeENUM.GOLD, 500, 10);

  public static List<ResourceDTO> resourceList = new ArrayList<>(Arrays.asList(food, gold));
  public static ResourcesDTO resourcesDTO = new ResourcesDTO(resourceList);

  public static SoldierDTO soldierDTO = new SoldierDTO(1L, 1, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now().plusMinutes(1L)));

  public static List<SoldierDTO> soldierList = new ArrayList<>(Arrays.asList(soldierDTO));
  public static SoldiersDTO soldiersDTO = new SoldiersDTO(soldierList);
  public static UserDTO userDTO = new UserDTO(1L, "jancsika", 1L, 0);

  public static KingdomDTO kingdomDTO = new KingdomDTO(1L, "Jancsika's Kingdom",
      1L, buildingList, resourceList, soldierList, locationDTO);

  public static TokenDTO tokenDTO = new TokenDTO();

  public static LeaderboardSoldiersDTO leaderboardSoldiersDTO = new LeaderboardSoldiersDTO("Jancsika's kingdom", 20 );

  public static LeaderboardBuildingsDTO leaderboardBuildingsDTO = new LeaderboardBuildingsDTO("Jancsika's kingdom", 3 );

}
