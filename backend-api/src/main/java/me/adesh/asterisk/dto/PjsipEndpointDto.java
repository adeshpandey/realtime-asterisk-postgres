package me.adesh.asterisk.dto;

import lombok.Builder;
import lombok.Data;
import me.adesh.asterisk.model.enums.YesNo;

@Data
@Builder
public class PjsipEndpointDto {

  private String id;

  private String context;

  private String auth;

  private String aors;

  public String[] getAors(){
    return aors.split(",");
  }

  private String allow;

  public String[] getAllow(){
    String[] allowList = {};
    return allow!=null ? allow.split(",") : allowList;
  }
}
