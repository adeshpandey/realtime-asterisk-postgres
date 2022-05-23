package me.adesh.asterisk.model.enums;

public enum DtmfMode {
  rfc4733("rfc4733"),
  inband("inband"),
  info("info"),
  auto("auto"),
  auto_info("auto_info");

  private final String name;

  DtmfMode(String name) {
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