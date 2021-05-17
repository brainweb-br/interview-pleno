FROM postgres:9.4

ENV POSTGRES_PASSWORD=brainweb
ENV POSTGRES_USER=root
ENV POSTGRES_DB=postgres


EXPOSE 5432
COPY init_schema.sql /docker-entrypoint-initdb.d/
