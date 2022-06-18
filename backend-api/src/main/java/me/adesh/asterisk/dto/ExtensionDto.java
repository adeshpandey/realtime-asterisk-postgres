package me.adesh.asterisk.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExtensionDto {
  private Integer id;

  private String context;

  private String extension;

  private Integer priority;

  private String app;

  private String appData;
}
