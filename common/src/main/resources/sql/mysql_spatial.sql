DROP TABLE poi_milan;

CREATE TABLE poi_milan
(idno INTEGER, name CHARACTER(80), ptyp CHARACTER(50),
  lat FLOAT (16,8), lon FLOAT (16,8), g GEOMETRY);

-- insert data
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (1,'Duomo','05 - church',9.191837781,45.464282256);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (2,'Biblioteca Ambrosiana','04 - building', 9.185365986,45.463248669);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (3,'Borsa di Milano','04 - building', 9.183246694,45.465179773);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (4,'Castello Sforzesco','04 - building', 9.179472633,45.470286454);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (5,'Cimitero Monumentale','03 - green', 9.178310878,45.486850940);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (6,'Fiera Di Milano','04 - building', 9.153554429,45.477649198);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (7,'Galleria Vittorio Emanuele','04 - building', 9.189949093,45.465694902);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (8,'Giardini pubblici','03 - green', 9.199755024,45.474053542);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (9,'La Scala','04 - art', 9.189474165,45.467526176);
INSERT INTO poi_milan (idno,name,ptyp,lat,lon)
VALUES (10,'Meazza (San Siro) Stadio','06 - sport', 9.123891471,45.478185243);

UPDATE poi_milan
SET g = GeomFromText(
CONCAT('POINT(', CAST(lat AS CHARACTER(20)),
' ', CAST(lon AS CHARACTER(20)),')')
);