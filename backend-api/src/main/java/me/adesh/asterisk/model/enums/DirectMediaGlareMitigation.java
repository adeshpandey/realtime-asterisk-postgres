package me.adesh.asterisk.model.enums;

public enum DirectMediaGlareMitigation {
  none("none"),
  outgoing("outgoing"),
  incoming("incoming");

  private final String name;

  DirectMediaGlareMitigation(String name) {
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