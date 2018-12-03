package hoops_root.NBA;

import hoops_root.Date;
import hoops_root.Player;
import hoops_root.traits.DominantHand;
import hoops_root.traits.Position;
import hoops_root.utilities.StatCalculator;

import java.net.MalformedURLException;
import java.net.URL;

class NBAPlayers {
  private static final String BBRLINK = "https://www.basketball-reference.com/players/";
  private static final String STATPATH = "resources/player_stats/NBA/";

  static Player getPlayer(String name, boolean online) {
    try {
      switch (name) {
        case "Kareem Abdul-Jabbar":
          return new Player(new String[] {"Kareem", "Abdul-Jabbar"},
                  "Kareem", new Date(1947, 4, 16),
                  Position.CENTER, DominantHand.RIGHT, 33, 86, 225,
                  StatCalculator.best(STATPATH + "leg/kareem_abduljabbar.csv"));
        case "Steven Adams":
          return new Player(new String[] {"Steven", "Funaki", "Adams"},
                  "Steven", new Date(1993, 7, 20),
                  Position.CENTER, DominantHand.RIGHT, 12, 84, 255,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "a/adamsst01.html"),
                          STATPATH + "okc/steven_adams.csv", online));
        case "Giannis Antetokounmpo":
          return new Player(new String[] {"Giannis", "Antetokounmpo"},
                  "Giannis", new Date(1994, 12, 6),
                  Position.POWER_FORWARD, DominantHand.RIGHT, 34, 83, 222,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "a/antetgi01.html"),
                          STATPATH + "mil/giannis_antetokounmpo.csv", online));
        case "Lonzo Ball":
          return new Player(new String[] {"Lonzo", "Anderson", "Ball"},
                  "Lonzo", new Date(1997, 10, 27),
                  Position.POINT_GUARD, DominantHand.RIGHT, 2, 78, 190,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "b/balllo01.html"),
                          STATPATH + "lal/lonzo_ball.csv", online));
        case "Eric Bledsoe":
          return new Player(new String[] {"Eric", "Bledsoe"},
                  "Eric", new Date(1989, 12, 9),
                  Position.POINT_GUARD, DominantHand.RIGHT, 6, 73, 205,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "b/bledser01.html"),
                          STATPATH + "mil/eric_bledsoe.csv", online));
        case "Jaylen Brown":
          return new Player(new String[] {"Jaylen", "Marselles", "Brown"},
                  "Jaylen", new Date(1996, 10, 24),
                  Position.SHOOTING_GUARD, DominantHand.RIGHT, 7, 79, 225,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "b/brownja02.html"),
                          STATPATH + "bos/jaylen_brown.csv", online));
        case "Kobe Bryant":
          return new Player(new String[] {"Kobe", "Bean", "Bryant"},
                  "Kobe", new Date(1978, 8, 23),
                  Position.SHOOTING_GUARD, DominantHand.RIGHT, 24, 78, 212,
                  StatCalculator.best(STATPATH + "leg/kobe_bryant.csv"));
        case "Jimmy Butler":
          return new Player(new String[] {"Jimmy", "Butler"},
                  "Jimmy", new Date(1989, 9, 14),
                  Position.SHOOTING_GUARD, DominantHand.RIGHT, 23, 80, 236,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "b/butleji01.html"),
                          STATPATH + "phi/jimmy_butler.csv", online));
        case "Clint Capela":
          return new Player(new String[] {"Clint", "N'Dumba-Capela", "Capela"},
                  "Clint", new Date(1994, 5, 18),
                  Position.CENTER, DominantHand.RIGHT, 15, 82, 240,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "c/capelca01.html"),
                          STATPATH + "hou/clint_capela.csv", online));
        case "Tyson Chandler":
          return new Player(new String[] {"Tyson", "Grant", "Chandler"},
                  "Tyson", new Date(1982, 10, 2),
                  Position.CENTER, DominantHand.RIGHT, 5, 85, 240,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "c/chandty01.html"),
                          STATPATH + "lal/tyson_chandler.csv", online));
        case "DeMarcus Cousins":
          return new Player(new String[] {"DeMarcus", "Amir", "Cousins"},
                  "DeMarcus", new Date(1990, 8, 13),
                  Position.CENTER, DominantHand.RIGHT, 0, 83, 270,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "c/couside01.html"),
                          STATPATH + "gsw/demarcus_cousins.csv", online));
        case "Steph Curry":
        case "Stephen Curry":
          return new Player(new String[] {"Wardell", "Stephen", "Curry"},
                  "Steph", new Date(1988, 3, 14),
                  Position.POINT_GUARD, DominantHand.RIGHT, 30, 75, 190,
                  StatCalculator.updateAndFetch(new URL(
                                  BBRLINK + "c/curryst01.html"),
                          STATPATH + "gsw/steph_curry.csv", online));
        case "Seth Curry":
          return new Player(new String[] {"Seth", "Adham", "Curry"},
                  "Seth", new Date(1990, 8, 23),
                  Position.POINT_GUARD, DominantHand.RIGHT, 31, 74, 185,
                  StatCalculator.updateAndFetch(new URL(
                                  BBRLINK + "c/curryse01.html"),
                          STATPATH + "por/seth_curry.csv", online));
        case "Luka Doncic":
          return new Player(new String[] {"Luka", "Doncic"},
                  "Luka", new Date(1999, 2, 28),
                  Position.POWER_FORWARD, DominantHand.RIGHT, 77, 79, 250,
                  StatCalculator.updateAndFetch(new URL(
                                  BBRLINK + "d/doncilu01.html"),
                          STATPATH + "dal/luka_doncic.csv", online));
        case "Tim Duncan":
          return new Player(new String[] {"Timothy", "Theodore", "Duncan"},
                  "Tim", new Date(1976, 4, 25),
                  Position.POWER_FORWARD, DominantHand.RIGHT, 21, 83, 250,
                  StatCalculator.best(STATPATH + "leg/tim_duncan.csv"));
        case "Kevin Durant":
          return new Player(new String[] {"Kevin", "Wayne", "Durant"},
                  "Kevin", new Date(1988, 9, 29),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 35, 81, 240,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "d/duranke01.html"),
                          STATPATH + "gsw/kevin_durant.csv", online));
        case "Joel Embiid":
          return new Player(new String[] {"Joel", "Hans", "Embiid"},
                  "Joel", new Date(1994, 3, 16),
                  Position.CENTER, DominantHand.RIGHT, 21, 84, 250,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "e/embiijo01.html"),
                          STATPATH + "phi/joel_embiid.csv", online));
        case "Paul George":
          return new Player(new String[] {"Paul", "Clifton", "Anthony", "George"},
                  "Paul", new Date(1990, 5, 2),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 13, 81, 220,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "g/georgpa01.html"),
                          STATPATH + "okc/paul_george.csv", online));
        case "Rudy Gobert":
          return new Player(new String[] {"Rudy", "Gobert"},
                  "Rudy", new Date(1992, 6, 26),
                  Position.CENTER, DominantHand.RIGHT, 27, 85, 245,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "g/goberru01.html"),
                          STATPATH + "uta/rudy_gobert.csv", online));
        case "Draymond Green":
          return new Player(new String[] {"Draymond", "Jamal", "Green"},
                  "Draymond", new Date(1990, 3, 4),
                  Position.POWER_FORWARD, DominantHand.RIGHT, 23, 79, 230,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "g/greendr01.html"),
                          STATPATH + "gsw/draymond_green.csv", online));
        case "James Harden":
          return new Player(new String[] {"James", "Edward", "Harden"},
                  "James", new Date(1989, 8, 26),
                  Position.SHOOTING_GUARD, DominantHand.LEFT, 13, 77, 220,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "h/hardeja01.html"),
                          STATPATH + "hou/james_harden.csv", online));
        case "Gordon Hayward":
          return new Player(new String[] {"Gordon", "Daniel", "Hayward"},
                  "Gordon", new Date(1990, 3, 23),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 20, 80, 226,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "h/haywago01.html"),
                          STATPATH + "bos/gordon_hayward.csv", online));
        case "Al Horford":
          return new Player(new String[] {"Alfred", "Joel", "Horford"},
                  "Al", new Date(1986, 6, 3),
                  Position.CENTER, DominantHand.RIGHT, 42, 82, 245,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "h/horfoal01.html"),
                          STATPATH + "bos/al_horford.csv", online));
        case "Brandon Ingram":
          return new Player(new String[] {"Brandon", "Xavier", "Ingram"},
                  "Brandon", new Date(1997, 9, 2),
                  Position.SHOOTING_GUARD, DominantHand.RIGHT, 14, 81, 190,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "i/ingrabr01.html"),
                          STATPATH + "lal/brandon_ingram.csv", online));
        case "Kyrie Irving":
          return new Player(new String[] {"Kyrie", "Andrew", "Irving"},
                  "Kyrie", new Date(1992, 3, 23),
                  Position.POINT_GUARD, DominantHand.RIGHT, 11, 75, 193,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "i/irvinky01.html"),
                          STATPATH + "bos/kyrie_irving.csv", online));
        case "LeBron James":
          return new Player(new String[] {"LeBron", "Raymone", "James"},
                  "LeBron", new Date(1984, 12, 30),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 23, 80, 250,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "j/jamesle01.html"),
                          STATPATH + "lal/lebron_james.csv", online));
        case "Magic Johnson":
          return new Player(new String[] {"Earvin", "Johnson"},
                  "Magic", new Date(1959, 8, 14),
                  Position.POINT_GUARD, DominantHand.RIGHT, 32, 81, 215,
                  StatCalculator.best(STATPATH + "leg/magic_johnson.csv"));
        case "Michael Jordan":
          return new Player(new String[] {"Michael", "Jeffrey", "Jordan"},
                  "Michael", new Date(1963, 2, 17),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 23, 78, 195,
                  StatCalculator.best(STATPATH + "leg/michael_jordan.csv"));
        case "Kyle Kuzma":
          return new Player(new String[] {"Kyle", "Alexander", "Kuzma"},
                  "Kyle", new Date(1995, 7, 24),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 0, 81, 240,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "k/kuzmaky01.html"),
                          STATPATH + "lal/kyle_kuzma.csv", online));
        case "Kawhi Leonard":
          return new Player(new String[] {"Kawhi", "Leonard"},
                  "Kawhi", new Date(1991, 6, 29),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 2, 79, 230,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "l/leonaka01.html"),
                          STATPATH + "tor/kawhi_leonard.csv", online));
        case "Damian Lillard":
          return new Player(new String[] {"Damian", "Lamonte", "Ollie", "Lillard"},
                  "Damian", new Date(1990, 7, 15),
                  Position.POINT_GUARD, DominantHand.RIGHT, 0, 75, 195,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "l/lillada01.html"),
                          STATPATH + "por/damian_lillard.csv", online));
        case "Kevon Looney":
          return new Player(new String[] {"Kevon", "Grant", "Looney"},
                  "Kevon", new Date(1996, 2, 6),
                  Position.CENTER, DominantHand.RIGHT, 5, 81, 220,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "l/looneke01.html"),
                          STATPATH + "gsw/kevon_looney.csv", online));
        case "Kyle Lowry":
          return new Player(new String[] {"Kyle", "Lowry"},
                  "Kyle", new Date(1986, 3, 25),
                  Position.POINT_GUARD, DominantHand.RIGHT, 7, 72, 205,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "l/lowryky01.html"),
                          STATPATH + "tor/kyle_lowry.csv", online));
        case "CJ McCollum":
          return new Player(new String[] {"Christian", "James", "McCollum"},
                  "CJ", new Date(1991, 9, 19),
                  Position.SHOOTING_GUARD, DominantHand.RIGHT, 3, 75, 190,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "m/mccolcj01.html"),
                          STATPATH + "por/cj_mccollum.csv", online));
        case "Khris Middleton":
          return new Player(new String[] {"James", "Khristian", "Middleton"},
                  "Khris", new Date(1991, 8, 12),
                  Position.SMALL_FORWARD, DominantHand.RIGHT, 22, 80, 234,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "m/middlkh01.html"),
                          STATPATH + "mil/khris_middleton.csv", online));
        case "Donovan Mitchell":
          return new Player(new String[] {"Donovan", "Mitchell"},
                  "Donovan", new Date(1996, 9, 7),
                  Position.SHOOTING_GUARD, DominantHand.RIGHT, 45, 75, 211,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "m/mitchdo01.html"),
                          STATPATH + "uta/donovan_mitchell.csv", online));
        case "Chris Paul":
          return new Player(new String[] {"Christopher", "Emmanuel", "Paul"},
                  "Chris", new Date(1985, 5, 6),
                  Position.POINT_GUARD, DominantHand.RIGHT, 3, 72, 175,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "p/paulch01.html"),
                          STATPATH + "hou/chris_paul.csv", online));
        case "Kristaps Porzingis":
          return new Player(new String[] {"Kristaps", "Porzingis"},
                  "Kristaps", new Date(1995, 8, 2),
                  Position.POWER_FORWARD, DominantHand.RIGHT, 6, 87, 240,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "p/porzikr01.html"),
                          STATPATH + "nyk/kristaps_porzingis.csv", online));
        case "Derrick Rose":
          return new Player(new String[] {"Derrick", "Martell", "Rose"},
                  "Derrick", new Date(1988, 10, 4),
                  Position.POINT_GUARD, DominantHand.RIGHT, 25, 75, 190,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "r/rosede01.html"),
                          STATPATH + "min/derrick_rose.csv", online));
        case "Ricky Rubio":
          return new Player(new String[] {"Ricard", "Rubio"},
                  "Ricky", new Date(1990, 10, 21),
                  Position.POINT_GUARD, DominantHand.RIGHT, 3, 76, 194,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "r/rubiori01.html"),
                          STATPATH + "uta/ricky_rubio.csv", online));
        case "Ben Simmons":
          return new Player(new String[] {"Benjamin", "David", "Simmons"},
                  "Ben", new Date(1996, 7, 20),
                  Position.POINT_GUARD, DominantHand.LEFT, 25, 82, 230,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "s/simmobe01.html"),
                          STATPATH + "phi/ben_simmons.csv", online));
        case "Jayson Tatum":
          return new Player(new String[] {"Jayson", "Christopher", "Tatum"},
                  "Jayson", new Date(1998, 3, 3),
                  Position.POWER_FORWARD, DominantHand.RIGHT, 0, 80, 205,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "t/tatumja01.html"),
                          STATPATH + "bos/jayson_tatum.csv", online));
        case "Klay Thompson":
          return new Player(new String[] {"Klay", "Alexander", "Thompson"},
                  "Klay", new Date(1988, 9, 29),
                  Position.SHOOTING_GUARD, DominantHand.RIGHT, 11, 79, 215,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "t/thompkl01.html"),
                          STATPATH + "gsw/klay_thompson.csv", online));
        case "Karl-Anthony Towns":
          return new Player(new String[] {"Karl-Anthony", "Towns"},
                  "Karl-Anthony", new Date(1995, 11, 15),
                  Position.CENTER, DominantHand.RIGHT, 32, 84, 244,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "t/townska01.html"),
                          STATPATH + "min/karlanthony_towns.csv", online));
        case "Jonas Valanciunas":
          return new Player(new String[] {"Jonas", "Valanciunas"},
                  "Jonas", new Date(1992, 5, 6),
                  Position.POINT_GUARD, DominantHand.RIGHT, 17, 84, 265,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "v/valanjo01.html"),
                          STATPATH + "tor/jonas_valanciunas.csv", online));
        case "Russell Westbrook":
          return new Player(new String[] {"Russell", "Westbrook"},
                  "Russell", new Date(1988, 11, 12),
                  Position.POINT_GUARD, DominantHand.RIGHT, 0, 75, 200,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "w/westbru01.html"),
                          STATPATH + "okc/russell_westbrook.csv", online));
        default:
          return new Player(new String[] {name.substring(0, name.indexOf(" ")),
                  name.substring(name.indexOf(" ") + 1)}, "[BOT] " +
                  name.substring(0, name.indexOf(" ")),
                  new Date(2000, 1, 1));
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
