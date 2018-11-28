package hoops_root.traits.positional_comparators;

import hoops_root.Player;
import hoops_root.traits.Position;

import java.util.Comparator;

public class CComparator implements Comparator<Player> {

  @Override
  public int compare(Player a, Player b) {
    return Player.overall(b, Position.CENTER) -
            Player.overall(a, Position.CENTER);
  }
}
