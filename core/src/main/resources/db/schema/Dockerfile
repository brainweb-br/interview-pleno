FROM postgres

ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_USER=root
ENV POSTGRES_DB=postgres

EXPOSE 5432
COPY init_schema.sql /docker-entrypoint-initdb.d/