public class Record {
  private int wins;
  private int losses;

  Record() {
    this.wins = 0;
    this.losses = 0;
  }

  int wins() { return wins; }

  int losses() { return losses; }

  void addWin() { wins++; }

  void addLoss() { losses++; }

  void reset() {
    wins = 0;
    losses = 0;
  }

  @Override
  public String toString() {
    return wins + "-" + losses;
  }
}
