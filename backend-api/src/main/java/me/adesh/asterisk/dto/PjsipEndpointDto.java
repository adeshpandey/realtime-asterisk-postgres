package me.adesh.asterisk.dto;

import lombok.Builder;
import lombok.Data;
import me.adesh.asterisk.model.enums.YesNo;

@Data
@Builder
public class PjsipEndpointDto {

  private String id;

  private String auth;

  private String aors;
}
