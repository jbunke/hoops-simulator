package hoops_root.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

  public static int[] calculate(String location) {
    List<String[]> table = readCSV(location);
    int[] stats = new int[11];
    int seasonsBack = 0;
    boolean foundSeason = false;

    // if 10 or more games of the current season have been played, sim from current
    if (Integer.parseInt(table.get(table.size() - 1)[5]) >= 10) {
      seasonsBack = 0;
    } else {
      seasonsBack++;
      while (!foundSeason && seasonsBack < table.size()) {
        if (Integer.parseInt(table.get(table.size() - (seasonsBack + 1))[5]) >= 50) {
          foundSeason = true;
        } else {
          seasonsBack++;
        }
      }
    }

    int index = table.size() - (seasonsBack + 1);

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
    float freeThrow = Float.parseFloat(table.get(index)[20]);
    stats[6] = Math.min((int)(freeThrow * 100) + 6, 99);

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
      gamesPlayed += Integer.parseInt(table.get(i)[5]);
      currentAge = Integer.parseInt(table.get(i)[1]);
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
}
