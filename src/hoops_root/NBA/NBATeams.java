package hoops_root.NBA;

import hoops_root.Date;
import hoops_root.Player;
import hoops_root.Team;
import hoops_root.traits.DominantHand;
import hoops_root.traits.Position;
import hoops_root.utilities.StatCalculator;

public class NBATeams {
  public static Team BOS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Kyrie", "Andrew", "Irving"},
            "Kyrie", new Date(1992, 3, 23),
            Position.POINT_GUARD, DominantHand.RIGHT, 11, 75, 193,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/bos/kyrie_irving.csv"));
    roster[1] = new Player(new String[] {"Jayson", "Christopher", "Tatum"},
            "Jayson", new Date(1998, 3, 3),
            Position.POWER_FORWARD, DominantHand.RIGHT, 0, 80, 205,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/bos/jayson_tatum.csv"));
    roster[2] = new Player(new String[] {"Alfred", "Joel", "Horford"},
            "Al", new Date(1986, 6, 3),
            Position.CENTER, DominantHand.RIGHT, 42, 82, 245,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/bos/al_horford.csv"));
    roster[3] = new Player(new String[] {"Jaylen", "Marselles", "Brown"},
            "Jaylen", new Date(1996, 10, 24),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 7, 79, 225,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/bos/jaylen_brown.csv"));
    roster[4] = new Player(new String[] {"Gordon", "Daniel", "Hayward"},
            "Gordon", new Date(1990, 3, 23),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 20, 80, 226,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/bos/gordon_hayward.csv"));
    roster = botsFrom(5, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("BOS", "Boston Celtics", roster);
  }

  public static Team GSW() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Wardell", "Stephen", "Curry"},
            "Steph", new Date(1988, 3, 14),
            Position.POINT_GUARD, DominantHand.RIGHT, 30, 75, 190,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/gsw/steph_curry.csv"));
    roster[1] = new Player(new String[] {"Kevin", "Wayne", "Durant"},
            "Kevin", new Date(1988, 9, 29),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 35, 81, 240,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/gsw/kevin_durant.csv"));
    roster[2] = new Player(new String[] {"Draymond", "Jamal", "Green"},
            "Draymond", new Date(1990, 3, 4),
            Position.POWER_FORWARD, DominantHand.RIGHT, 23, 79, 230,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/gsw/draymond_green.csv"));
    roster[3] = new Player(new String[] {"Klay", "Alexander", "Thompson"},
            "Klay", new Date(1988, 9, 29),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 11, 79, 215,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/gsw/klay_thompson.csv"));
    roster[4] = new Player(new String[] {"Kevon", "Grant", "Looney"},
            "Kevon", new Date(1996, 2, 6),
            Position.CENTER, DominantHand.RIGHT, 5, 81, 220,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/gsw/kevon_looney.csv"));
    roster[5] = new Player(new String[] {"DeMarcus", "Amir", "Cousins"},
            "DeMarcus", new Date(1990, 8, 13),
            Position.CENTER, DominantHand.RIGHT, 0, 83, 270,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/gsw/demarcus_cousins.csv"));
    roster = botsFrom(6, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("GSW", "Golden State Warriors", roster);
  }

  public static Team HOU() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"James", "Edward", "Harden"},
            "James", new Date(1989, 8, 26),
            Position.SHOOTING_GUARD, DominantHand.LEFT, 13, 77, 220,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/hou/james_harden.csv"));
    roster[1] = new Player(new String[] {"Christopher", "Emmanuel", "Paul"},
            "Chris", new Date(1985, 5, 6),
            Position.POINT_GUARD, DominantHand.RIGHT, 3, 72, 175,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/hou/chris_paul.csv"));
    roster[2] = new Player(new String[] {"Clint", "N'Dumba-Capela", "Capela"},
            "Clint", new Date(1994, 5, 18),
            Position.CENTER, DominantHand.RIGHT, 15, 82, 240,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/hou/clint_capela.csv"));
    roster = botsFrom(3, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("HOU", "Houston Rockets", roster);
  }

  public static Team LAL() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Lonzo", "Anderson", "Ball"},
            "Lonzo", new Date(1997, 10, 27),
            Position.POINT_GUARD, DominantHand.RIGHT, 2, 78, 190,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/lal/lonzo_ball.csv"));
    roster[1] = new Player(new String[] {"Kyle", "Alexander", "Kuzma"},
            "Kyle", new Date(1995, 7, 24),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 0, 81, 240,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/lal/kyle_kuzma.csv"));
    roster[2] = new Player(new String[] {"LeBron", "Raymone", "James"},
            "LeBron", new Date(1984, 12, 30),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 23, 80, 250,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/lal/lebron_james.csv"));
    roster[3] = new Player(new String[] {"Brandon", "Xavier", "Ingram"},
            "Brandon", new Date(1997, 9, 2),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 14, 81, 190,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/lal/brandon_ingram.csv"));
    roster[4] = new Player(new String[] {"Tyson", "Grant", "Chandler"},
            "Tyson", new Date(1982, 10, 2),
            Position.CENTER, DominantHand.RIGHT, 5, 85, 240,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/lal/tyson_chandler.csv"));
    roster = botsFrom(5, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("LAL", "Los Angeles Lakers", roster);
  }

  public static Team MIL() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"Giannis", "Antetokounmpo"},
            "Giannis", new Date(1994, 12, 6),
            Position.POWER_FORWARD, DominantHand.RIGHT, 34, 83, 222,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/mil/giannis_antetokounmpo.csv"));
    roster = botsFrom(1, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("MIL", "Milwaukee Bucks", roster);
  }

  public static Team OKC() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    // Russell Westbrook
    roster[0] = new Player(new String[] {"Russell", "Westbrook"},
            "Russell", new Date(1988, 11, 12),
            Position.POINT_GUARD, DominantHand.RIGHT, 0, 75, 200,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/okc/russell_westbrook.csv"));
    // Paul George
    roster[1] = new Player(new String[] {"Paul", "Clifton", "Anthony", "George"},
            "Paul", new Date(1990, 5, 2),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 13, 81, 220,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/okc/paul_george.csv"));
    // Steven Adams
    roster[2] = new Player(new String[] {"Steven", "Funaki", "Adams"},
            "Steven", new Date(1993, 7, 20),
            Position.CENTER, DominantHand.RIGHT, 12, 84, 255,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/okc/steven_adams.csv"));
    roster = botsFrom(3, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("OKC", "Oklahoma City Thunder", roster);
  }

  public static Team PHI() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    // Joel Embiid
    roster[0] = new Player(new String[] {"Joel", "Hans", "Embiid"},
            "Joel", new Date(1994, 3, 16),
            Position.CENTER, DominantHand.RIGHT, 21, 84, 250,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/phi/joel_embiid.csv"));
    // Ben Simmons
    roster[1] = new Player(new String[] {"Benjamin", "David", "Simmons"},
            "Ben", new Date(1996, 7, 20),
            Position.POINT_GUARD, DominantHand.LEFT, 25, 82, 230,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/phi/ben_simmons.csv"));
    // Jimmy Butler
    roster[2] = new Player(new String[] {"Jimmy", "Butler"},
            "Jimmy", new Date(1989, 9, 14),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 23, 80, 236,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/phi/jimmy_butler.csv"));
    roster = botsFrom(3, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("PHI", "Philadelphia 76ers", roster);
  }

  public static Team TOR() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    // Kawhi Leonard
    roster[0] = new Player(new String[] {"Kawhi", "Leonard"},
            "Kawhi", new Date(1991, 6, 29),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 2, 79, 230,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/tor/kawhi_leonard.csv"));
    // Kyle Lowry
    roster[1] = new Player(new String[] {"Kyle", "Lowry"},
            "Kyle", new Date(1986, 3, 25),
            Position.POINT_GUARD, DominantHand.RIGHT, 7, 72, 205,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/tor/kyle_lowry.csv"));
    // Jonas Valanciunas
    roster[2] = new Player(new String[] {"Jonas", "Valanciunas"},
            "Jonas", new Date(1992, 5, 6),
            Position.POINT_GUARD, DominantHand.RIGHT, 17, 84, 265,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/tor/jonas_valanciunas.csv"));
    roster = botsFrom(3, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("TOR", "Toronto Raptors", roster);
  }

  public static Team shell() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = new Player(new String[] {"I", "Don't", "Exist"},
            "I", new Date(2000, 1, 1),
            Position.POINT_GUARD, DominantHand.RIGHT, 23, 72, 180,
            StatCalculator.calculate(
                    "resources/player_stats/NBA/team/player.csv"));
    roster = botsFrom(0, roster);
    for (Player player : roster) {
      System.out.println(player + " - " + player.overall());
    }
    return new Team("SHL", "shell", roster);
  }

  private static Player[] botsFrom(int index, Player[] roster) {
    for (int i = index; i < 12; i++) {
      roster[i] = new Player(new String[] {String.valueOf(i)}, "Bot",
              new Date(2000, 1, 1));
    }
    return roster;
  }
}
