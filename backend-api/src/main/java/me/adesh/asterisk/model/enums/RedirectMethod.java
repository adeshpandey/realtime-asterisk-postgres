package me.adesh.asterisk.model.enums;

public enum RedirectMethod {
  user("user"),
  uri_code("uri_code"),
  uri_pjsip("uri_pjsip");

  private final String name;

  RedirectMethod(String name) {
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
