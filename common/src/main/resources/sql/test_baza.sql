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

DROP TABLE person;

CREATE TABLE person(

person_id serial NOT NULL,
first_name character varying(255) NOT NULL,
last_name character varying(255) NOT NULL,
avatar oid NOT NULL,
best_movie oid NOT NULL,
anthem oid NOT NULL,
place_birth geometry,
CONSTRAINT pk_person PRIMARY KEY (person_id)
);

ALTER TABLE person OWNER TO mdbv;

