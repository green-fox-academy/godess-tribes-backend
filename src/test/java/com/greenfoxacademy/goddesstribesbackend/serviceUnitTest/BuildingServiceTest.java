package com.greenfoxacademy.goddesstribesbackend.serviceUnitTest;

import com.greenfoxacademy.goddesstribesbackend.repositories.*;
import com.greenfoxacademy.goddesstribesbackend.services.BuildingService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTest {

  @Mock
  private KingdomRepository kingdomRepository;
  private BuildingRepository buildingRepository;
  private FarmRepository farmRepository;
  private MineRepository mineRepository;
  private ResourceRepository resourceRepository;
  private TownhallRepository townhallRepository;
  private ProductionBuildingRepository productionBuildingRepository;

  @InjectMocks
  private BuildingService buildingService;

  @BeforeClass
  public void buildingTypeCheckShouldReturnError_when_typeIsNotInENUM{

  }

}
