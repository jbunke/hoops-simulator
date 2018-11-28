package hoops_root;

public class Date {
  final int year;
  final int month;
  final int day;

  Date(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  @Override
  public String toString() {
    return year + "-" + month + "-" + day;
  }
}
