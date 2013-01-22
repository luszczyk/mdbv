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

INSERT INTO poi_milan (idno,name,ptyp,lat,lon, g)
VALUES (11,'Meazza (San Siro) Stadio','06 - sport', 9.123891471,45.478185243,
GeomFromText('POLYGON((1457389.49 6875261.48,1457389.63 6875264.87,1457391.04 6875268.54,1457393.62 6875271.5,1457438.46 6875402.37,1457534.46 6875598.88,1457542.22 6875597.95,1457546.72 6875600.58,1457554.22 6875613.88,1457556.17 6875618.11,1457556.64 6875625.4,1457552.45 6875633.77,1457653.26 6875812.13,1457683.66 6875848.12,1457798.08 6876032.66,1457931.31 6876199.45,1458046.31 6876316.42,1458094.93 6876388.06,1458148.67 6876560.37,1458258.71 6876920.33,1458287.71 6876977.49,1458341.76 6877003.06,1458396.04 6877015.02,1458593.94 6877024.4,1458685.21 6876981.76,1458756.86 6876911.81,1458801.21 6876842.72,1458812.3 6876783.85,1458832.37 6876747.29,1458865.21 6876734.96,1458931.33 6876737.34,1459076.34 6876612.19,1459199.95 6876648.99,1459414.44 6876809.57,1459473 6876832.11,1459527.48 6876839.94,1459584.2 6876837.41,1459626.56 6876809,1459662.67 6876760.39,1459718.99 6876725.25,1459821.52 6876669.4,1459869.66 6876643.4,1459892.29 6876623.2,1459878.04 6876600.33,1459846.56 6876583.24,1459735.35 6876546.17,1459614.11 6876488.73,1459384.9 6876173.99,1459337.06 6876141.41,1459318.29 6876129.8,1459305.97 6876109.8,1459197.68 6875807.4,1459251.53 6875616.47,1459289.55 6875355.59,1459133.88 6875341.1,1459040.11 6875332.36,1458774.62 6875307.65,1458400.93 6875334.02,1458295.21 6875331.43,1458210.4 6875317.81,1458100.68 6875280.04,1458016.79 6875240.73,1457973.81 6875225.74,1457903.71 6875213.59,1457899.42 6875221.47,1457872.81 6875216.07,1457877.32 6875207.71,1457858.37 6875201.85,1457827.69 6875207.49,1457578.17 6875247.71,1457410 6875256.37,1457408.56 6875254.65,1457404.3 6875252.06,1457399.03 6875251.58,1457394.47 6875253.34,1457391.62 6875256.04,1457390.62 6875257.68,1457389.49 6875261.48))')
);

