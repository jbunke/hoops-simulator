public class Player {
  private String[] name;
  private String prefName;
  private Date birthDate;

  // TRAITS


  // STATS

  Player(String[] name, String prefName, Date birthDate) {
    this.name = name;
    this.prefName = prefName;
    this.birthDate = birthDate;
  }

  @Override
  public String toString() {
    return prefName + " " + name[name.length - 1];
  }
}
