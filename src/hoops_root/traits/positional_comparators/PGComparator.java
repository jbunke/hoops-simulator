package hoops_root.traits.positional_comparators;

import hoops_root.Player;
import hoops_root.traits.Position;

import java.util.Comparator;

public class PGComparator implements Comparator<Player> {

  @Override
  public int compare(Player a, Player b) {
    return Player.overall(b, Position.POINT_GUARD) -
            Player.overall(a, Position.POINT_GUARD);
  }
}
