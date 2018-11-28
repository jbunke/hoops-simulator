package hoops_root;

public class Series {
  private int[] score; // highCede - lowCede
  private PlayoffGame[] sequence;
  private Team highCede;
  private Team lowCede;
  private boolean determined;

  Series(Team highCede, Team lowCede) {
    this.highCede = highCede;
    this.lowCede = lowCede;
    this.score = new int[] {0, 0};
    this.sequence = new PlayoffGame[7];
    this.determined = false;
  }

  void adjustScore(Team winner, PlayoffGame game) {
    assert (!determined);
    assert (winner.equals(highCede) || winner.equals(lowCede));

    sequence[score[0] + score[1]] = game;

    if (winner.equals(highCede)) {
      score[0]++;
    } else {
      score[1]++;
    }

    if (score[0] == 4 || score[1] == 4) {
      determined = true;
    }
  }

  int gamesWon(Team team) {
    assert (team.equals(highCede) || team.equals(lowCede));

    if (team.equals(highCede)) {
      return score[0];
    }
    return score[1];
  }

  boolean isDetermined() { return determined; }

  Team getHighCede() { return highCede; }

  Team getLowCede() { return lowCede; }

  Team winner() {
    assert (determined);

    if (score[0] == 4) {
      return highCede;
    }
    return lowCede;
  }

  void printSeries() {
    assert (determined);

    System.out.println(this + ":");

    int games = 0;
    for (int i = 0; i < 7; i++) {
      if (sequence[i] == null) { break; }
      assert (sequence[i].played);
      System.out.println("hoops_root.Game " + (i + 1) + ": " + sequence[i]);
      games = i;
    }
    games++;
    System.out.println(winner().name() + " win the series in " +
            games + " games.\n");
  }

  @Override
  public String toString() {
    return highCede.nameAndRecord() + " vs. " + lowCede.nameAndRecord();
  }
}
