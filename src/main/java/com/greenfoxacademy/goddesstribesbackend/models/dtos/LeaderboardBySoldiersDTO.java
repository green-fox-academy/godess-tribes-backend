package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import java.util.List;

public class LeaderboardBySoldiersDTO {
  private List<LeaderboardSoldiersDTO> leaderboard;

  public LeaderboardBySoldiersDTO() {
  }

  public LeaderboardBySoldiersDTO(List<LeaderboardSoldiersDTO> leaderboard) {
    this.leaderboard = leaderboard;
  }

  public List<LeaderboardSoldiersDTO> getLeaderboard() {
    return leaderboard;
  }

  public void setLeaderboard(List<LeaderboardSoldiersDTO> leaderboard) {
    this.leaderboard = leaderboard;
  }
}
