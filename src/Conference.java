import java.util.ArrayList;
import java.util.List;

public class Conference {
  private String name;
  private List<Division> divisions;
  private List<Team> teams;
  private int playoffCutoff; // inclusive cutoff for # of teams that make playoffs

  Conference(String name, List<Division> divisions) {
    this.name = name;
    this.divisions = divisions;
    this.teams = new ArrayList<>();

    for (Division division : divisions) {
      List<Team> divTeams = division.getTeams();

      for (Team team : divTeams) {
        if (!teams.contains(team)) {
          teams.add(team);
        }
      }
    }

    int teamCount = teams.size();

    for (int i = 6; i > 0; i--) {
      if (Math.pow(2, i) <= teamCount) {
        playoffCutoff = (int) Math.pow(2, i);
        break;
      }
    }
  }

  List<Team> getTeams() { return teams; }
}
