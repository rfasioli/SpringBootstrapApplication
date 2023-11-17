CREATE TABLE bootstrap.api_request (
  id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
  method VARCHAR(32) NOT NULL,
  path VARCHAR(255) NOT NULL,
  query VARCHAR(255),
  headers VARCHAR(1024),
  body CLOB(4096),
  CONSTRAINT api_request_pk PRIMARY KEY (id)
);
