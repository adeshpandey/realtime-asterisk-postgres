#!/bin/bash

envsubst <  /templates/pjsip.tpl > /etc/asterisk/pjsip.conf;
envsubst <  /templates/odbc.tpl > /etc/odbc.ini;
envsubst <  /templates/res_odbc.tpl > /etc/asterisk/res_odbc.conf;
envsubst <  /templates/alembic.tpl > /ast-db-manage/config.ini;

cd /ast-db-manage && alembic -c config.ini upgrade head
echo "launch asterisk in foreground[passed in the cmd]"
exec "$@"
