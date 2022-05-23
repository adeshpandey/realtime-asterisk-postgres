package me.adesh.asterisk.model.enums;

public enum PjSip100Rel {

  no("no"),
  required("required"),
  yes("yes");

  private final String name;

  PjSip100Rel(String name) {
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
