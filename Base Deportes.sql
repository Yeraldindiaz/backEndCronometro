CREATE DATABASE IF NOT EXISTS CRONOSPORT; 
USE CRONOSPORT;

CREATE TABLE IF NOT EXISTS DEPORTES (
  ID_Deporte int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(150) DEFAULT NULL,
  descripcion varchar(150) DEFAULT NULL,
  PRIMARY KEY (ID_Deporte)
) ;


CREATE TABLE IF NOT EXISTS ENTRENADOR (
  `ID_Entrenador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `apellido` varchar(150) NOT NULL,
  `fechaNacimiento` varchar(150) NOT NULL,
  `cedula` int(11) NOT NULL,
  `ciudad` varchar(150) NOT NULL,
  `contrasena` varchar(150) NOT NULL,
  `deporteFK` int(11) ,
  PRIMARY KEY (`ID_Entrenador`),
  
  FOREIGN KEY (deporteFK) REFERENCES DEPORTES (ID_Deporte)
);


CREATE TABLE IF NOT EXISTS DEPORTISTA (
  `ID_Deportista` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `apellido` varchar(150) NOT NULL,
  `fechaNacimiento` varchar(150) NOT NULL,
  `cedula` int(11) NOT NULL,
  `ciudad` varchar(150) NOT NULL,
  `categoria` varchar(150) NOT NULL,
  `contrasena` varchar(150) NOT NULL,
  `deporteFK` int(11) ,
  `entrenadorFK` int(11) ,
  PRIMARY KEY (`ID_Deportista`),

  FOREIGN KEY (`deporteFK`) REFERENCES DEPORTES (`ID_Deporte`),
  FOREIGN KEY (`entrenadorFK`) REFERENCES ENTRENADOR (`ID_Entrenador`)
) ;


CREATE TABLE IF NOT EXISTS EVENTOS (
  `ID_Evento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `fecha` varchar(150) NOT NULL,
  `lugar` varchar(150) NOT NULL,
  `deport_FK` int(11),
  PRIMARY KEY (`ID_Evento`),
  
  FOREIGN KEY (`deport_FK`) REFERENCES DEPORTES (`ID_Deporte`)
);


CREATE TABLE IF NOT EXISTS TIEMPOCOM (
  `ID_tiempos` int(11) NOT NULL AUTO_INCREMENT,
  `prueba` varchar(150) NOT NULL,
  `distancia` int(11) NOT NULL,
  `tiempo` varchar(50) NOT NULL,
  `categoria` varchar(150) NOT NULL,
  `eventoFK` int(11),
  `deportistaFK` int(11),
  PRIMARY KEY (`ID_tiempos`),
  
  FOREIGN KEY (`eventoFK`) REFERENCES EVENTOS (`ID_Evento`),
  FOREIGN KEY (`deportistaFK`) REFERENCES DEPORTISTA (`ID_Deportista`)
);


CREATE TABLE IF NOT EXISTS CHEQUEOS (
  `ID_Chequeo` int(11) NOT NULL AUTO_INCREMENT,
  `prueba` varchar(150) NOT NULL,
  `distancia` int(11) NOT NULL,
  `tiempo` varchar(150) NOT NULL,
  `deportistaFK` int(11),
  PRIMARY KEY (`ID_Chequeo`),

  FOREIGN KEY (`deportistaFK`) REFERENCES DEPORTISTA (`ID_Deportista`)
);


