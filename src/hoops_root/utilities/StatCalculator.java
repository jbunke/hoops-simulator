package hoops_root.utilities;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatCalculator {

  // CSV COLUMN INDICES
  private static final int G_P = 5, G_S = 6, MINUTES = 7, S_FG_MADE = 8,
          S_FG_ATTEMPTED = 9, S_FG_PERC = 10, S_3P_MADE = 11,
          S_3P_ATTEMPTED = 12, S_3P_PERC = 13, S_2P_MADE = 14,
          S_2P_ATTEMPTED = 15, S_2P_PERC = 16, S_EFG_PERC = 17,
          S_FT_MADE = 18, S_FT_ATTEMPTED = 19, S_FT_PERC = 20,
          S_OFF_REB = 21, S_DEF_REB = 22, S_TOT_REB = 23,
          S_ASSISTS = 24, S_STEALS = 25, S_BLOCKS = 26,
          S_TURNOVERS = 27, S_FOULS= 28, S_POINTS= 29;

  // STAT INDICES
  private static final int SHOOTING = 0, REBOUNDING = 1, ATHLETICISM = 2,
          PASSING = 3, VISION = 4, CLUTCH = 5, FREE_THROW = 6, DEFENSE = 7,
          DRIVING = 8, PRONENESS_TO_INJURY = 9, FOUL_TENDENCY = 10;

  private static List<String[]> readCSV(String location) {
    BufferedReader br;
    String line;
    String splitChar = ",";
    List<String[]> stats = new ArrayList<>();

    try {
      br = new BufferedReader(new FileReader(location));
      while ((line = br.readLine()) != null) {
        if (!(line.contains("Season") || line.contains("Career"))) {
          String[] season = line.split(splitChar);
          stats.add(season);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return stats;
  }

  private static int seasonIndex(List<String[]> table) {
    int seasonsBack = 0;
    boolean foundSeason = false;

    // if 10 or more games of the current season have been played, sim from current
    if (Integer.parseInt(table.get(table.size() - 1)[5]) >= 10) {
      seasonsBack = 0;
    } else {
      seasonsBack++;
      while (!foundSeason && seasonsBack < table.size()) {
        if (table.get(table.size() - (seasonsBack + 1)).length > 5 &&
                Integer.parseInt(table.get(table.size() - (seasonsBack + 1))[5]) >= 50) {
          foundSeason = true;
        } else {
          seasonsBack++;
        }
      }
    }

    return table.size() - (seasonsBack + 1);
  }

  private static int[] generateStats(List<String[]> table, int index) {
    int[] stats = new int[11];

    if (index == -1) { return stats; }

    int threshold = 0;
    for (String s : table.get(index)) {
      if (s.equals("")) { threshold++; }
    }
    if (threshold > 1) { return stats; }

    // SHOOTING: [3P% c@ 33 for 0.45] + [3PM c@ 33 for 4] + [2P% c@ 33 for 0.55]
    float s3ptPerc = 0;

    if (!table.get(index)[13].equals("")) {
      s3ptPerc = Float.parseFloat(table.get(index)[S_3P_PERC]); // cap at .45
    }

    float s2ptPerc = Float.parseFloat(table.get(index)[S_2P_PERC]); // cap at .55
    float s3ptMade = Float.parseFloat(table.get(index)[S_3P_MADE]);

    stats[SHOOTING] = (int)(Math.min(s3ptPerc, 0.45) * (33 / 0.45));
    stats[SHOOTING] += (int)(Math.min(s3ptMade, 4.0) * (33 / 4.0));
    stats[SHOOTING] += (int)(Math.min(s2ptPerc, 0.55) * (33 / 0.55));
    stats[SHOOTING] = Math.min(stats[SHOOTING], 99);

    // REBOUNDING: [50] + [sqrt(TRB) c@ 49 for 49/15]
    float totalRebounds = Float.parseFloat(table.get(index)[S_TOT_REB]); // mlt by 9 and cap
    stats[REBOUNDING] = Math.min(50 + (int)(Math.sqrt(totalRebounds) * 15), 99);

    // ATHLETICISM: [MIN c@ 33 for 33] + [(6 - PF) c@ 33 for 6] + [sqrt(PPG) + sqrt(AST) + sqrt(TRB) c@ 33 for 12]
    float ppg = Float.parseFloat(table.get(index)[S_POINTS]);
    float mins = Float.parseFloat(table.get(index)[MINUTES]);
    float fouls = Float.parseFloat(table.get(index)[S_FOULS]);
    float assists = Float.parseFloat(table.get(index)[S_ASSISTS]);
    stats[ATHLETICISM] = (int)(Math.min(mins, 33));
    stats[ATHLETICISM] += (int)(Math.max(7.0 - fouls, 0.0) * (33 / 6.0));
    stats[ATHLETICISM] += (int)(Math.min(Math.sqrt(ppg) +
            Math.sqrt(assists) + Math.sqrt(totalRebounds), 12.0) * (33 / 12.0));
    stats[ATHLETICISM] = Math.min(stats[ATHLETICISM], 99);
    stats[ATHLETICISM] = Math.max(stats[ATHLETICISM], 1);

    // PASSING: [sqrt(AST) c@ 53 for 3.2] + [AST / sqrt(TOV) c@ 53 for 4.0]
    float turnovers = Float.parseFloat(table.get(index)[S_TURNOVERS]);
    stats[PASSING] = (int)(Math.min(Math.sqrt(assists), 3.2) * (53 / 3.2));
    stats[PASSING] += (int)(Math.min(assists / Math.sqrt(turnovers), 5.0) * (53 / 5.0));
    stats[PASSING] = Math.min(stats[PASSING], 99);

    // VISION: [sqrt(STL) c@ 38 for 1.35] + [AST / MIN c@ 38 for 0.25] + [PPG c@ 30 for 28.5]
    float steals = Float.parseFloat(table.get(index)[S_STEALS]);
    stats[VISION] = (int)(Math.min(Math.sqrt(steals), 1.35) * (38 / 1.35));
    stats[VISION] += (int)(Math.min(assists / mins, 0.25) * (38 / 0.25));
    stats[VISION] += (int)(Math.min(ppg, 28.5) * (30 / 28.5));
    stats[VISION] = Math.min(stats[VISION], 99);

    // CLUTCH TODO
    stats[CLUTCH] = 70; // how to assess clutch?

    // FREE THROW: [FT% + 6 c@ 99]
    stats[FREE_THROW] = 30;
    if (table.get(index)[S_FT_PERC].length() > 0) {
      float freeThrow = Float.parseFloat(table.get(index)[S_FT_PERC]);
      stats[FREE_THROW] = Math.min((int)(freeThrow * 100) + 6, 99);
    }

    // DEFENSE: [sqrt(STL) / MIN c@ 55 for 0.038] + [sqrt(BLK) / MIN c@ 55 for 0.04]
    float blocking = Float.parseFloat(table.get(index)[S_BLOCKS]);
    stats[DEFENSE] = (int)(Math.min(Math.sqrt(steals) / mins, 0.038) * (55 / 0.038));
    stats[DEFENSE] += (int)(Math.min(Math.sqrt(blocking) / mins, 0.04) * (55 / 0.04));
    stats[DEFENSE] = Math.min(stats[DEFENSE], 99);

    // DRIVING...
    float s2ps = Float.parseFloat(table.get(index)[S_2P_MADE]);
    stats[DRIVING] = Math.min(50 + (int)(ppg * Math.pow(s2ps, (1/4.0))), 99);

    // injury-prone
    int gamesPlayed = 0;
    int startingAge = Integer.parseInt(table.get(0)[1]);
    int currentAge = Integer.parseInt(table.get(0)[1]);
    for (int i = 0; i < (table.size() - 1); i++) {
      if (table.get(i).length >= 6) {
        gamesPlayed += Integer.parseInt(table.get(i)[5]);
        currentAge = Integer.parseInt(table.get(i)[1]);
      }
    }
    int gamesPerSeason = gamesPlayed / ((currentAge + 1) - startingAge);

    if (gamesPerSeason >= 77) {
      stats[PRONENESS_TO_INJURY] = 0;
    } else if (gamesPerSeason >= 70) {
      stats[PRONENESS_TO_INJURY] = 1;
    } else if (gamesPerSeason >= 60) {
      stats[PRONENESS_TO_INJURY] = 2;
    } else if (gamesPerSeason >= 50) {
      stats[PRONENESS_TO_INJURY] = 3;
    } else {
      stats[PRONENESS_TO_INJURY] = 4;
    }

    // flagrant
    stats[FOUL_TENDENCY] = Math.max(0, Math.min((int)(fouls) - 1, 4));

    return stats;
  }

  private static int[] calculate(String location) {
    List<String[]> table = readCSV(location);

    if (table.size() == 0 || table.get(0).length <= 1)
      return new int[11];

    int index = seasonIndex(table);
    return generateStats(table, index);
  }

  private static void fileWrite(List<String> stats, String location) {
    FileWriter writer;

    try {
      writer = new FileWriter(location, false);

      for (String row : stats) {
        writer.write(row);
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void update(URL url, String location) {
    List<String> stats = new ArrayList<>();
    URLConnection connection;
    String line = "";

    try {
      connection = url.openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      boolean start = false;
      boolean done = false;
      while (!start && scanner.hasNext()) {
        line = scanner.nextLine();
        if (line.contains("<div class=\"overthrow table_container\" id=\"div_per_game\">")) {
          start = true;
        }
      }
      for (int i = 0; i < 4; i++) {
        // dump
        scanner.nextLine();
      }
      // Headers
      StringBuilder header = new StringBuilder("");
      for (int i = 0; i < 30 && scanner.hasNext(); i++) {
        if (i != 0) {
          header.append(",");
        }
        line = scanner.nextLine();
        header.append(
                line.substring(line.indexOf("\" >") + 3, line.indexOf("</th>")));
      }
      stats.add(header.toString());
      start = false;
      while (!start && scanner.hasNext()) {
        line = scanner.nextLine();
        if (line.contains("<tr")) {
          start = true;
        }
      }
      while (!done) {
        // ROWS
        int i = 0;
        String match = "a";
        if (line.contains("Career")) {
          done = true;
          match = "th";
          i = 50;
        }
        StringBuilder row = new StringBuilder("");

        while (line.contains("</" + match + ">")) {
          if (!row.toString().equals("")) {
            row.append(",");
          }
          while (line.indexOf("<") != line.indexOf("</" + match + ">")) {
            line = line.substring(line.indexOf(">") + 1);
          }
          row.append(line.substring(0, line.indexOf("</" + match + ">")));
          line = line.substring(line.indexOf("</" + match + ">") + 3 + match.length());
          i++;
          // RUDY GOBERT GLITCH FIX ATTEMPT
          if (line.indexOf("</") == 0) {
            line = line.substring(line.indexOf(">") + 1);
          }
          // matcher logic
          int[] matchers = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
          if (line.contains("</td")) {
            matchers[0] = line.indexOf("</td");
          }
          if (line.contains("</a")) {
            matchers[1] = line.indexOf("</a");
          }
          if (line.contains("</strong")) {
            matchers[2] = line.indexOf("</strong");
          }
          if (matchers[0] <= matchers[1] && matchers[0] <= matchers[2]) {
            match = "td";
          } else if (matchers[2] <= matchers[0] && matchers[2] <= matchers[0]) {
            match = "strong";
          } else {
            match = "a";
          }
          if (i == 3) { match = "a"; }
          if (line.indexOf("<" + match) > line.indexOf("</" + match)) {
            line = line.substring(line.indexOf("</" + match + ">") + 3 + match.length());
          }
        }

        if (!scanner.hasNext())
          break;

        stats.add(row.toString());
        line = scanner.nextLine();
        while (!done && !(line.contains("<tr") || line.contains("<strong"))) {
          line = scanner.nextLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    fileWrite(stats, location);
  }

  public static int[] best(String location) {
    List<String[]> table = readCSV(location);
    List<int[]> statsList = new ArrayList<>();
    int k = 1;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < table.size(); i++) {
      if (table.get(i).length == 30) {
        statsList.add(generateStats(table, i));
        int statSum = 0;
        for (int j = 0; j < 9; j++) {
          statSum += statsList.get(statsList.size() - 1)[j];
        }
        if (statSum > max) {
          max = statSum;
          k = statsList.size() - 1;
        }
      }
    }
    if (statsList.size() <= k)
      return new int[11];

    return statsList.get(k);
  }

  public static int[] updateAndFetch(URL url, String location, boolean online) {
    if (online) {
      update(url, location);
    }
    return calculate(location);
  }
}
