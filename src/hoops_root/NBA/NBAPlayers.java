package hoops_root.NBA;

import hoops_root.Date;
import hoops_root.Player;
import hoops_root.traits.DominantHand;
import hoops_root.traits.Position;
import hoops_root.utilities.StatCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

class NBAPlayers {
  private static final String BBRLINK = "https://www.basketball-reference.com/players/";
  private static final String STATPATH = "resources/player_stats/NBA/";
  private static final String INFOPATH = "resources/player_info/NBA/";

  static Player getPlayer(String name, boolean online, boolean best) {
    try {
      switch (name) {
        case "Clint Capela":
          return new Player(new String[] {"Clint", "Capela"},
                  "Clint", new Date(1994, 5, 18),
                  Position.CENTER, DominantHand.RIGHT, 15, 82, 240,
                  StatCalculator.updateAndFetch(new URL(BBRLINK + "c/capelca01.html"),
                          STATPATH + "c/clint_capela.csv", online));
        case "Damian Jones":
          return NBAPlayerFromName(name, 3, online, best);
        case "Kemba Walker":
        case "Jaylen Brown":
        case "Bojan Bogdanovic":
        case "Tobias Harris":
        case "Lou Williams":
        case "Danny Green":
        case "Anthony Davis":
        case "Harrison Barnes":
        case "Magic Johnson":
          return NBAPlayerFromName(name, 2, online, best);
        default:
          return NBAPlayerFromName(name, 1, online, best);
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static Player NBAPlayerFromName(String prefName, boolean online,
                                          boolean best, String statFilePath,
                                          String infoFilePath, URL url) {
    int[] stats;
    if (best) {
      stats = StatCalculator.best(statFilePath);
    } else {
      stats = StatCalculator.updateAndFetch(url, statFilePath, online);
    }

    if (online) {
      writeToInfoFilePath(url, infoFilePath);
    }
    return readFromInfoFilePath(infoFilePath, prefName, stats);
  }

  static Player NBAPlayerFromName(String name, boolean online, boolean best, String linkTail) {
    URL url = null;

    try {
      url = new URL(BBRLINK + linkTail);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return NBAPlayerFromName(prefName(name), online, best,
            STATPATH + fileName(name), INFOPATH + fileName(name), url);
  }

  private static String prefName(String name) {
    if (!name.contains(" ")) return name;

    return name.substring(0, name.indexOf(" "));
  }

  private static String lastName(String name) {
    if (!name.contains(" ")) return name;

    return name.substring(name.indexOf(" ") + 1);
  }

  private static String fileName(String name) {
    String fileName = name.replaceAll(" ", "_").toLowerCase();
    fileName = lastName(name).substring(0, 1).toLowerCase() +
            "/" + fileName + ".csv";
    return fileName;
  }

  private static Player NBAPlayerFromName(String name, int num, boolean online,
                                          boolean best) {
    String fileName = name.replaceAll(" ", "_").toLowerCase();
    fileName = lastName(name).substring(0, 1).toLowerCase() +
            "/" + fileName + ".csv";
    String statFilePath = STATPATH + fileName;
    String infoFilePath = INFOPATH + fileName;
    String lastName = lastName(name).toLowerCase();
    if (lastName.length() > 5) {
      lastName = lastName.substring(0, 5);
    }
    String code = lastName.substring(0, 1) + "/" + lastName +
            name.substring(0, 2).toLowerCase() + "0" +
            String.valueOf(num) + ".html";
    URL url = null;
    try {
      url = new URL(BBRLINK + code);
    } catch (MalformedURLException e) {
      System.out.println("Invalid URL");
    }

    return NBAPlayerFromName(prefName(name), online, best, statFilePath, infoFilePath, url);
  }

  private static Player readFromInfoFilePath(String infoFilePath,
                                           String prefName, int[] stats) {
    BufferedReader br;
    String line;

    try {
      br = new BufferedReader(new FileReader(infoFilePath));
      line = br.readLine();
      String[] info = line.split(",");

      String fullName = info[0];
      fullName = fullName.replace(" Jr.", "").
              replace(" Sr.", "").replace(" III", "").
              replace(" II", "");
      String[] names = fullName.split(" ");
      Position position = Position.POINT_GUARD;
      String pos = info[1].toLowerCase();
      if (pos.contains("pow")) {
        position = Position.POWER_FORWARD;
      } else if (pos.contains("cen")) {
        position = Position.CENTER;
      } else if (pos.contains("sho")) {
        position = Position.SHOOTING_GUARD;
      } else if (pos.contains("sma")) {
        position = Position.SMALL_FORWARD;
      }
      DominantHand hand = DominantHand.fromString(info[2]);
      int height = (Integer.parseInt(info[3].substring(0, 1)) * 12) +
              Integer.parseInt(info[3].substring(2));
      int weight = Integer.parseInt(info[4]);
      String birthday = info[5];
      int month = month(birthday.substring(0, birthday.indexOf(" ")));
      int day = Integer.parseInt(birthday.substring(birthday.indexOf(" ") + 1));
      int year = Integer.parseInt(info[6]);
      int preferredNumber = info.length >= 8 ? Integer.parseInt(info[7]) : 0;

      return new Player(names, prefName, new Date(year, month, day),
              position, hand, preferredNumber, height, weight, stats);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void writeToInfoFilePath(URL url, String infoFilePath) {
    URLConnection connection;
    FileWriter writer;
    String line;
    try {
      connection = url.openConnection();
      writer = new FileWriter(infoFilePath, false);
      Scanner scanner = new Scanner(connection.getInputStream());
      int phase = 0;
      while (scanner.hasNext()) {
        line = scanner.nextLine();
        if (line.contains("<h1 itemprop=\"name\">") && phase == 0) {
          phase++; // name found
        }
        if (line.contains("<strong>") && line.contains("</strong>") &&
                !(line.contains("Pronunciation")) && phase == 1) {
          String fullName = line.substring(line.indexOf("<strong>") + 8,
                  line.indexOf("</st"));
          writer.write(fullName + ",");
          phase++; // full name parsed and written to info file
        }
        if (line.contains("Position") && phase == 2) {
          scanner.nextLine();
          line = scanner.nextLine();
          if (line.contains("and")) {
            line = line.substring(0, line.indexOf(" and"));
            line = line.replaceAll(" ", "");
          }
          writer.write(line + ",");
          phase++;
        }
        if (line.contains("Shoots") && phase == 3) {
          scanner.nextLine();
          line = scanner.nextLine();
          writer.write(line.toUpperCase().replaceAll(" ", "") + ",");
          phase++;
        }
        if (line.contains("height") && phase == 4) {
          line = line.substring(line.indexOf("height\">") + 8);
          String height = line.substring(0, line.indexOf("<"));
          line = line.substring(line.indexOf("weight\">") + 8);
          String weight = line.substring(0, line.indexOf("lb"));
          writer.write(height + "," + weight + ",");
          phase++;
        }
        if (line.contains("day=") && phase == 5) {
          line = line.substring(line.indexOf(">") + 1);
          line = line.substring(0, line.indexOf("<"));
          writer.write(line + ",");
          phase++;
        }
        if (line.contains("year=") && phase == 6) {
          line = line.substring(line.indexOf(">") + 1);
          writer.write(line.substring(0, 4) + ",");
          phase++;
        }
        if (line.contains("number=") && phase == 7) {
          line = line.substring(line.indexOf("number=") + 7);
          line = line.substring(0, line.indexOf("\""));
          writer.write(line);
          writer.close();
          break;
        }
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static int month(String month) {
    switch (month) {
      case "January":
        return 1;
      case "February":
        return 2;
      case "March":
        return 3;
      case "April":
        return 4;
      case "May":
        return 5;
      case "June":
        return 6;
      case "July":
        return 7;
      case "August":
        return 8;
      case "September":
        return 9;
      case "October":
        return 10;
      case "November":
        return 11;
      case "December":
      default:
        return 12;
    }
  }
}
