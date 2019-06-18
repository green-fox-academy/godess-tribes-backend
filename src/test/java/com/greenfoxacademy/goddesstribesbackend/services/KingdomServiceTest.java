package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Townhall;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.repositories.KingdomRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KingdomServiceTest {

  private static KingdomService kingdomService;

  private static KingdomRepository kingdomRepositoryMock = Mockito.mock(KingdomRepository.class);
  private static UserService userServiceMock = Mockito.mock(UserService.class);
  private static BuildingService buildingServiceMock = Mockito.mock(BuildingService.class);
  private static ResourceService resourceServiceMock = Mockito.mock(ResourceService.class);
  private static SoldierService soldierServiceMock = Mockito.mock(SoldierService.class);
  private static ProductionService productionServiceMock = Mockito.mock(ProductionService.class);

  @BeforeClass
  public static void init() {
    kingdomService = new KingdomService(kingdomRepositoryMock, userServiceMock,
                                        buildingServiceMock, resourceServiceMock,
                                        soldierServiceMock, productionServiceMock);
  }

  @Test
  public void saveKingdomShouldReturnProperResult_when_KingdomIsSaved() {
    String username = "Juliska";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    User user = new User(username, password);
    Kingdom expectedKingdom = new Kingdom(kingdomName, user);

    when(userServiceMock.checkUserByName(any())).thenReturn(true);
    when(kingdomRepositoryMock.save(any())).thenReturn(expectedKingdom);

    Kingdom resultKingdom = kingdomService.saveKingdom(kingdomName, user);
    assertEquals(expectedKingdom, resultKingdom);
  }

  @Test
  public void saveKingdomShouldReturnNull_when_UsernameIsTaken() {
    String username = "Juliska";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    User user = new User(username, password);
    Kingdom expectedKingdom = null;

    when(userServiceMock.checkUserByName(any())).thenReturn(false);

    Kingdom resultKingdom = kingdomService.saveKingdom(kingdomName, user);
    assertEquals(expectedKingdom, resultKingdom);
    assertNull(resultKingdom);
  }

  @Test
  public void saveKingdomShouldReturnNull_when_UserIsNull() {
    String kingdomName = "Tündérország";
    User user = null;
    Kingdom expectedKingdom = null;

    Kingdom resultKingdom = kingdomService.saveKingdom(kingdomName, user);
    assertEquals(expectedKingdom, resultKingdom);
    assertNull(resultKingdom);
  }

  @Test
  public void saveKingdomShouldReturnProperResult_when_KingdomNameIsNull() {
    String username = "Juliska";
    String password = "jancsi123";
    String kingdomName = null;
    User user = new User(username, password);
    String expectedKingdomName = username + "'s kingdom";

    when(userServiceMock.checkUserByName(any())).thenReturn(true);
    when(kingdomRepositoryMock.save(any())).then(returnsFirstArg());

    Kingdom resultKingdom = kingdomService.saveKingdom(kingdomName, user);
    assertEquals(expectedKingdomName, resultKingdom.getKingdomName());
  }

  @Test
  public void renameKingdomShouldReturnProperResult_when_KingdomIsRenamed() {
    String username = "Juliska";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    User user = new User(username, password);
    Kingdom kingdom = new Kingdom(kingdomName, user);
    String newKingdomName = "Sárkányország";

    when(kingdomRepositoryMock.save(any())).then(returnsFirstArg());

    Kingdom renamedKingdom = kingdomService.renameKingdom(newKingdomName, kingdom);
    assertEquals(newKingdomName, renamedKingdom.getKingdomName());
  }

  @Test
  public void findKingdomByIdShouldReturnProperResult_when_KingdomIsFound() {
    String username = "Juliska";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    User user = new User(username, password);
    Kingdom expectedKingdom = new Kingdom(kingdomName, user);
    Optional<Kingdom> kingdomOptional = Optional.of(expectedKingdom);

    when(kingdomRepositoryMock.findById(1L)).thenReturn(kingdomOptional);

    Kingdom resultKingdom = kingdomService.findKingdomById(1L);
    assertEquals(expectedKingdom, resultKingdom);
  }

  @Test
  public void findKingdomByIdShouldReturnNull_when_KingdomIsNotFound() {
    String username = "Juliska";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    User user = new User(username, password);
    Kingdom expectedKingdom = null;

    when(kingdomRepositoryMock.findById(1L)).thenReturn(Optional.empty());

    Kingdom resultKingdom = kingdomService.findKingdomById(1L);
    assertEquals(expectedKingdom, resultKingdom);
    assertNull(resultKingdom);
  }

  @Test
  public void kingdomShouldBeInitialized_when_initKingdomIsCalled_when_KingdomIsInactive() {
    String username = "Julcsi";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    User user = new User(username, password);
    Kingdom kingdom = new Kingdom(kingdomName, user);
    Optional<Kingdom> kingdomOptional = Optional.of(kingdom);
    Townhall townhall = new Townhall(kingdom);

    when(userServiceMock.checkUserByName(any())).thenReturn(true);
    when(kingdomRepositoryMock.findKingdomByUser_Username(any())).thenReturn(kingdomOptional);
    when(buildingServiceMock.saveTownhall(any())).thenReturn(townhall);

    kingdomService.initKingdom(username);

    verify(userServiceMock, times(1)).checkUserByName(username);
    verify(kingdomRepositoryMock, times(1)).findKingdomByUser_Username(username);
    verify(buildingServiceMock, times(1)).saveTownhall(kingdom);
    verify(resourceServiceMock, times(1)).saveFoodAtStart(townhall);
    verify(resourceServiceMock, times(1)).saveGoldAtStart(townhall);
    verify(buildingServiceMock, times(1)).saveFarmAtStart(kingdom);
    verify(buildingServiceMock, times(1)).saveMineAtStart(kingdom);
    verify(kingdomRepositoryMock, times(1)).save(kingdom);
  }


  @Test
  public void kingdomShouldNotBeInitialized_when_initKingdomIsCalled_when_KingdomIsActive() {
    String username = "Julcsika";
    String password = "jancsi123";
    String kingdomName = "Tündérország";
    User user = new User(username, password);
    Kingdom kingdom = new Kingdom(kingdomName, user);
    kingdom.setActive(true);
    Optional<Kingdom> kingdomOptional = Optional.of(kingdom);
    Townhall townhall = new Townhall(kingdom);

    when(userServiceMock.checkUserByName(any())).thenReturn(true);
    when(kingdomRepositoryMock.findKingdomByUser_Username(any())).thenReturn(kingdomOptional);

    kingdomService.initKingdom(username);

    verify(userServiceMock, times(1)).checkUserByName(username);
    verify(kingdomRepositoryMock, times(1)).findKingdomByUser_Username(username);
    verify(buildingServiceMock, times(0)).saveTownhall(kingdom);
    verify(resourceServiceMock, times(0)).saveFoodAtStart(townhall);
    verify(resourceServiceMock, times(0)).saveGoldAtStart(townhall);
    verify(buildingServiceMock, times(0)).saveFarmAtStart(kingdom);
    verify(buildingServiceMock, times(0)).saveMineAtStart(kingdom);
    verify(kingdomRepositoryMock, times(0)).save(kingdom);
  }

}
