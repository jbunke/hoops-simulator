package hoops_root;

public class PlayoffGame extends Game {
  private Series series;

  PlayoffGame(Team away, Team home, Series series) {
    super(away, home);
    this.series = series;
  }

  @Override
  void simulate() {
    assert (!played);

    if (!series.isDetermined()) {
      sim();

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

    saveStats();

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
