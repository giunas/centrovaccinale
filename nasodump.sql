-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: sql11.freemysqlhosting.net    Database: sql11420508
-- ------------------------------------------------------
-- Server version	5.5.62-0ubuntu0.14.04.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `email` varchar(45) NOT NULL,
  `password` char(40) NOT NULL,
  `tipo` tinyint(4) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('email@email.com','6279886fde090b3038f267098bcca771a6efa946',0),('femail@email.com','3667a4fc9f9f56ad0161c2f15eb0b338a87afe9a',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prenotazione`
--

DROP TABLE IF EXISTS `prenotazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prenotazione` (
  `cod_pren` char(40) NOT NULL,
  `data` date NOT NULL,
  `ora` time NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `specifica` varchar(45) NOT NULL,
  `patologia` varchar(45) NOT NULL,
  `cf` char(16) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `comune` varchar(45) NOT NULL,
  `indirizzo` varchar(45) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `stato` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cod_pren`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prenotazione`
--

LOCK TABLES `prenotazione` WRITE;
/*!40000 ALTER TABLE `prenotazione` DISABLE KEYS */;
INSERT INTO `prenotazione` VALUES ('66ea0c8122c115f4bb7240365fcd748a3a65375a','2021-06-21','22:00:00','Soggetto vulnerabile per Patologia','Malattie autoimmuni-immunodeficienze','COLITE ULCEROSA','NSAGPP97A21G273P','Giuseppe','Naso','Palermo','Via San Filippo, 36','3917704523',0),('71b2357823e4ad8d75d9df34ce1e9a97a4be9841','2021-06-30','09:00:00','Studente area sanitaria / Operatore sanitario','Personale sanitario','Nessuna scelta','VRDNDR73A12G273T','Andrea','Verdi','Palermo','Via Ernesto Basile, 84','3401122333',0);
/*!40000 ALTER TABLE `prenotazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaccinazione`
--

DROP TABLE IF EXISTS `vaccinazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vaccinazione` (
  `id_vacc` int(11) NOT NULL AUTO_INCREMENT,
  `data_eff` date NOT NULL,
  `ora_eff` time NOT NULL,
  `vaccino` varchar(45) DEFAULT NULL,
  `motivo` varchar(500) DEFAULT NULL,
  `cod_pren_fk` char(40) NOT NULL,
  `email_medico_fk` varchar(45) NOT NULL,
  PRIMARY KEY (`id_vacc`),
  KEY `cod_pren_fk_idx` (`cod_pren_fk`),
  KEY `email_medico_fk_idx` (`email_medico_fk`),
  CONSTRAINT `cod_pren_fk` FOREIGN KEY (`cod_pren_fk`) REFERENCES `prenotazione` (`cod_pren`),
  CONSTRAINT `email_medico_fk` FOREIGN KEY (`email_medico_fk`) REFERENCES `account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaccinazione`
--

LOCK TABLES `vaccinazione` WRITE;
/*!40000 ALTER TABLE `vaccinazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `vaccinazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-21 20:10:01
