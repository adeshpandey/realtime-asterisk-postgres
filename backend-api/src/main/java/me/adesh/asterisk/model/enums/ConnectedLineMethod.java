package me.adesh.asterisk.model.enums;


public enum ConnectedLineMethod {
  invite("invite"),
  reinvite("reinvite"),
  update("update");

  private final String name;

  ConnectedLineMethod(String name) {
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