public class Game {
  private Team away;
  private Team home;
  private int[] score = new int[2]; // {away, score}
  private boolean played;

  Game(Team away, Team home) {
    this.away = away;
    this.home = home;
    this.score = new int[] {0, 0};
    this.played = false;
  }

  @Override
  public String toString() {
    return away + " (" + away.getRecord() + ") at " +
            home + " (" + home.getRecord() + ")";
  }
}
