import java.util.ArrayList;
import java.util.List;

public class Scheduler {
  static Game[] gameDay(int gameday, List<Team> teams) {
    Game[] schedule = new Game[teams.size() / 2];

    List<List<Team>> divisions = new ArrayList<>();
    List<Team> div1 = new ArrayList<>();
    List<Team> div2 = new ArrayList<>();
    List<Team> div3 = new ArrayList<>();
    List<Team> div4 = new ArrayList<>();
    List<Team> div5 = new ArrayList<>();
    List<Team> div6 = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      div1.add(teams.get(i));
      div2.add(teams.get(5 + i));
      div3.add(teams.get(10 + i));
      div4.add(teams.get(15 + i));
      div5.add(teams.get(20 + i));
      div6.add(teams.get(25 + i));
    }

    divisions.add(div1);
    divisions.add(div2);
    divisions.add(div3);
    divisions.add(div4);
    divisions.add(div5);
    divisions.add(div6);

    gameday = gameday % 29;

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
        /*
        if (i < 3) {
          schedule[12 + i] = new Game(divisions.get(i).get(0),
                  divisions.get(getDiv((macro + 1) % 5, i)).get(0));
          done++;
        }
        */
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
    macro = (macro + 1) % 6;
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
