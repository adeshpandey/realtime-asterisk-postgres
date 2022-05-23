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
import me.adesh.asterisk.model.enums.YesNo;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ps_aors")
@TypeDefs({@TypeDef(name = "yesno_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "ast_bool_values", typeClass = PostgresEnumType.class)})
public class PjSipAor {


  @Id
  @Column(name = "id", length = 40)
  private String id;

  @Column(name = "contact", nullable = true)
  private String contact;

  @Column(name = "default_expiration", nullable = true)
  private Integer defaultExpiration;

  @Column(name = "mailboxes", length = 80, nullable = true)
  private String mailBoxes;

  @Column(name = "max_contacts", nullable = true)
  private Integer maxContacts;

  @Column(name = "minimum_expiration", nullable = true)
  private Integer minimumExpiration;

  @Column(name = "remove_existing", nullable = true)
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo removeExisting;

  @Column(name = "qualify_frequency", nullable = true)
  private Integer qualifyFrequency;

  @Column(name = "authenticate_qualify")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo authenticateQualify;

  @Column(name = "maximum_expiration", nullable = true)
  private Integer maximumExpiration = 300;

  @Column(name = "outbound_proxy", length = 40, nullable = true)
  private String outboundProxy;

  @Column(name = "support_path", nullable = true)
  @Type(type = "yesno_values")
  private YesNo supportPath;

  @Column(name = "qualify_timeout", nullable = true)
  private Double qualifyTimeout;

  @Column(name = "voicemail_extension", length = 40, nullable = true)
  private String voicemailExtension;

  @Column(name = "remove_unavailable", nullable = true)
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private YesNo removeUnavailable;


}
