package me.adesh.asterisk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndpointRequest {

  private String id;

  private String context;

  private String auth;

  private String[] aors;

  private String[] allow;

}
