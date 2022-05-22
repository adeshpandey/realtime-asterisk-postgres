package me.adesh.asterisk.dto;

import javax.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PjSipAorDto {

  private String id;

  private String contact;

  private Boolean removeExisting;

  private Integer defaultExpiration;

  private String mailBoxes;

  private Integer maxContacts;

  private Integer minimumExpiration;

  private Integer qualifyFrequency;

  private Boolean authenticateQualify;

  private Integer maximumExpiration;

  private String outboundProxy;

  private Boolean supportPath;

  private Double qualifyTimeout;

  private String voicemailExtension;

  private Boolean removeUnavailable;
}
