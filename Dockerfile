FROM centos:7
LABEL org.opencontainers.image.authors="adesh.pandey10@gmail.com"

WORKDIR /usr/src

RUN yum update -y && yum install -y wget epel-release unixODBC unixODBC-devel gcc postgresql-odbc postgresql-devel postgresql-client python3 python3-devel pip3 gettext sox
RUN pip3 install psycopg2 alembic

ENV ASTERISK_VERSION=18
RUN wget https://downloads.asterisk.org/pub/telephony/asterisk/asterisk-${ASTERISK_VERSION}-current.tar.gz
RUN tar xvf asterisk-${ASTERISK_VERSION}-current.tar.gz
RUN mkdir asterisk
RUN tar -xzf asterisk-${ASTERISK_VERSION}-current.tar.gz -C asterisk --strip-components=1

WORKDIR /usr/src/asterisk/contrib/scripts
RUN ./install_prereq install && ./get_mp3_source.sh
WORKDIR /usr/src/asterisk
RUN ./configure --with-jansson-bundled
RUN make menuselect.makeopts \
    && menuselect/menuselect --disable BUILD_NATIVE   \
    --enable res_agi                             \
    --enable res_ari                             \
    --enable res_ari_applications                \
    --enable res_ari_asterisk                    \
    --enable res_ari_bridges                     \
    --enable res_ari_channels                    \
    --enable res_ari_device_states               \
    --enable res_ari_endpoints                   \
    --enable res_ari_events                      \
    --enable res_ari_mailboxes                   \
    --enable res_ari_model                       \
    --enable res_ari_playbacks                   \
    --enable res_ari_recordings                  \
    --enable res_ari_sounds                      \
    --enable res_clialiases                      \
    --enable res_clioriginate                    \
    --enable res_config_curl                     \
    --enable res_config_odbc                     \
    --enable res_convert                         \
    --enable res_crypto                          \
    --enable res_curl                            \
    --enable res_fax                             \
    --enable res_format_attr_celt                \
    --enable res_format_attr_g729                \
    --enable res_format_attr_h263                \
    --enable res_format_attr_h264                \
    --enable res_format_attr_ilbc                \
    --enable res_format_attr_opus                \
    --enable res_format_attr_silk                \
    --enable res_format_attr_siren14             \
    --enable res_format_attr_siren7              \
    --enable res_format_attr_vp8                 \
    --enable res_http_media_cache                \
    --enable res_http_post                       \
    --enable res_http_websocket                  \
    --enable res_limit                           \
    --enable res_manager_devicestate             \
    --enable res_manager_presencestate           \
    --enable res_musiconhold                     \
    --enable res_mutestream                      \
    --enable res_odbc                            \
    --enable res_odbc_transaction                \
    --enable res_parking                         \
    --enable res_pjproject                       \
    --enable res_pjsip                           \
    --enable res_realtime                        \
    --enable res_resolver_unbound                \
    --enable res_rtp_asterisk                    \
    --enable res_rtp_multicast                   \
    --enable res_security_log                    \
    --enable res_speech                          \
    --enable res_srtp                            \
    --enable res_stasis                          \
    --enable res_stir_shaken                     \
    --enable res_stun_monitor                    \
    --enable res_timing_dahdi                    \
    --enable res_timing_timerfd                  \
    --enable res_xmpp                            \
    --enable res_ael_share                       \
    --enable res_audiosocket                     \
    --enable res_chan_stats                      \
    --enable res_config_pgsql                    \
    --enable res_endpoint_stats                  \
    --enable res_hep                             \
    --enable res_hep_pjsip                       \
    --enable res_hep_rtcp                        \
    --enable res_phoneprov                       \
    --enable res_prometheus                      \
    --enable res_smdi                            \
    --enable res_snmp                            \
    --enable res_statsd                          \
    --enable res_timing_kqueue                   \
    --enable res_timing_pthread                  \
    --enable res_tonedetect                      \
    --enable res_monitor     menuselect.makeopts  \
    && make -j$(nproc) 1> /dev/null     \
    && make -j$(nproc) install 1> /dev/null    \
    && make -j$(nproc) samples 1> /dev/null     \
    && make dist-clean     \
    && sed -i -e 's/# MAXFILES=/MAXFILES=/' /usr/sbin/safe_asterisk     \
    && useradd -m asterisk -s /sbin/nologin \
    && chown -R asterisk:asterisk /var/run/asterisk \
    /etc/asterisk/ \
    /var/lib/asterisk \
    /var/log/asterisk \
    /var/spool/asterisk \
    && mv /usr/src/asterisk/contrib/ast-db-manage /ast-db-manage && rm -rf /usr/src/*
# And run asterisk in the foreground.
COPY ./configs/pjsip.conf /etc/asterisk/pjsip.conf
COPY ./configs/extensions.conf /etc/asterisk/extensions.conf
COPY ./templates /templates
COPY ./templates/odbc.tpl /etc/odbc.ini
COPY ./sngrep.repo /etc/yum.repos.d/sngrep.repo
RUN rpm --import http://packages.irontec.com/public.key
RUN yum update -y && yum install -y sngrep && yum clean all
COPY ./docker-entrypoint.sh /docker-entrypoint.sh
RUN chown asterisk /etc/odbc.ini /ast-db-manage
USER asterisk
ENTRYPOINT [ "/docker-entrypoint.sh" ]
CMD ["/bin/sh", "-c",  " /usr/sbin/asterisk -f "]