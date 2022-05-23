package me.adesh.asterisk.model.enums;

public enum T38UdpTl {

  no("no"),
  sdes("sdes"),
  dtls("dtls");

  private final String name;

  T38UdpTl(String name) {
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
