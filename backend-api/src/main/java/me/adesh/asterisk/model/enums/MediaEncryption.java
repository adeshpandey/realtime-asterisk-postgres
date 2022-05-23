package me.adesh.asterisk.model.enums;

public enum MediaEncryption {
  no("no"),
  sdes("sdes"),
  dtls("dtls");

  private final String name;

  MediaEncryption(String name) {
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
