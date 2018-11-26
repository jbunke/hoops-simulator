import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    // TODO - Pacific Division (GSW, LAC, LAL, PHX, SAC)
    Player[] gswRoster = new Player[12];
    gswRoster[0] = new Player(new String[] {"Wardell", "Stephen", "Curry"},
            "Steph", new Date(1988, 3, 14));
    gswRoster[1] = new Player(new String[] {"Kevin", "Wayne", "Durant"},
            "Kevin", new Date(1988, 9, 29));
    // TODO
    for (int i = 2; i < 12; i++) {
      gswRoster[i] = new Player(new String[] {"Bot"},
              "A", new Date(2000, 1 ,1));
    }
    Team gsw = new Team("GSW", "Golden State Warriors", gswRoster);

    List<Team> pacificTeams = new ArrayList<>();
    pacificTeams.add(gsw);

    Player MVP = gsw.getPlayer("Steph Curry");

    Division pacific = new Division("Pacific", pacificTeams);
  }
}
