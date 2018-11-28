package hoops_root.NBA;

import hoops_root.Date;
import hoops_root.Player;
import hoops_root.Team;
import hoops_root.traits.DominantHand;
import hoops_root.traits.Position;

public class NBATeams {
  public static Team BOS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Kyrie", "Andrew", "Irving"},
            "Kyrie", new Date(1992, 3, 23),
            Position.POINT_GUARD, DominantHand.RIGHT, 11, 75, 193,
            new int[] {86, 75, 87, 81, 92, 93, 90, 70, 91, 2, 1});
    roster[1] = new Player(new String[] {"Jayson", "Christopher", "Tatum"},
            "Jayson", new Date(1998, 3, 3),
            Position.POWER_FORWARD, DominantHand.RIGHT, 0, 80, 205,
            new int[] {90, 85, 80, 70, 70, 75, 89, 80, 84, 0, 1});
    roster[2] = new Player(new String[] {"Alfred", "Joel", "Horford"},
            "Al", new Date(1986, 6, 3),
            Position.CENTER, DominantHand.RIGHT, 42, 82, 245,
            new int[] {84, 87, 85, 75, 75, 75, 74, 88, 80, 1, 2});
    roster[3] = new Player(new String[] {"Jaylen", "Marselles", "Brown"},
            "Jaylen", new Date(1996, 10, 24),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 7, 79, 225,
            new int[] {78, 72, 70, 65, 65, 70, 68, 70, 75, 1, 1});
    roster[4] = new Player(new String[] {"Gordon", "Daniel", "Hayward"},
            "Gordon", new Date(1990, 3, 23),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 20, 80, 226,
            new int[] {88, 75, 78, 75, 84, 75, 90, 65, 80, 2, 1});
    roster = botsFrom(5, roster);
    for (Player player : roster) {
      System.out.println(player + " - " +
              Player.overall(player, player.position()));
    }
    return new Team("BOS", "Boston Celtics", roster);
  }

  public static Team GSW() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Wardell", "Stephen", "Curry"},
            "Steph", new Date(1988, 3, 14),
            Position.POINT_GUARD, DominantHand.RIGHT, 30, 75, 190,
            new int[] {98, 60, 70, 90, 90, 90, 98, 55, 95, 3, 1});
    roster[1] = new Player(new String[] {"Kevin", "Wayne", "Durant"},
            "Kevin", new Date(1988, 9, 29),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 35, 81, 240,
            new int[] {91, 75, 75, 80, 85, 95, 96, 75, 95, 1, 2});
    roster[2] = new Player(new String[] {"Draymond", "Jamal", "Green"},
            "Draymond", new Date(1990, 3, 4),
            Position.POWER_FORWARD, DominantHand.RIGHT, 23, 79, 230,
            new int[] {70, 82, 75, 70, 60, 80, 78, 75, 82, 0, 4});
    roster[3] = new Player(new String[] {"Klay", "Alexander", "Thompson"},
            "Klay", new Date(1988, 9, 29),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 11, 79, 215,
            new int[] {96, 55, 70, 85, 80, 91, 90, 60, 70, 1, 1});
    roster[4] = new Player(new String[] {"Kevon", "Grant", "Looney"},
            "Kevon", new Date(1996, 2, 6),
            Position.CENTER, DominantHand.RIGHT, 5, 81, 220,
            new int[] {60, 60, 60, 50, 50, 60, 65, 60, 50, 1, 1});
    roster = botsFrom(5, roster);
    for (Player player : roster) {
      System.out.println(player + " - " +
              Player.overall(player, player.position()));
    }
    return new Team("GSW", "Golden State Warriors", roster);
  }

  public static Team LAL() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Lonzo", "Anderson", "Ball"},
            "Lonzo", new Date(1997, 10, 27),
            Position.POINT_GUARD, DominantHand.RIGHT, 2, 78, 190,
            new int[] {77, 75, 75, 96, 92, 60, 62, 50, 70, 1, 1});
    roster[1] = new Player(new String[] {"Kyle", "Alexander", "Kuzma"},
            "Kyle", new Date(1995, 7, 24),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 0, 81, 240,
            new int[] {84, 78, 80, 68, 70, 60, 77, 68, 83, 0, 2});
    roster[2] = new Player(new String[] {"LeBron", "Raymone", "James"},
            "LeBron", new Date(1984, 12, 30),
            Position.POWER_FORWARD, DominantHand.RIGHT, 23, 80, 250,
            new int[] {92, 90, 97, 85, 95, 82, 80, 90, 98, 0, 2});
    roster[3] = new Player(new String[] {"Brandon", "Xavier", "Ingram"},
            "Brandon", new Date(1997, 9, 2),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 14, 81, 190,
            new int[] {84, 78, 70, 81, 75, 70, 72, 73, 85, 1, 4});
    roster[4] = new Player(new String[] {"Tyson", "Grant", "Chandler"},
            "Tyson", new Date(1982, 10, 2),
            Position.CENTER, DominantHand.RIGHT, 5, 85, 240,
            new int[] {50, 85, 80, 50, 60, 80, 67, 75, 60, 2, 3});
    roster = botsFrom(5, roster);
    for (Player player : roster) {
      System.out.println(player + " - " +
              Player.overall(player, player.position()));
    }
    return new Team("LAL", "Los Angeles Lakers", roster);
  }

  public static Team MIL() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Giannis", "Antetokounmpo"},
            "Giannis", new Date(1994, 12, 6),
            Position.POWER_FORWARD, DominantHand.RIGHT, 34, 83, 222,
            new int[] {60, 96, 98, 75, 68, 80, 74, 85, 93, 0, 2});
    roster = botsFrom(1, roster);
    for (Player player : roster) {
      System.out.println(player + " - " +
              Player.overall(player, player.position()));
    }
    return new Team("MIL", "Milwaukee Bucks", roster);
  }

  private static Player[] botsFrom(int index, Player[] roster) {
    for (int i = index; i < 12; i++) {
      roster[i] = new Player(new String[] {String.valueOf(i)}, "Bot",
              new Date(2000, 1, 1));
    }
    return roster;
  }
}
