FROM mysql:5.7.43

LABEL maintainer="ivangfr@yahoo.com.br"

COPY mysql.cnf /etc/mysql/conf.d/
COPY researchdb.sql /docker-entrypoint-initdb.d/
