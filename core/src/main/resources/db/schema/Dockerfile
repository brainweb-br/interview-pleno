FROM postgres
env  POSTGRES_PASSWORD=brainweb
EXPOSE 5432
COPY init_schema.sql /docker-entrypoint-initdb.d/
