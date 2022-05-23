package me.adesh.asterisk.dto;

import lombok.Builder;
import lombok.Data;
import me.adesh.asterisk.model.enums.YesNo;

@Data
@Builder
public class PjSipAorDto {

  private String id;

  private String contact;

  private YesNo removeExisting;

  private Integer defaultExpiration;

  private String mailBoxes;

  private Integer maxContacts;

  private Integer minimumExpiration;

  private Integer qualifyFrequency;

  private YesNo authenticateQualify;

  private Integer maximumExpiration;

  private String outboundProxy;

  private YesNo supportPath;

  private Double qualifyTimeout;

  private String voicemailExtension;

  private YesNo removeUnavailable;
}
