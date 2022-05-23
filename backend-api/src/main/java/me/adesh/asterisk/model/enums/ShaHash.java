package me.adesh.asterisk.model.enums;

public enum ShaHash {
  sha1("SHA-1"),
  sha256("SHA-256");

  private final String name;

  ShaHash(String name) {
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
