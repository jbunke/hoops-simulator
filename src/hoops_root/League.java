package hoops_root;

import java.util.ArrayList;
import java.util.List;

public class League {
  private String name;
  private int year; // ((year-1)/year) season; example: 2019 -> 2018/2019 season
  private Conference[] conferences;
  private List<Game[]> schedule;
  private List<PlayoffGame[]> playoffSchedule;
  private Series[] playoffSeries;
  private List<Team> teams;
  private List<Player> players;
  private int gameDaysPlayed;
  private int totalGamesPerTeam;
  private Team champion;

  League(String name, int year,
         Conference conference1, Conference conference2, int totalGamesPerTeam) {
    this.name = name;
    this.year = year;
    this.conferences = new Conference[] {conference1, conference2};
    this.teams = new ArrayList<>();
    this.players = new ArrayList<>();
    this.schedule = new ArrayList<>();
    this.totalGamesPerTeam = totalGamesPerTeam;
    this.gameDaysPlayed = 0;

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

  List<Player> getPlayers() {
    return players;
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
    for (Game game : schedule.get(gameDaysPlayed)) {
      game.simulate();
    }
    conferences[0].updateStandings();
    conferences[1].updateStandings();
    gameDaysPlayed++;
  }

  private void simulatePlayoffSeries() {
    for (PlayoffGame[] gameNum : playoffSchedule) {
      for (PlayoffGame game : gameNum) {
        game.simulate();
      }
    }
  }

  private void simulateGameDays(int n) {
    for (int i = 0; i < n; i++) {
      simulateGameDay();
    }
  }

  void simulateRegularSeason() {
    if (gameDaysPlayed != 0) {
      setSchedule(totalGamesPerTeam);
    }
    simulateGameDays(totalGamesPerTeam);
  }

  private void determinePlayoffSeries(List<Team> conf1Teams,
                                             List<Team> conf2Teams) {
    assert (conf1Teams.size() == conf2Teams.size());
    int seriesCount = conf1Teams.size();
    playoffSeries = new Series[seriesCount];

    // ESTABLISH SERIES
    for (int i = 0; i < seriesCount / 2; i++) {
      playoffSeries[i] = new Series(conf1Teams.get(i),
              conf1Teams.get(seriesCount - (i + 1)));
      playoffSeries[i + (seriesCount / 2)] = new Series(conf2Teams.get(i),
              conf2Teams.get(seriesCount - (i + 1)));
    }
  }

  private void simulatePostSeason() {
    assert (gameDaysPlayed == totalGamesPerTeam);
    // SEED TEAMS
    conferences[0].updateStandings();
    conferences[1].updateStandings();
    List<Team> playoffsConf1 = conferences[0].getPlayoffTeams();
    List<Team> playoffsConf2 = conferences[1].getPlayoffTeams();
    assert (playoffsConf1.size() == playoffsConf2.size());

    for (int i = 0; i < playoffsConf1.size(); i++) {
      playoffsConf1.get(i).setSeed(i + 1);
      playoffsConf2.get(i).setSeed(i + 1);
    }

    // PLAYOFF INITIALIZER
    Team conf1Champ;
    Team conf2Champ;
    int round = 0;

    while (playoffsConf1.size() > 1) {
      round++;
      System.out.println("ROUND " + round + ":\n---------");
      determinePlayoffSeries(playoffsConf1, playoffsConf2);
      this.playoffSchedule = Scheduler.playoffRound(
              playoffsConf1, playoffsConf2,
              playoffSeries);
      simulatePlayoffSeries();
      for (Series series : playoffSeries) {
        series.printSeries();
      }

      if (playoffsConf1.size() > 2) {
        playoffsConf1 = new ArrayList<>();
        playoffsConf2 = new ArrayList<>();
        int offset = playoffSeries.length / 2;
        for (int i = 0; i < offset; i++) {
          playoffsConf1.add(playoffSeries[i].winner());
          playoffsConf2.add(playoffSeries[i + offset].winner());
        }
      } else {
        // FINALS
        System.out.println("FINALS:\n---------");
        conf1Champ = playoffSeries[0].winner();
        conf2Champ = playoffSeries[1].winner();

        if (conf1Champ.getRecord().wins() >= conf2Champ.getRecord().wins()) {
          playoffSeries = new Series[] { new Series(conf1Champ, conf2Champ) };
        } else {
          playoffSeries = new Series[] { new Series(conf2Champ, conf1Champ) };
        }
        this.playoffSchedule = Scheduler.finals(playoffSeries[0]);
        simulatePlayoffSeries();
        for (Series series : playoffSeries) {
          series.printSeries();
        }
        champion = playoffSeries[0].winner();
        printChampion();
        break;
      }
    }
  }

  void simulateSeason() {
    simulateRegularSeason();
    simulatePostSeason();
  }

  void newSeason() {
    year++;
    gameDaysPlayed = 0;
    for (Team team : teams) {
      team.newSeason();
    }
    setSchedule(totalGamesPerTeam);
  }

  private void printChampion() {
    System.out.println("The " + year + " " + name + " Champions are the " +
            champion.name() + ".\n");
  }

  void printTeamsQuality() {
    for (Team team : teams) {
      System.out.println(team.name() + " " + team.quality());
    }
    System.out.println();
  }
}
