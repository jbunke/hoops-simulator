import java.util.ArrayList;
import java.util.List;

public class League {
  private String name;
  private int year; // ((year-1)/year) season; example: 2019 -> 2018/2019 season
  private Conference[] conferences;
  private List<Game[]> schedule;
  private List<Team> teams;
  private List<Player> players;
  private int gamedaysPlayed;
  private int totalGamesPerTeam;

  League(String name, int year,
         Conference conference1, Conference conference2, int totalGamesPerTeam) {
    this.name = name;
    this.year = year;
    this.conferences = new Conference[] {conference1, conference2};
    this.teams = new ArrayList<>();
    this.players = new ArrayList<>();
    this.schedule = new ArrayList<>();
    this.totalGamesPerTeam = totalGamesPerTeam;
    this.gamedaysPlayed = 0;

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

    setSchedule(totalGamesPerTeam);
  }

  private void setSchedule(int totalGamesPerTeam) {
    this.schedule = new ArrayList<>();
    int gameDays = 0;
    while (gameDays < totalGamesPerTeam) {
      for (int i = 0; i < 29; i++) {
        if (gameDays >= totalGamesPerTeam) { break; }
        Game[] gameDay = Scheduler.gameDay(gameDays, teams);
        schedule.add(gameDay);
        gameDays++;
      }
    }
  }

  private void simulateGameDay() {
    for (Game game : schedule.get(gamedaysPlayed)) {
      game.randomSim();
    }
    conferences[0].updateStandings();
    conferences[1].updateStandings();
    gamedaysPlayed++;
  }

  void simulateGameDays(int n) {
    for (int i = 0; i < n; i++) {
      simulateGameDay();
    }
  }
}
