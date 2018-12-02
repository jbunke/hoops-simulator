package hoops_root;

import hoops_root.traits.Position;
import hoops_root.traits.positional_comparators.*;
import hoops_root.utilities.ArrayControl;

import java.util.*;

public class Team {
  private final String code; // three-char code; like "GSW"
  private final String name; // team name; like "Golden State Warriors"
  private Record record;
  private int seed;
  private Player[] roster;
  private Player[] starters;
  private List<Game> pastGames;
  private List<Game> upcomingGames;
  private Map<Integer, Player> jerseyNumbers;
  private List<Integer> availableNumbers;
  private Position[] lineupPriority;

  public Team(String code, String name, Player[] roster) {
    this.code = code;
    this.name = name;
    this.record = new Record();
    this.seed = 0;
    this.roster = roster;
    this.pastGames = new ArrayList<>();
    this.upcomingGames = new ArrayList<>();
    this.jerseyNumbers = new HashMap<>();
    this.availableNumbers = new ArrayList<>();
    this.lineupPriority = new Position[5];

    for (int i = 0; i < 100; i++) {
      availableNumbers.add(i);
    }

    for (Player player : roster) {
      player.setTeam(this);
      if (!jerseyNumbers.containsKey(player.prefJerseyNumber) &&
              player.prefJerseyNumber >= 0) {
        jerseyNumbers.put(player.prefJerseyNumber, player);
        player.setJerseyNumber(player.prefJerseyNumber);
        availableNumbers.remove(new Integer(player.prefJerseyNumber));
      } else {
        jerseyNumbers.put(availableNumbers.get(0), player);
        player.setJerseyNumber(availableNumbers.get(0));
        availableNumbers.remove(0);
      }
    }

    priorityByQuality();
    automateLineUp();
  }

  private void replenishBench() {
    Player[] bench = getBench();

    for (Player player : bench) {
      player.replenishEnergy();
    }
  }

  Player[] substitutions() {
    // replenish energy
    replenishBench();

    // reassemble lineup
    priorityByQuality();
    automateLineUp();
    return starters;
  }

  private void priorityByQuality() {
    boolean[] available = new boolean[] { true, true, true, true, true };
    int assigned = 0;
    List<Player> players = Arrays.asList(roster);
    players.sort(new PositionComparator());

    for (Player player : players) {
      if (player.status() == Player.Status.FIT) {
        if (available[player.position().index] && assigned < 5) {
          lineupPriority[assigned] = player.position();
          available[player.position().index] = false;
          assigned++;
        }
      }
    }
    for (int i = 0; i < 5; i++) {
      if (available[i]) {
        lineupPriority[assigned] = Position.fromIndex(i);
        assigned++;
      }
      if (assigned >= 5) { break; }
    }
  }

  private void automateLineUp() {
    // PG, SG, PF, SF, C
    starters = new Player[5];
    List<Player> isPlaying = new ArrayList<>();

    for (Position position : lineupPriority) {
      List<Player> players = new ArrayList<>();
      for (Player player : roster) {
        if (!isPlaying.contains(player) &&
                player.status() == Player.Status.FIT) {
          players.add(player);
        }
      }
      int index = position.index;

      switch (position) {
        case POINT_GUARD:
          players.sort(new PGComparator());
          break;
        case SHOOTING_GUARD:
          players.sort(new SGComparator());
          break;
        case POWER_FORWARD:
          players.sort(new PFComparator());
          break;
        case SMALL_FORWARD:
          players.sort(new SFComparator());
          break;
        case CENTER:
        default:
          players.sort(new CComparator());
          break;
      }
      starters[index] = players.get(0);
      isPlaying.add(players.get(0));
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
      if (find.equals(player.goesBy())) {
        return player;
      }
    }
    return null;
  }

  int getSeed() { return seed; }

  void setSeed(int seed) { this.seed = seed; }

  Record getRecord() { return record; }

  Player[] getStarters() { return starters; }

  Player[] getBench() { return ArrayControl.playerDifference(roster, starters); }

  Player[] getRoster() { return roster; }

  int quality() {
    int quality = 0;
    for (int i = 0; i < 5; i++) {
      quality += Player.overall(starters[i], Position.fromIndex(i));
    }
    return quality / 5;
  }

  // returns a team of bots
  static Team botTeam(String code, String name) {
    Player[] botTeamRoster = new Player[12];

    for (int i = 0; i < 12; i++) {
      botTeamRoster[i] = new Player(new String[] {String.valueOf(i)},
              code + " Bot", new Date(2000, 1, 1));
    }

    return new Team(code, name, botTeamRoster);
  }

  void newSeason() {
    this.record.reset();
    this.pastGames = new ArrayList<>();
    this.upcomingGames = new ArrayList<>();
  }

  String code() { return code; }

  String name() { return name; }

  String nameAndRecord() { return name + " (" + record + ")"; }

  @Override
  public String toString() {
    return code + " (" + record + ")";
  }
}
