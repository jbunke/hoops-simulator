package hoops_root;

public class Game {
  Team away;
  Team home;
  int[] score = new int[2]; // {away, home}
  boolean played;

  Game(Team away, Team home) {
    this.away = away;
    this.home = home;
    this.score = new int[] {0, 0};
    this.played = false;

    away.addGameToSchedule(this);
    home.addGameToSchedule(this);
  }

  void randomSim() {
    assert (!played);
    // TODO - Have to still track points and player stats; OT; ...
    while (score[0] == score[1]) {
      score[0] = away.quality() + (int)(Math.random() * 50);
      score[1] = home.quality() + (int)(Math.random() * 50);
    }

    played = true;
    outcome();
  }

  void outcome() {
    assert (played);
    assert (score[0] != score[1]);

    if (score[0] > score[1]) {
      away.getRecord().addWin();
      home.getRecord().addLoss();
    } else {
      away.getRecord().addLoss();
      home.getRecord().addWin();
    }

    away.shiftGameToPast(this);
    home.shiftGameToPast(this);
  }

  /*
  Team winner() {
    assert (played);
    assert (score[0] != score[1]);

    if (score[0] > score[1]) {
      return away;
    }
    return home;
  }
  */

  @Override
  public String toString() {
    if (played) {
      if (score[0] > score[1]) {
        return away + " def. " + home + " " + score[0] + "-" + score[1];
      }
      return home + " def. " + away + " " + score[1] + "-" + score[0];
    }
    return away + " at " + home;
  }
}
