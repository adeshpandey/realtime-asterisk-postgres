package me.adesh.asterisk.model.enums;

public enum AuthType {
  md5("md5"),
  userpass("userpass"),
  google_oauth("google_oauth");

  private final String name;

  AuthType(String name) {
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

