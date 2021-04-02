FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=admin

COPY initial.sql /docker-entrypoint-initdb.d/.
