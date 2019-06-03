package com.greenfoxacademy.goddesstribesbackend.factories.buildingfactory;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Building;

public abstract class AbstractBuildingFactory {

  abstract Building getBuilding(BuildingTypeENUM type);
  abstract Building upgradeBuilding(BuildingTypeENUM type);


}
