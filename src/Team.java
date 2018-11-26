import java.util.ArrayList;
import java.util.List;

public class Team {
  private final String code; // three-char code; like "GSW"
  private final String name; // team name; like "Golden State Warriors"
  private Record record;
  private Player[] roster;
  private List<Game> pastGames;
  private List<Game> upcomingGames;

  Team(String code, String name, Player[] roster) {
    this.code = code;
    this.name = name;
    this.record = new Record();
    this.roster = roster;
    this.pastGames = new ArrayList<>();
    this.upcomingGames = new ArrayList<>();

    for (Player player : roster) {
      player.setTeam(this);
    }
  }

  void addGameToSchedule(Game toAdd) {
    upcomingGames.add(toAdd);
  }

  void shiftGameToPast(Game toShift) {
    pastGames.add(0, toShift);
    upcomingGames.remove(toShift);
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

  Player[] getRoster() { return roster; }

  // returns a team of bots
  static Team botTeam(String code, String name) {
    Player[] botTeamRoster = new Player[12];

    for (int i = 0; i < 12; i++) {
      botTeamRoster[i] = new Player(new String[] {String.valueOf(i)},
              "Bot", new Date(2000, 1, 1));
    }

    return new Team(code, name, botTeamRoster);
  }

  String nameAndRecord() { return name + " (" + record + ")"; }

  @Override
  public String toString() {
    return code + " (" + record + ")";
  }
}
