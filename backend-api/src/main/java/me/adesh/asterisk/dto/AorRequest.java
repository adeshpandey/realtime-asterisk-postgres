package me.adesh.asterisk.dto;

import lombok.Getter;
import me.adesh.asterisk.model.enums.YesNo;

@Getter
public class AorRequest {

  private String id;

  private String contact;

  private Integer maxContacts;

  private YesNo removeExisting;

  private YesNo removeUnavailable;
}
