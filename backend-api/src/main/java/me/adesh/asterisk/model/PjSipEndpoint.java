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
import me.adesh.asterisk.model.enums.AstBool;
import me.adesh.asterisk.model.enums.CidPrivacy;
import me.adesh.asterisk.model.enums.ConnectedLineMethod;
import me.adesh.asterisk.model.enums.DirectMediaGlareMitigation;
import me.adesh.asterisk.model.enums.DtlsSetup;
import me.adesh.asterisk.model.enums.DtmfMode;
import me.adesh.asterisk.model.enums.MediaEncryption;
import me.adesh.asterisk.model.enums.PjSip100Rel;
import me.adesh.asterisk.model.enums.PjsipTimer;
import me.adesh.asterisk.model.enums.RedirectMethod;
import me.adesh.asterisk.model.enums.ShaHash;
import me.adesh.asterisk.model.enums.T38UdpTl;
import me.adesh.asterisk.model.enums.YesNo;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ps_endpoints")
@TypeDefs({
    @TypeDef(name = "yesno_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_connected_line_method_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_direct_media_glare_mitigation_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_dtmf_mode_values_v3", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_timer_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_cid_privacy_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_100rel_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_media_encryption_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_t38udptl_ec_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_redirect_method_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "ast_bool_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "sha_hash_values", typeClass = PostgresEnumType.class),
    @TypeDef(name = "pjsip_dtls_setup_values", typeClass = PostgresEnumType.class)
})
public class PjSipEndpoint {

  @Id
  private String id;
  private String transport;
  private String aors;
  private String auth;
  private String context;
  private String disallow;
  private String allow;

  @Column(name = "direct_media")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo directMedia;

  @Column(name = "connected_line_method")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_connected_line_method_values")
  private ConnectedLineMethod connectedLineMethod;

  @Column(name = "direct_media_method")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_connected_line_method_values")
  private ConnectedLineMethod directMediaMethod;

  @Column(name = "direct_media_glare_mitigation")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_direct_media_glare_mitigation_values")
  private DirectMediaGlareMitigation directMediaGlareMitigation;

  @Column(name = "disable_direct_media_on_nat")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo disableDirectMediaOnNat;

  @Column(name = "dtmf_mode")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_dtmf_mode_values_v3")
  private DtmfMode dtmfMode;

  @Column(name = "external_media_address")
  private String externalMediaAddress;

  @Column(name = "force_rport")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo forceRport;

  @Column(name = "ice_support")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo iceSupport;

  @Column(name = "identify_by")
  private String identifyBy;

  private String mailboxes;

  @Column(name = "moh_suggest")
  private String mohSuggest;

  @Column(name = "outbound_auth")
  private String outboundAuth;

  @Column(name = "outbound_proxy")
  private String outboundProxy;

  @Column(name = "rewrite_contact")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo rewriteContact;

  @Column(name = "rtp_ipv6")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo rtpIpv6;

  @Column(name = "rtp_symmetric")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo rtpSymmetric;

  @Column(name = "send_diversion")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo sendDiversion;

  @Column(name = "send_pai")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo sendPai;

  @Column(name = "send_rpid")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo sendRpid;

  @Column(name = "timers_min_se")
  private Integer timersMinSe;

  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_timer_values")
  private PjsipTimer timers;

  @Column(name = "timers_sess_expires")
  private Integer timersSessExpires;

  @Column(name = "callerid")
  private String callerId;

  @Column(name = "callerid_privacy")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_cid_privacy_values")
  private CidPrivacy calleridPrivacy;

  @Column(name = "callerid_tag")
  private String calleridTag;

  @Column(name = "`100rel`")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_100rel_values")
  private PjSip100Rel rel;

  @Column(name = "aggregate_mwi")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo aggregateMwi;

  @Column(name = "trust_id_inbound")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo trustIdInbound;

  @Column(name = "trust_id_outbound")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo trustIdOutbound;

  @Column(name = "use_ptime")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo usePtime;

  @Column(name = "use_avpf")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo useAvpf;

  @Column(name = "media_encryption")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_media_encryption_values")
  private MediaEncryption mediaEncryption;

  @Column(name = "inband_progress")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo inbandProgress;

  @Column(name = "call_group")
  private String callGroup;

  @Column(name = "pickup_group")
  private String pickupGroup;

  @Column(name = "named_call_group")
  private String namedCallGroup;

  @Column(name = "named_pickup_group")
  private String namedPickupGroup;

  @Column(name = "device_state_busy_at")
  private Integer deviceStateBusyAt;

  @Column(name = "fax_detect")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo faxDetect;

  @Column(name = "t38_udptl")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo t38Udptl;

  @Column(name = "t38_udptl_ec")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_t38udptl_ec_values")
  private T38UdpTl t38UdptlEc;

  @Column(name = "t38_udptl_maxdatagram")
  private Integer t38UdptlMaxdatagram;

  @Column(name = "t38_udptl_nat")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo t38UdptlNat;

  @Column(name = "t38_udptl_ipv6")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo t38UdptlIpv6;

  @Column(name = "tone_zone")
  private String toneZone;

  private String language;

  @Column(name = "one_touch_recording")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo oneTouchRecording;

  @Column(name = "record_on_feature")
  private String recordOnFeature;

  @Column(name = "record_off_feature")
  private String recordOffFeature;

  @Column(name = "rtp_engine")
  private String rtpEngine;

  @Column(name = "allow_transfer")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo allowTransfer;

  @Column(name = "allow_subscribe")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo allowSubscribe;

  @Column(name = "sdp_owner")
  private String sdpOwner;

  @Column(name = "sdp_session")
  private String sdpSession;

  @Column(name = "tos_audio")
  private String tosAudio;

  @Column(name = "tos_video")
  private String tosVideo;

  @Column(name = "sub_min_expiry")
  private Integer subMinExpiry;

  @Column(name = "from_domain")
  private String fromDomain;

  @Column(name = "from_user")
  private String fromUser;

  @Column(name = "mwi_from_user")
  private String mwiFromUser;

  @Column(name = "dtls_verify")
  private String dtlsVerify;

  @Column(name = "dtls_rekey")
  private String dtlsRekey;

  @Column(name = "dtls_cert_file")
  private String dtlsCertFile;

  @Column(name = "dtls_private_key")
  private String dtlsPrivateKey;

  @Column(name = "dtls_cipher")
  private String dtlsCipher;

  @Column(name = "dtls_ca_file")
  private String dtlsCaFile;

  @Column(name = "dtls_ca_path")
  private String dtlsCaPath;

  @Column(name = "dtls_setup")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_dtls_setup_values")
  private DtlsSetup dtlsSetup;

  @Column(name = "srtp_tag_32")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo srtpTag32;

  @Column(name = "media_address")
  private String mediaAddress;

  @Column(name = "redirect_method")
  @Enumerated(EnumType.STRING)
  @Type(type = "pjsip_redirect_method_values")
  private RedirectMethod redirectMethod;

  @Column(name = "set_var")
  private String setVar;

  @Column(name = "cos_audio")
  private Integer cosAudio;

  @Column(name = "cos_video")
  private Integer cosVideo;

  @Column(name = "message_context")
  private String messageContext;

  @Column(name = "force_avp")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo forceAvp;

  @Column(name = "media_use_received_transport")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo mediaUseReceivedTransport;

  @Column(name = "accountcode")
  private String accountCode;

  @Column(name = "user_eq_phone")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo userEqPhone;

  @Column(name = "moh_passthrough")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo mohPassThrough;

  @Column(name = "media_encryption_optimistic")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo mediaEncryptionOptimistic;

  @Column(name = "rpid_immediate")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo rpidImmediate;

  @Column(name = "g726_non_standard")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo g726NonStandard;

  @Column(name = "rtp_keepalive")
  private Integer rtpKeepAlive;

  @Column(name = "rtp_timeout")
  private Integer rtpTimeout;

  @Column(name = "rtp_timeout_hold")
  private Integer rtpTimeoutHold;

  @Column(name = "bind_rtp_to_media_address")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo bindRtpToMediaAddress;

  @Column(name = "voicemail_extension")
  private String voicemailExtension;

  @Column(name = "mwi_subscribe_replaces_unsolicited")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool mwiSubscribeReplacesUnsolicited;

  private String deny;

  private String permit;

  private String acl;

  @Column(name = "contact_deny")
  private String contactDeny;

  @Column(name = "contact_permit")
  private String contactPermit;

  @Column(name = "contact_acl")
  private String contactAcl;

  @Column(name = "subscribe_context")
  private String subscribeContext;

  @Column(name = "fax_detect_timeout")
  private Integer faxDetectTimeout;

  @Column(name = "contact_user")
  private String contactUser;

  @Column(name = "preferred_codec_only")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo preferredCodecOnly;

  @Column(name = "asymmetric_rtp_codec")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo asymmetricRtpCodec;

  @Column(name = "rtcp_mux")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo rtcpMux;

  @Column(name = "allow_overlap")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo allowOverlap;

  @Column(name = "refer_blind_progress")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo referBlindProgress;

  @Column(name = "notify_early_inuse_ringing")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo notifyEarlyInUseRinging;

  @Column(name = "max_audio_streams")
  private Integer maxAudioStreams;

  @Column(name = "max_video_streams")
  private Integer maxVideoStreams;

  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo webrtc;

  @Column(name = "dtls_fingerprint")
  @Enumerated(EnumType.STRING)
  @Type(type = "sha_hash_values")
  private ShaHash dtlsFingerprint;

  @Column(name = "incoming_mwi_mailbox")
  private String incomingMwiMailbox;

  @Column(name = "bundle")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo bundle;

  @Column(name = "dtls_auto_generate_cert")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo dtlsAutoGenerateCert;

  @Column(name = "follow_early_media_fork")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo followEarlyMediaFork;

  @Column(name = "accept_multiple_sdp_answers")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo acceptMultipleSdpAnswers;

  @Column(name = "suppress_q850_reason_headers")
  @Enumerated(EnumType.STRING)
  @Type(type = "yesno_values")
  private YesNo suppressQ850ReasonHeaders;

  @Column(name = "trust_connected_line")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool trustConnectedLine;

  @Column(name = "send_connected_line")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool sendConnectedLine;

  @Column(name = "ignore_183_without_sdp")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool ignore183WithoutSdp;

  @Column(name = "codec_prefs_incoming_offer")
  private String codec_prefs_incoming_offer;

  @Column(name = "codec_prefs_outgoing_offer")
  private String codecPrefsOutgoingOffer;

  @Column(name = "codec_prefs_incoming_answer")
  private String codecPrefsIncomingAnswer;

  @Column(name = "codec_prefs_outgoing_answer")
  private String codecPrefsOutgoingAnswer;

  @Column(name = "stir_shaken")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool stirShaken;

  @Column(name = "send_history_info")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool sendHistoryInfo;

  @Column(name = "allow_unauthenticated_options")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool allowUnauthenticatedOptions;

  @Column(name = "t38_bind_udptl_to_media_address")
  @Enumerated(EnumType.STRING)
  @Type(type = "ast_bool_values")
  private AstBool t38BindUdptlToMediaAddress;

}
