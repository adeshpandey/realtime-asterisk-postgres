package me.adesh.asterisk.dto;

import lombok.Getter;
import me.adesh.asterisk.model.enums.AuthType;

@Getter
public class AuthRequest {

  private String id;

  private AuthType authType;

  private String username;

  private String password;

}
