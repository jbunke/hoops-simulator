import java.util.ArrayList;
import java.util.List;

public class League {
  private String name;
  private int year; // ((year-1)/year) season; example: 2019 -> 2018/2019 season
  private Conference[] conferences;
  private List<Game[]> schedule;
  private List<Team> teams;
  private List<Player> players;

  League(String name, int year,
         Conference conference1, Conference conference2) {
    this.name = name;
    this.year = year;
    this.conferences = new Conference[] {conference1, conference2};
    this.teams = new ArrayList<>();
    this.players = new ArrayList<>();
    this.schedule = new ArrayList<>();

    for (Conference conference : conferences) {
      List<Team> conferenceTeams = conference.getTeams();

      for (Team team : conferenceTeams) {
        if (!teams.contains(team)) {
          teams.add(team);

          for (Player player : team.getRoster()) {
            if (!players.contains(player)) {
              players.add(player);
            }
          }
        }
      }
    }

    setSchedule();
  }

  private void setSchedule() {
    this.schedule = new ArrayList<>();
    int gameDays = 0;
    while (gameDays < 82) {
      for (int i = 0; i < 29; i++) {
        if (gameDays >= 82) { break; }
        Game[] gameDay = Scheduler.gameDay(gameDays, teams);
        schedule.add(gameDay);
        gameDays++;
      }
    }
  }
}
