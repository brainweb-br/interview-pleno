CREATE USER gubee WITH password 'gubee';

CREATE SCHEMA interview_service;
ALTER USER gubee SET search_path = 'interview_service, public';

GRANT USAGE, CREATE ON SCHEMA interview_service TO gubee;
GRANT ALL ON ALL TABLES IN SCHEMA interview_service TO gubee;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA interview_service TO gubee;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA interview_service TO gubee;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA interview_service TO gubee;

SET SCHEMA 'interview_service';
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
GRANT EXECUTE ON FUNCTION uuid_generate_v4() TO gubee;
