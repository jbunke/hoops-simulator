package hoops_root;

import java.util.Comparator;

public class StandingsComparator implements Comparator<Team> {
  @Override
  public int compare(Team a, Team b) {
    if (a.getRecord().losses() == b.getRecord().losses()) {
      return b.getRecord().wins() - a.getRecord().wins();
    }
    return a.getRecord().losses() - b.getRecord().losses();
  }
}
