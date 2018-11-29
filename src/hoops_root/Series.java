package hoops_root;

public class Series {
  private int[] score; // highSeed - lowSeed
  private PlayoffGame[] sequence;
  private Team highSeed;
  private Team lowSeed;
  private boolean determined;

  Series(Team highSeed, Team lowSeed) {
    this.highSeed = highSeed;
    this.lowSeed = lowSeed;
    this.score = new int[] {0, 0};
    this.sequence = new PlayoffGame[7];
    this.determined = false;
  }

  void adjustScore(Team winner, PlayoffGame game) {
    assert (!determined);
    assert (winner.equals(highSeed) || winner.equals(lowSeed));

    sequence[score[0] + score[1]] = game;

    if (winner.equals(highSeed)) {
      score[0]++;
    } else {
      score[1]++;
    }

    if (score[0] == 4 || score[1] == 4) {
      determined = true;
    }
  }

  int gamesWon(Team team) {
    assert (team.equals(highSeed) || team.equals(lowSeed));

    if (team.equals(highSeed)) {
      return score[0];
    }
    return score[1];
  }

  boolean isDetermined() { return determined; }

  Team getHighSeed() { return highSeed; }

  Team getLowSeed() { return lowSeed; }

  Team winner() {
    assert (determined);

    if (score[0] == 4) {
      return highSeed;
    }
    return lowSeed;
  }

  void printSeries() {
    assert (determined);

    System.out.println(this + ":");

    int games = 0;
    for (int i = 0; i < 7; i++) {
      if (sequence[i] == null) { break; }
      assert (sequence[i].played);
      System.out.println("Game " + (i + 1) + ": " + sequence[i]);
      games = i;
    }
    games++;
    System.out.println(winner().name() + " win the series in " +
            games + " games.\n");
  }

  @Override
  public String toString() {
    return highSeed.getSeed() + " " + highSeed.name() + " vs. "
            + lowSeed.getSeed() + " " + lowSeed.name();
  }
}
