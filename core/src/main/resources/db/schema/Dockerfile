FROM postgres

EXPOSE 5432
COPY init_schema.sql /docker-entrypoint-initdb.d/
docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres