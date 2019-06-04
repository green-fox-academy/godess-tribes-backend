package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import java.util.List;

public class LeaderboardByBuildingsDTO {

  private List<LeaderboardBuildingsDTO> leaderboard;

  public LeaderboardByBuildingsDTO() {
  }

  public LeaderboardByBuildingsDTO(List<LeaderboardBuildingsDTO> leaderboard) {
    this.leaderboard = leaderboard;
  }

  public List<LeaderboardBuildingsDTO> getLeaderboard() {
    return leaderboard;
  }

  public void setLeaderboard(List<LeaderboardBuildingsDTO> leaderboard) {
    this.leaderboard = leaderboard;
  }

}
