package me.adesh.asterisk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.type.YesNoType;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "ps_aors")
public class PjSipAor {

  public PjSipAor() {

  }

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
  private Boolean removeExisting;

  @Column(name = "qualify_frequency", nullable = true)
  private Integer qualifyFrequency;

  @Column(name = "authenticate_qualify", nullable = true)
  private Boolean authenticateQualify;

  @Column(name = "maximum_expiration", nullable = true)
  private Integer maximumExpiration;

  @Column(name = "outbound_proxy", length = 40, nullable = true)
  private String outboundProxy;

  @Column(name = "support_path", nullable = true)
  private Boolean supportPath;

  @Column(name = "qualify_timeout", nullable = true)
  private Double qualifyTimeout;

  @Column(name = "voicemail_extension", length = 40, nullable = true)
  private String voicemailExtension;

  @Column(name = "remove_unavailable", nullable = true)
  private Boolean removeUnavailable;


}
