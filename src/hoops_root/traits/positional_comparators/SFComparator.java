package hoops_root.traits.positional_comparators;

import hoops_root.Player;
import hoops_root.traits.Position;

import java.util.Comparator;

public class SFComparator implements Comparator<Player> {

  @Override
  public int compare(Player a, Player b) {
    return Player.overall(b, Position.SMALL_FORWARD) -
            Player.overall(a, Position.SMALL_FORWARD);
  }
}
