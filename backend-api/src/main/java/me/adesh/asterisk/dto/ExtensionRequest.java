package me.adesh.asterisk.dto;

import lombok.Getter;
import lombok.Setter;
import me.adesh.asterisk.model.enums.YesNo;

@Getter
@Setter
public class ExtensionRequest {

  private String context;

  private String extension;

  private Integer priority;

  private String app;

  private String appData;

}
