package me.adesh.asterisk.model.enums;

public enum PjsipTimer {
  forced("forced"),
  no("no"),
  required("required"),
  yes("yes");

  private final String name;

  PjsipTimer(String name) {
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