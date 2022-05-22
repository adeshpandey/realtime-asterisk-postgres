package me.adesh.asterisk.model;

public enum YesNo {
  YES("yes"),
  NO("no");

  private final String name;

  YesNo(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return name;
  }
}

