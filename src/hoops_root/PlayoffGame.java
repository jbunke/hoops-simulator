package hoops_root;

public class PlayoffGame extends Game {
  private Series series;

  PlayoffGame(Team away, Team home, Series series) {
    super(away, home);
    this.series = series;
  }

  @Override
  void randomSim() {
    assert (!played);
    // TODO - Have to still track points and player stats; OT; ...
    if (!series.isDetermined()) {
      while (score[0] == score[1]) {
        score[0] = 80 + (int)(Math.random() * 50);
        score[1] = 80 + (int)(Math.random() * 50);
      }
      played = true;
      outcome();
    }
  }

  @Override
  void outcome() {
    assert (played);
    assert (score[0] != score[1]);

    if (score[0] > score[1]) {
      series.adjustScore(away, this);
    } else {
      series.adjustScore(home, this);
    }

    away.shiftGameToPast(this);
    home.shiftGameToPast(this);
  }

  @Override
  public String toString() {
    if (played) {
      if (score[0] > score[1]) {
        return away.code() + " (" + series.gamesWon(away) + ") def. " +
                home.code() + " (" + series.gamesWon(home) + ") " +
                score[0] + "-" + score[1];
      }
      return home.code() + " (" + series.gamesWon(home) + ") def. " +
              away.code() + " (" + series.gamesWon(away) + ") " +
              score[1] + "-" + score[0];
    }
    return away.code() + " (" + series.gamesWon(away) + ") at " +
            home.code() + " (" + series.gamesWon(home) + ")";
  }
}
