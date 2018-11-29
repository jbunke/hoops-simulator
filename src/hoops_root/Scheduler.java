package hoops_root;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

  static List<PlayoffGame[]> finals(Series series) {
    List<PlayoffGame[]> finalsGames = new ArrayList<>();

    int game = 0;
    while (game < 7) {
      PlayoffGame[] finalGame = new PlayoffGame[1];

      switch (game) {
        case 0:
        case 1:
        case 4:
        case 6:
          finalGame[0] = new PlayoffGame(series.getLowSeed(),
                  series.getHighSeed(), series);
          break;
        default:
          finalGame[0] = new PlayoffGame(series.getHighSeed(),
                  series.getLowSeed(), series);
          break;
      }
      finalsGames.add(finalGame);
      game++;
    }

    return finalsGames;
  }

  static List<PlayoffGame[]> playoffRound(List<Team> conf1Teams,
                                          List<Team> conf2Teams,
                                          Series[] series) {
    // 2-2-1-1-1 court 7 game series format
    assert (conf1Teams.size() == conf2Teams.size());
    assert (conf1Teams.size() % 2 == 0);
    int game = 0;
    int conf2Offset = conf1Teams.size() / 2;
    int confSize = conf1Teams.size();
    // TODO - Delete: hoops_root.Series[] series = new hoops_root.Series[confSize];
    List<PlayoffGame[]> round = new ArrayList<>();

    // SCHEDULE GAMES
    while (game < 7) {
      PlayoffGame[] playoff = new PlayoffGame[conf1Teams.size()];

      for (int i = 0; i < conf1Teams.size() / 2; i++) {
        switch (game) {
          case 0:
          case 1:
          case 4:
          case 6:
            playoff[i] = new PlayoffGame(conf1Teams.get(confSize - (i + 1)),
                    conf1Teams.get(i), series[i]);
            playoff[i + conf2Offset] = new PlayoffGame(conf2Teams.get(confSize - (i + 1)),
                    conf2Teams.get(i), series[i + conf2Offset]);
            break;
          default:
            playoff[i] = new PlayoffGame(conf1Teams.get(i),
                    conf1Teams.get(confSize - (i + 1)), series[i]);
            playoff[i + conf2Offset] = new PlayoffGame(conf2Teams.get(i),
                    conf2Teams.get(confSize - (i + 1)), series[i + conf2Offset]);
        }
      }
      round.add(playoff);
      game++;
    }

    return round;
  }

  // REGULAR SEASON (NBA FORMAT SPECIFIC)
  static Game[] gameDay(int gameday, List<Team> teams) {
    Game[] schedule = new Game[teams.size() / 2];
    int teamsCount = teams.size();
    assert (teamsCount % 6 == 0);
    int divSize = teamsCount / 6;

    List<List<Team>> divisions = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      divisions.add(new ArrayList<>());
    }

    for (int i = 0; i < divSize; i++) {
      for (int j = 0; j < 6; j++) {
        divisions.get(j).add(teams.get((j * divSize) + i));
      }
    }

    gameday = gameday % (teamsCount - 1);

    int micro = gameday % 5;
    int macro = gameday / 5;
    int done = 0;

    for (int i = 0; i < 6; i++) {
      if (micro == 0) {
        schedule[i] = new Game(divisions.get(i).get(0),
                divisions.get(getDiv(macro, i)).get(4));
        done++;
        schedule[6 + i] = new Game(divisions.get(i).get(1),
                divisions.get(getDiv(macro, i)).get(3));
        done++;
      } else if (micro == 1) {
        schedule[i] = new Game(divisions.get(i).get(0),
                divisions.get(getDiv(macro, i)).get(3));
        done++;
        schedule[6 + i] = new Game(divisions.get(i).get(2),
                divisions.get(getDiv(macro, i)).get(4));
        done++;
      } else if (micro == 2) {
        schedule[i] = new Game(divisions.get(i).get(0),
                divisions.get(getDiv(macro, i)).get(2));
        done++;
        schedule[6 + i] = new Game(divisions.get(i).get(1),
                divisions.get(getDiv(macro, i)).get(4));
        done++;
      } else if (micro == 3) {
        schedule[i] = new Game(divisions.get(i).get(0),
                divisions.get(getDiv(macro, i)).get(1));
        done++;
        schedule[6 + i] = new Game(divisions.get(i).get(2),
                divisions.get(getDiv(macro, i)).get(3));
        done++;
      } else if (micro == 4) {
        schedule[i] = new Game(divisions.get(i).get(1),
                divisions.get(getDiv(macro, i)).get(2));
        done++;
        schedule[6 + i] = new Game(divisions.get(i).get(3),
                divisions.get(getDiv(macro, i)).get(4));
        done++;
      }
    }

    Game[] byeGames = scheduleByeGames(micro, macro, divisions);
    schedule[12] = byeGames[0];
    schedule[13] = byeGames[1];
    schedule[14] = byeGames[2];
    done += 3;

    assert (done == 15);
    return schedule;
  }

  private static Game[] scheduleByeGames(int micro, int macro,
                                List<List<Team>> divisions) {
    Game[] byeGames = new Game[3];
    macro = (macro + 1) % 5;
    if (macro == 0) { macro++; }
    boolean[] accounted =
            new boolean[] {false, false, false, false, false, false};
    int done = 0;

    while (done < 3) {

      int index = Integer.MAX_VALUE;
      for (int i = 0; i < accounted.length; i++) {
        if (!accounted[i]) {
          index = i;
          break;
        }
      }

      switch (micro) {
        case 0:
          byeGames[done] = new Game(divisions.get(index).get(2),
                  divisions.get(getDiv(macro, index)).get(2));
          accounted[index] = true;
          accounted[getDiv(macro, index)] = true;
          done++;
          break;
        case 1:
          byeGames[done] = new Game(divisions.get(index).get(1),
                  divisions.get(getDiv(macro, index)).get(1));
          accounted[index] = true;
          accounted[getDiv(macro, index)] = true;
          done++;
          break;
        case 2:
          byeGames[done] = new Game(divisions.get(index).get(3),
                  divisions.get(getDiv(macro, index)).get(3));
          accounted[index] = true;
          accounted[getDiv(macro, index)] = true;
          done++;
          break;
        case 3:
          byeGames[done] = new Game(divisions.get(index).get(4),
                  divisions.get(getDiv(macro, index)).get(4));
          accounted[index] = true;
          accounted[getDiv(macro, index)] = true;
          done++;
          break;
        case 4:
          byeGames[done] = new Game(divisions.get(index).get(0),
                  divisions.get(getDiv(macro, index)).get(0));
          accounted[index] = true;
          accounted[getDiv(macro, index)] = true;
          done++;
          break;
      }
    }

    return byeGames;
  }

  private static int getDiv(int macro, int div) {
    switch (macro) {
      case 0:
        return div;
      case 1:
        switch (div) {
          case 0:
            return 2;
          case 1:
            return 3;
          case 2:
            return 0;
          case 3:
            return 1;
          case 4:
            return 5;
          case 5:
            return 4;
        }
        break;
      case 2:
        switch (div) {
          case 0:
            return 1;
          case 1:
            return 0;
          case 2:
            return 4;
          case 3:
            return 5;
          case 4:
            return 2;
          case 5:
            return 3;
        }
        break;
      case 3:
        switch (div) {
          case 0:
            return 4;
          case 1:
            return 5;
          case 2:
            return 3;
          case 3:
            return 2;
          case 4:
            return 0;
          case 5:
            return 1;
        }
        break;
      case 4:
        switch (div) {
          case 0:
            return 3;
          case 1:
            return 4;
          case 2:
            return 5;
          case 3:
            return 0;
          case 4:
            return 1;
          case 5:
            return 2;
        }
        break;
      case 5:
        switch (div) {
          case 0:
            return 5;
          case 1:
            return 2;
          case 2:
            return 1;
          case 3:
            return 4;
          case 4:
            return 3;
          case 5:
            return 0;
        }
        break;
    }
    return 0;
  }
}
