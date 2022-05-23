package me.adesh.asterisk.model.enums;

public enum YesNo {
  yes("yes"),
  no("no");

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

