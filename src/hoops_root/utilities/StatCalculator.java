package hoops_root.utilities;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatCalculator {
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

    // shooting
    float s3ptPerc = 0;

    if (!table.get(index)[13].equals("")) {
      s3ptPerc = Float.parseFloat(table.get(index)[13]); // cap at .5
    }

    float s2ptPerc = Float.parseFloat(table.get(index)[16]); // cap at .6

    stats[0] = Math.min((int)((((s3ptPerc * 6) + (s2ptPerc * 4)) / 10) * 196), 99);

    // rebounds
    float totalRebounds = Float.parseFloat(table.get(index)[23]); // mlt by 9 and cap
    stats[1] = Math.min(50 + (int)(Math.sqrt(totalRebounds) * 15), 99);

    // athleticism
    float ppg = Float.parseFloat(table.get(index)[29]);
    float mins = Float.parseFloat(table.get(index)[7]);
    stats[2] = Math.min(50 + (int)((ppg + mins) * (.7)), 99);

    // passing
    float assists = Float.parseFloat(table.get(index)[24]);
    stats[3] = Math.min(50 + (int)(Math.sqrt(assists) * 15), 99);

    // vision
    float pointsToTurnovers = Float.parseFloat(table.get(index)[29]) /
            Float.parseFloat(table.get(index)[27]);
    stats[4] = Math.min((int)(5 * pointsToTurnovers) +
            (int)(Math.sqrt(assists) * 15), 99);

    // clutch TODO
    stats[5] = 70; // how to assess clutch?

    // free throw
    stats[6] = 30;
    if (table.get(index)[20].length() > 0) {
      float freeThrow = Float.parseFloat(table.get(index)[20]);
      stats[6] = Math.min((int)(freeThrow * 100) + 6, 99);
    }

    // blocking
    float blocking = Float.parseFloat(table.get(index)[26]);
    stats[7] = Math.min(50 + (int)(Math.sqrt(blocking) * 28), 99);

    // driving
    float s2ps = Float.parseFloat(table.get(index)[14]);
    stats[8] = Math.min(50 + (int)(ppg * Math.pow(s2ps, (1/4.0))), 99);

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
      stats[9] = 0;
    } else if (gamesPerSeason >= 70) {
      stats[9] = 1;
    } else if (gamesPerSeason >= 60) {
      stats[9] = 2;
    } else if (gamesPerSeason >= 50) {
      stats[9] = 3;
    } else {
      stats[9] = 4;
    }

    // flagrant
    float fouls = Float.parseFloat(table.get(index)[28]);
    stats[10] = Math.max(0, Math.min((int)(fouls) - 1, 4));

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
    return statsList.get(k);
  }

  public static int[] updateAndFetch(URL url, String location, boolean online) {
    if (online) {
      update(url, location);
    }
    return calculate(location);
  }
}
