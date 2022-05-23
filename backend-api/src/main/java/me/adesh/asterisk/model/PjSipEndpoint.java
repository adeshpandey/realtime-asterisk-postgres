package me.adesh.asterisk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.adesh.asterisk.model.enums.AuthType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ps_endpoints")
@TypeDef(name = "pjsip_auth_type_values_v2", typeClass = PostgresEnumType.class)
public class PjSipEndpoint {

  @Id
  @Column(name = "id", length = 40)
  private String id;

  @Column(name = "auth_type")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_auth_type_values_v2")
  private AuthType authType;

  @Column(name = "nonce_lifetime")
  private int nonceLifetime;

  @Column(name = "md5_cred", length = 40)
  private String md5Cred;

  @Column(name = "password", length = 80)
  private String password;

  @Column(name = "realm", length = 40)
  private String realm;

  @Column(name = "username", length = 40)
  private String username;

  @Column(name = "refresh_token", length = 255)
  private String refreshToken;

  @Column(name = "oauth_clientid", length = 255)
  private String oauthClientId;

  @Column(name = "oauth_secret", length = 255)
  private String oauthSecret;

}
