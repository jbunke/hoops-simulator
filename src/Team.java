public class Team {
  private final String code; // three-char code; like "GSW"
  private final String name; // team name; like "Golden State Warriors"
  private Record record;
  private Player[] roster;

  Team(String code, String name, Player[] roster) {
    this.code = code;
    this.name = name;
    this.record = new Record();
    this.roster = roster;
  }

  Player getPlayer(String find) {
    for (Player player : roster) {
      if (find.equals(player.toString())) {
        return player;
      }
    }
    return null;
  }

  Record getRecord() { return record; }

  @Override
  public String toString() {
    return code;
  }
}
