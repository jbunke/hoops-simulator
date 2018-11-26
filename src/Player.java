public class Player {
  private String[] name;
  private String prefName;
  private Date birthDate;
  private Team team;

  // TRAITS


  // STATS

  Player(String[] name, String prefName, Date birthDate) {
    this.name = name;
    this.prefName = prefName;
    this.birthDate = birthDate;
  }

  Team getTeam() { return team; }

  void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public String toString() {
    return prefName + " " + name[name.length - 1];
  }
}
