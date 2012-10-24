-- Table: md

DROP TABLE md;

CREATE TABLE md
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  data oid NOT NULL,
  createdate date NOT NULL,
  ext character varying(20) NOT NULL,
  CONSTRAINT md_pkey PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);

-- Table: image

DROP TABLE image;

CREATE TABLE image
(
  name character varying(255) NOT NULL,
  data oid NOT NULL,
  day date
)
WITH (
  OIDS=FALSE
);