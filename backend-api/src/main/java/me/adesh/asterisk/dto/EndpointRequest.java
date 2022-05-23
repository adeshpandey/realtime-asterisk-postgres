package me.adesh.asterisk.dto;

import lombok.Getter;
import lombok.Setter;
import me.adesh.asterisk.model.PjSipAuth;
import me.adesh.asterisk.model.enums.AuthType;

@Getter
@Setter
public class EndpointRequest {

  private String id;

  private String auth;

  private String aors;

}
