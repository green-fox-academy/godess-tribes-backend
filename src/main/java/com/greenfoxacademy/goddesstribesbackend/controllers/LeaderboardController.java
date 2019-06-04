package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardBuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardByBuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardBySoldiersDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardSoldiersDTO;
import com.greenfoxacademy.goddesstribesbackend.services.LeaderboardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController {

  private LeaderboardService leaderboardService;

  @Autowired
  public LeaderboardController(LeaderboardService leaderboardService) {
    this.leaderboardService = leaderboardService;
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = LeaderboardBuildingsDTO.class)})
  @GetMapping("/leaderboard/buildings")
  public ResponseEntity<Object> leaderboardOfBuildings() {
    LeaderboardByBuildingsDTO leaderboardByBuildingsDTO = leaderboardService.createLeaderboardByBuildings();
    return ResponseEntity.status(200).body(leaderboardByBuildingsDTO);
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = LeaderboardSoldiersDTO.class)})
  @GetMapping("/leaderboard/soldiers")
  public ResponseEntity<Object> leaderboardOfSoldiers() {
    LeaderboardBySoldiersDTO leaderboardBySoldiersDTO = leaderboardService.createLeaderboardBySoldiers();
    return ResponseEntity.status(200).body(leaderboardBySoldiersDTO);
  }
}
