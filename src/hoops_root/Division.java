package hoops_root;

import java.util.List;

public class Division {
  private String name;
  private List<Team> teams;

  Division(String name, List<Team> teams) {
    this.name = name;
    this.teams = teams;
  }

  void addTeam(Team team) {
    if (!teams.contains(team)) {
      teams.add(team);
    }
  }

  List<Team> getTeams() { return teams; }
}