CREATE TABLE IF NOT EXISTS ASISTENCIA (
  `ID_Asistencia` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` varchar(150) NOT NULL,
  `jornada` varchar(150) NOT NULL,
  `entrenadorFK` int(11),
  `deportistaFK` int(11),
  PRIMARY KEY (`ID_Asistencia`),
  
  
  FOREIGN KEY (`entrenadorFK`) REFERENCES ENTRENADOR (`ID_Entrenador`),
  FOREIGN KEY (`deportistaFK`) REFERENCES DEPORTISTA (`ID_Deportista`)
);


CREATE TABLE IF NOT EXISTS RUTINA (
  `ID_Rutina` int(11) NOT NULL AUTO_INCREMENT,
  `repeticiones` int(11) NOT NULL,
  `distancia` int(11) NOT NULL,
  `estilo` varchar(150) NOT NULL,
  `fecha` varchar(150) NOT NULL,
  `jornada` varchar(255) NOT NULL,
  `entrenadorFK` int(11),
  PRIMARY KEY (`ID_Rutina`),
  
  FOREIGN KEY (`entrenadorFK`) REFERENCES ENTRENADOR (`ID_Entrenador`)
);


CREATE TABLE IF NOT EXISTS TIEMPOENT (
  `ID_tiempos` int(11) NOT NULL AUTO_INCREMENT,
  `tiempo` varchar(50) NOT NULL,
  `tiempoexigencia` varchar(50) NOT NULL,
  `tiempodescanso` varchar(50) NOT NULL,
  `serie` int(11) NOT NULL,
  `deportistaFK` int(11),
  `rutinaFK` int(11),
  PRIMARY KEY (`ID_tiempos`),
  
  FOREIGN KEY (`deportistaFK`) REFERENCES DEPORTISTA (`ID_Deportista`),
  FOREIGN KEY (`rutinaFK`) REFERENCES RUTINA (`ID_Rutina`)
);


INSERT INTO DEPORTES (`nombre`, `descripcion`) VALUES ('Natacion Paralimpica', 'Natacion clasica adaptada');
INSERT INTO DEPORTES (`nombre`, `descripcion`) VALUES ('Natacion', 'Deporte Acuatico Carreras Diferentes estilos');
INSERT INTO DEPORTES (`nombre`, `descripcion`) VALUES ('Natacion con aletas', 'Deporte Acuatico Carreras Diferentes estilos Con aletas');
INSERT INTO DEPORTES (`nombre`, `descripcion`) VALUES ('Nado Sincronizado', 'Deporte Acuatico De danza en el agua');
INSERT INTO DEPORTES (`nombre`, `descripcion`) VALUES ('Polo Acuatico', 'Deporte Acuatico Con pelota por equipos');

INSERT INTO ENTRENADOR (`nombre`, `apellido`, `fechaNacimiento`, `cedula`, `ciudad`, `contrasena`, `deporteFK`) VALUES ('Stevens', 'Ruiz', '17/03/2018', '34567', 'Bogota', '34567', '1');
INSERT INTO ENTRENADOR (`nombre`, `apellido`, `fechaNacimiento`, `cedula`, `ciudad`, `contrasena`, `deporteFK`) VALUES ('Jimmy', 'Acosta', '17/03/2018', '34567', 'Bogota', '34567', '1');
INSERT INTO ENTRENADOR (`nombre`, `apellido`, `fechaNacimiento`, `cedula`, `ciudad`, `contrasena`, `deporteFK`) VALUES ('Diego', 'Mayorga', '17/03/2018', '34567', 'Bogota', '34567', '1');

INSERT INTO DEPORTISTA (`nombre`, `apellido`, `fechaNacimiento`, `cedula`, `ciudad`, `categoria`, `contrasena`, `deporteFK`, `entrenadorFK`) VALUES ('Mario', 'Bolaños Roa', '06/07/1993', '1018460477', 'Bogota', 'S8', '246', '1', '1');
INSERT INTO DEPORTISTA (`nombre`, `apellido`, `fechaNacimiento`, `cedula`, `ciudad`, `categoria`, `contrasena`, `deporteFK`, `entrenadorFK`) VALUES ('Jairo', 'Riascos', '17/03/2018', '1018', 'Bogota', 'S10', '246', '1', '2');
INSERT INTO DEPORTISTA (`nombre`, `apellido`, `fechaNacimiento`, `cedula`, `ciudad`, `categoria`, `contrasena`, `deporteFK`, `entrenadorFK`) VALUES ('Brayan', 'Hurbano', '17/03/2018', '60477', 'Santander', 'S11', '246', '1', '3');

INSERT INTO EVENTOS (`nombre`, `Tipo`, `fecha`, `lugar`, `deport_FK`) VALUES ('Juegos NAcionales', 'nacionales', '17/03/2018', 'Bogota', '1');
INSERT INTO EVENTOS (`nombre`, `Tipo`, `fecha`, `lugar`, `deport_FK`) VALUES ('Juegos SUramericanos', 'SUramericanos', '17/03/2018', 'Bogota', '1');
INSERT INTO EVENTOS (`nombre`, `Tipo`, `fecha`, `lugar`, `deport_FK`) VALUES ('Juegos Panamaericanos', 'Panamaericanos', '17/03/2018', 'Bogota', '1');
INSERT INTO EVENTOS (`nombre`, `Tipo`, `fecha`, `lugar`, `deport_FK`) VALUES ('Juegos Mundiales', 'Mundiales', '17/03/2018', 'Bogota', '1');
INSERT INTO EVENTOS (`nombre`, `Tipo`, `fecha`, `lugar`, `deport_FK`) VALUES ('Juegos Olimpicos', 'Olimpicos', '17/03/2018', 'Bogota', '1');

INSERT INTO TIEMPOCOM (`prueba`, `distancia`, `tiempo`, `categoria`, `eventoFK`, `deportistaFK`) VALUES ('Espalda', '100', '16:52:05', 'S8', '4', '1');
INSERT INTO TIEMPOCOM (`prueba`, `distancia`, `tiempo`, `categoria`, `eventoFK`, `deportistaFK`) VALUES ('lIBRE', '100', '6:02:50', 'S5', '2', '2');


INSERT INTO asistencia (`fecha`, `jornada`, `entrenadorFK`, `deportistaFK`) VALUES ('17/03/2018', 'Mañana', '1', '1');
INSERT INTO asistencia (`fecha`, `jornada`, `entrenadorFK`, `deportistaFK`) VALUES ('17/03/2018', 'Mañana', '2', '2');

INSERT INTO chequeos (`prueba`, `distancia`, `tiempo`, `deportistaFK`) VALUES ('Libre', '50', '35:76', '1');
INSERT INTO chequeos (`prueba`, `distancia`, `tiempo`, `deportistaFK`) VALUES ('Espalda', '50', '35:76', '2');

INSERT INTO rutina (`repeticiones`, `distancia`, `estilo`, `fecha`, `jornada`, `entrenadorFK`) VALUES ('4', '100', 'Libre', '17/03/2018', 'Mañana', '1');
INSERT INTO rutina (`repeticiones`, `distancia`, `estilo`, `fecha`, `jornada`, `entrenadorFK`) VALUES ('4', '100', 'Espalda', '17/03/2018', 'Mañana', '2');

INSERT INTO TIEMPOENT (`tiempo`, `tiempoexigencia`, `tiempodescanso`, `serie`, `deportistaFK`, `rutinaFK`) VALUES ('54:00', '50:00', '10:00', '1', '1', '1');
INSERT INTO TIEMPOENT (`tiempo`, `tiempoexigencia`, `tiempodescanso`, `serie`, `deportistaFK`, `rutinaFK`) VALUES ('54:00', '50:00', '10:00', '1', '2', '2');
