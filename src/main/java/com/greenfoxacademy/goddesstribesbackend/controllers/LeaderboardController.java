package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardBuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardSoldiersDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController {

  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {@ApiResponse(code = 200, message ="OK", response = LeaderboardBuildingsDTO.class)})
  @GetMapping("/leaderboard/buildings")
  public ResponseEntity<Object> mockLeaderboardOfBuildings() {
    return ResponseEntity.status(200).body(MockData.leaderboardBuildingsDTO);
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {@ApiResponse(code = 200, message ="OK", response = LeaderboardSoldiersDTO.class)})
  @GetMapping("/leaderboard/soldiers")
  public ResponseEntity<Object> mockLeaderboardOfSoldiers() {
    return ResponseEntity.status(200).body(MockData.leaderboardSoldiersDTO);
  }

}
