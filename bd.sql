CREATE DATABASE  IF NOT EXISTS `viajes` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `viajes`;
-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: viajes
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `destino`
--

DROP TABLE IF EXISTS `destino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destino` (
  `idDestino` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Pais` varchar(45) NOT NULL,
  `Precio` int NOT NULL,
  `url` varchar(225) NOT NULL,
  `grupos_idGrupos` int NOT NULL,
  `zonas_idZona` int NOT NULL,
  `tipoViaje_idTipoViaje` int NOT NULL,
  PRIMARY KEY (`idDestino`),
  KEY `fk_destino_grupos1_idx` (`grupos_idGrupos`),
  KEY `fk_destino_zonas1_idx` (`zonas_idZona`),
  KEY `fk_destino_tipoViaje1_idx` (`tipoViaje_idTipoViaje`),
  CONSTRAINT `fk_destino_grupos1` FOREIGN KEY (`grupos_idGrupos`) REFERENCES `grupos` (`idGrupos`),
  CONSTRAINT `fk_destino_tipoViaje1` FOREIGN KEY (`tipoViaje_idTipoViaje`) REFERENCES `tipoViaje` (`idTipoViaje`),
  CONSTRAINT `fk_destino_zonas1` FOREIGN KEY (`zonas_idZona`) REFERENCES `zonas` (`idZona`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destino`
--

LOCK TABLES `destino` WRITE;
/*!40000 ALTER TABLE `destino` DISABLE KEYS */;
INSERT INTO `destino` VALUES (1,'Bali','Indonesia',600,'https://www.elattelier.com/wp-content/uploads/2016/05/Viajar_con_estilo_bali_el_attelier_1-1.jpg',2,2,2),(2,'Paris','Francia',150,'https://viajes.nationalgeographic.com.es/medio/2021/03/03/sena_4ffb342d_1254x836.jpg',2,5,3),(3,'DisneyLand Paris','Francia',200,'https://www.parquetematico.net/wp-content/uploads/2019/05/Castillo-de-Disneyland-Paris-a-primera-hora-sin-gente.jpg',3,6,1),(4,'Budapest','Hungría',250,'https://www.easyjet.com/ejcms/cache/medialibrary/Images/JSS/Destinations/Hero/Hungary_Budapest_3840x2160.jpg?mw=1920&hash=D400FA53D52E4B6092820B268023F5C4F846D023',4,5,6),(5,'Guiza','Egipto',850,'https://heymondo.es/blog/wp-content/uploads/2018/12/seguridad-egipto-1_opt.jpg',2,5,3),(6,'Londres','Inglaterra',340,'https://www.nyhabitat.com/sp/blog/wp-content/uploads/2013/01/Big-ben-Elizabeth-torre-londres.jpg',3,5,3),(7,'New York','Estados Unidos',788,'https://upload.wikimedia.org/wikipedia/commons/4/47/New_york_times_square-terabass.jpg',1,5,3);
/*!40000 ALTER TABLE `destino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `idGrupos` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `NumParticipantesMin` int NOT NULL,
  `NumParticipantesMax` int DEFAULT NULL,
  PRIMARY KEY (`idGrupos`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'Viaje en Solitario',1,1),(2,'Luna de Miel',2,2),(3,'Familiares',2,10),(4,'Viaje en Grupo',5,30),(5,'Viaje de Trabajo',1,10),(6,'Viaje Escolar',20,70),(7,'Tercera Edad',1,80);
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menus` (
  `IdMenu` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Tipo` enum('S','I') NOT NULL,
  `Estado` bit(1) NOT NULL DEFAULT b'1',
  `rolesUsuario_idRolUs` int NOT NULL,
  `IdMenu_Menu` int DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdMenu`),
  KEY `FK_Menu_Rol` (`rolesUsuario_idRolUs`),
  KEY `Fk_Menu_menu` (`IdMenu_Menu`),
  CONSTRAINT `Fk_Menu_menu` FOREIGN KEY (`IdMenu_Menu`) REFERENCES `menus` (`IdMenu`),
  CONSTRAINT `FK_Menu_Rol` FOREIGN KEY (`rolesUsuario_idRolUs`) REFERENCES `rolesUsuario` (`idRolUs`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (0,'Inicio','S',_binary '',1,NULL,NULL),(1,'Usuario','S',_binary '',1,NULL,NULL),(2,'Nuevo','I',_binary '',1,1,'/privado/administrador/AltaUsuario.softwareII'),(3,'Modificar','I',_binary '',1,1,'/privado/administrador/ModificarUsuario.softwareII'),(4,'Eliminar','I',_binary '',1,1,'/privado/administrador/EliminarUsuario.softwareII'),(5,'Zonas','S',_binary '',1,NULL,NULL),(6,'Nuevo','I',_binary '',1,5,'/privado/administrador/AltaZona.softwareII'),(7,'Modificar','I',_binary '',1,5,'/privado/administrador/ModificarZona.softwareII'),(8,'Eliminar','I',_binary '',1,5,'/privado/administrador/EliminarZona.softwareII'),(9,'Viajes','S',_binary '',1,NULL,'/privado/administrador/ListarViajes.softwareII'),(13,'Inicio','S',_binary '',2,NULL,NULL),(14,'Buscar Viajes','S',_binary '',2,NULL,NULL),(15,'Buscar Destinos','I',_binary '',2,14,'/privado/comprador/Listar.softwareII'),(16,'Buscar por Grupos de Edad','I',_binary '',2,14,'/privado/comprador/ListarYFiltrarPorGrupos.softwareII'),(17,'Buscar por Tipos de Viaje','I',_binary '',2,14,'/privado/comprador/ListarYFiltrarPorTipos.softwareII'),(18,'Buscar por Zonas','I',_binary '',2,14,'/privado/comprador/ListarYFiltrarPorZonas.softwareII'),(22,'Tipo de Viaje','S',_binary '',1,NULL,NULL),(23,'Nuevo','I',_binary '',1,22,'/privado/administrador/AltaTipoViaje.softwareII'),(24,'Modificar','I',_binary '',1,22,'/privado/administrador/ModificarTipoViaje.softwareII'),(25,'Eliminar','I',_binary '',1,22,'/privado/administrador/EliminarTipoViaje.softwareII'),(26,'Grupos de Edades','S',_binary '',1,NULL,NULL),(27,'Nuevo','I',_binary '',1,26,'/privado/administrador/AltaGrupos.softwareII'),(28,'Modificar','I',_binary '',1,26,'/privado/administrador/ModificarGrupos.softwareII'),(29,'Eliminar','I',_binary '',1,26,'/privado/administrador/EliminarGrupos.softwareII'),(30,'Mi Área Privada','S',_binary '',2,NULL,NULL),(31,'Ver mis compras','I',_binary '',2,30,'/privado/comprador/ListarCompras.softwareII'),(32,'Viajes Pendientes','I',_binary '',2,30,'/privado/comprador/Pendientes.softwareII'),(33,'Editar mis Datos','I',_binary '',2,30,'/privado/comprador/EditarDatos.softwareII'),(34,'Destino','S',_binary '',1,NULL,NULL),(35,'Nuevo','I',_binary '',1,34,'/privado/administrador/AltaDestino.softwareII'),(36,'Modificar','I',_binary '',1,34,'/privado/administrador/ModificarDestino.softwareII'),(37,'Eliminar','I',_binary '',1,34,'/privado/administrador/EliminarDestino.softwareII');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `FechaNacimiento` datetime NOT NULL,
  `NumTarjeta` bigint NOT NULL,
  `DNI` varchar(45) NOT NULL,
  `Telefono` int NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Administradorr','Admin','2018-02-10 00:00:00',1223455678,'1234567F',123123123),(2,'Pepe,nsf','Diaz','2018-02-10 00:00:00',1231231123,'1111111D',111222333),(3,'Antía','Pérez-Gorostiaga González','1998-11-28 00:00:00',12344512,'12113213D',1212113),(4,'Ana','Perez','2002-07-03 00:00:00',12123333,'333212S',121234244),(7,'Elisa','Conde','2001-07-20 00:00:00',123213124,'12132132D',12323213),(9,'maria','gazapo','2001-06-15 00:00:00',1122211,'12121212F',334241),(11,'Raquel','Collantes','1999-02-06 00:00:00',998651,'12343212R',44342232),(12,'Iker','Martinez','2002-07-03 00:00:00',1234123412341234,'00998877G',121212122),(13,'Iker','Martinez','2002-07-03 00:00:00',1234123412341234,'00998877G',121212122);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolesUsuario`
--

DROP TABLE IF EXISTS `rolesUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rolesUsuario` (
  `idRolUs` int NOT NULL AUTO_INCREMENT,
  `TipoUsuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRolUs`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolesUsuario`
--

LOCK TABLES `rolesUsuario` WRITE;
/*!40000 ALTER TABLE `rolesUsuario` DISABLE KEYS */;
INSERT INTO `rolesUsuario` VALUES (1,'Administrador'),(2,'Comprador');
/*!40000 ALTER TABLE `rolesUsuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoViaje`
--

DROP TABLE IF EXISTS `tipoViaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoViaje` (
  `idTipoViaje` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Actividades` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoViaje`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoViaje`
--

LOCK TABLES `tipoViaje` WRITE;
/*!40000 ALTER TABLE `tipoViaje` DISABLE KEYS */;
INSERT INTO `tipoViaje` VALUES (1,'Aventura','Excursiones'),(2,'Relax','Spa'),(3,'Turismo','Visitas a museos y monumentos'),(4,'Turismo Gastronómico','Ir a comer a restaurantes'),(5,'Turismo Rural','Visitar el campo'),(6,'Turismo de Fiesta','Recorrer las mejores discotecas y fiestas');
/*!40000 ALTER TABLE `tipoViaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `User` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Correo` varchar(45) NOT NULL,
  `rolesUsuario_idRolUs` int NOT NULL,
  `persona_idPersona` int NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_usuario_rolesUsuario1_idx` (`rolesUsuario_idRolUs`),
  KEY `fk_usuario_persona1_idx` (`persona_idPersona`),
  CONSTRAINT `fk_usuario_persona1` FOREIGN KEY (`persona_idPersona`) REFERENCES `persona` (`idPersona`),
  CONSTRAINT `fk_usuario_rolesUsuario1` FOREIGN KEY (`rolesUsuario_idRolUs`) REFERENCES `rolesUsuario` (`idRolUs`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','admin@gmail.com',1,1),(2,'1','1','pepe@gmail.com',2,2),(3,'elisa','12','lily@mail.com',2,7),(4,'maria','12','mari@mmmy.com',2,9),(5,'raqueel','122','raquel@mmail.com',2,11),(6,'iikkkeerrr','12','ikkker@mm.com',2,13);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Venta`
--

DROP TABLE IF EXISTS `Venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Venta` (
  `idVenta` int NOT NULL AUTO_INCREMENT,
  `usuario_idUsuario` int NOT NULL,
  `Viajes_idViajes` int NOT NULL,
  `FechaVenta` datetime NOT NULL,
  PRIMARY KEY (`idVenta`,`usuario_idUsuario`,`Viajes_idViajes`),
  KEY `fk_usuario_has_Viajes_Viajes1_idx` (`Viajes_idViajes`),
  KEY `fk_usuario_has_Viajes_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_usuario_has_Viajes_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `fk_usuario_has_Viajes_Viajes1` FOREIGN KEY (`Viajes_idViajes`) REFERENCES `viaje` (`idViajes`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Venta`
--

LOCK TABLES `Venta` WRITE;
/*!40000 ALTER TABLE `Venta` DISABLE KEYS */;
INSERT INTO `Venta` VALUES (1,2,5,'2021-05-30 21:46:55'),(2,2,0,'2021-05-30 22:11:34'),(3,2,0,'2021-05-30 22:21:46'),(4,2,0,'2021-05-30 22:24:32'),(5,2,6,'2021-05-30 22:33:28'),(6,2,7,'2021-06-11 13:26:09');
/*!40000 ALTER TABLE `Venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaje`
--

DROP TABLE IF EXISTS `viaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viaje` (
  `idViajes` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `persona_idPersona` int NOT NULL,
  `FechaInicio` datetime DEFAULT NULL,
  `FechaFin` datetime DEFAULT NULL,
  `destino_idDestino` int NOT NULL,
  `vendido` int DEFAULT NULL,
  PRIMARY KEY (`idViajes`),
  KEY `fk_Viajes_persona1_idx` (`persona_idPersona`),
  KEY `fk_Viajes_destino1_idx` (`destino_idDestino`),
  CONSTRAINT `fk_Viajes_destino1` FOREIGN KEY (`destino_idDestino`) REFERENCES `destino` (`idDestino`),
  CONSTRAINT `fk_Viajes_persona1` FOREIGN KEY (`persona_idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES (0,'cgfhjkasf',2,'2021-05-31 00:00:00','2021-06-08 00:00:00',2,1),(1,'2354',2,'2021-05-31 00:00:00','2021-06-09 00:00:00',1,0),(3,'rwef',2,'2021-05-31 00:00:00','2021-06-16 00:00:00',2,1),(4,'m ac',2,'2021-05-31 00:00:00','2021-06-09 00:00:00',2,0),(5,'hvbjwq',2,'2021-05-31 00:00:00','2021-06-09 00:00:00',2,NULL),(6,'viaje',2,'2021-05-31 00:00:00','2021-06-16 00:00:00',2,1),(7,'bali',2,'2021-06-15 00:00:00','2021-06-22 00:00:00',1,1);
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zonas`
--

DROP TABLE IF EXISTS `zonas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zonas` (
  `idZona` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idZona`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zonas`
--

LOCK TABLES `zonas` WRITE;
/*!40000 ALTER TABLE `zonas` DISABLE KEYS */;
INSERT INTO `zonas` VALUES (1,'Costa','Disfruta de las mejores zonas costeras, podrá'),(2,'Playa','Disfruta de las mejores playas.'),(3,'Montaña','Explora la montaña y disfruta de la naturalez'),(4,'Campo','Disfruta de la tranquilidad del campo en las '),(5,'Ciudad','Conoce las maravillas de las ciudades.'),(6,'Parques de Atracciones','Diviertete en los mejores parques de atraccio');
/*!40000 ALTER TABLE `zonas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-11 15:45:30
