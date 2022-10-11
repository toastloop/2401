class Planet {
  private String name;
  private long diameter;
  private int moon;

  Planet next;

  Planet(String n, long d, int m) {
    name = n;
    diameter = d;
    moon = m;
  }

  String getName() {
    return name;
  }

  long getDiameter() {
    return diameter;
  }

  int getMoon() {
    return moon;
  }

  public String toString() {
    return "Name: " + name +
        "\nDiameter: " + diameter +
        "\nMoon: " + moon;
  }
}