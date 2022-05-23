package me.adesh.asterisk.model.enums;

public enum CidPrivacy {
  allowed_not_screened("allowed_not_screened"),
  allowed_passed_screened("allowed_passed_screened"),
  allowed_failed_screened("allowed_failed_screened"),
  allowed("allowed"),
  prohib_not_screened("prohib_not_screened"),
  prohib_passed_screened("prohib_passed_screened"),
  prohib_failed_screened("prohib_failed_screened"),
  prohib("prohib"),
  unavailable("unavailable");

  private final String name;

  CidPrivacy(String name) {
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
