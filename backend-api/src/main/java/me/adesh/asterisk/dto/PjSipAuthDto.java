package me.adesh.asterisk.dto;

import lombok.Builder;
import lombok.Data;
import me.adesh.asterisk.model.enums.AuthType;

@Builder
@Data
public class PjSipAuthDto {

  private String id;

  private AuthType authType;

  private String username;

  private String password;
}
