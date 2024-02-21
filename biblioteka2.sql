/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.27-MariaDB : Database - biblioteka2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`biblioteka2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `biblioteka2`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `AdministratorID` int(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(30) NOT NULL,
  `Prezime` varchar(30) NOT NULL,
  `KorisnickoIme` varchar(30) NOT NULL,
  `Lozinka` varchar(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `administrator` */

insert  into `administrator`(`AdministratorID`,`Ime`,`Prezime`,`KorisnickoIme`,`Lozinka`) values 
(1,'Maja','Grubor','maja','maja'),
(2,'Milica','Grubor','milica','milica'),
(3,'Davorka','Grubor','davorka','davorka'),
(4,'Danko','Grubor','danko','danko');

/*Table structure for table `autor` */

DROP TABLE IF EXISTS `autor`;

CREATE TABLE `autor` (
  `AutorID` int(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(30) DEFAULT NULL,
  `Prezime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`AutorID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `autor` */

insert  into `autor`(`AutorID`,`Ime`,`Prezime`) values 
(1,'Ivo','Andric'),
(2,'Mesa','Selimovic'),
(3,'Donato','Karizi'),
(4,'Vilijem','Sekspir');

/*Table structure for table `clan_biblioteke` */

DROP TABLE IF EXISTS `clan_biblioteke`;

CREATE TABLE `clan_biblioteke` (
  `ClanBibliotekeID` int(10) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(30) NOT NULL,
  `Prezime` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  PRIMARY KEY (`ClanBibliotekeID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `clan_biblioteke` */

insert  into `clan_biblioteke`(`ClanBibliotekeID`,`Ime`,`Prezime`,`Email`) values 
(30,'Dijana','Isidorovic','dijana16@gmail.com'),
(31,'Janko','Isidorovic','janko26@gmail.com'),
(32,'Relja','Isidorovic','relja@gmail.com'),
(33,'Nikola','Markovic','nikola@gmail.com'),
(34,'Tamara','Ivanovic','tamara20@gmail.com'),
(35,'Tiho','Parenta','tiho10@gmail.com'),
(36,'Milan','Parenta','milan@gmail.com'),
(37,'Daca','Parenta','danijela@gmail.com'),
(38,'Luka','Jovic','luka@gmail.com'),
(39,'Rade','Aksentijevic','rade@gmail.com'),
(40,'Tanja','Ivanovic','tanja@gmail.com');

/*Table structure for table `knjiga` */

DROP TABLE IF EXISTS `knjiga`;

CREATE TABLE `knjiga` (
  `ISBN` bigint(13) NOT NULL,
  `Naslov` varchar(30) DEFAULT NULL,
  `AutorID` int(10) DEFAULT NULL,
  `GodinaIzdanja` int(4) DEFAULT NULL,
  `Zanr` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `knjiga_ibfk_1` (`AutorID`),
  CONSTRAINT `knjiga_ibfk_1` FOREIGN KEY (`AutorID`) REFERENCES `autor` (`AutorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `knjiga` */

insert  into `knjiga`(`ISBN`,`Naslov`,`AutorID`,`GodinaIzdanja`,`Zanr`) values 
(168562238298,'Saptac',3,2014,'Misterija'),
(212397223997,'Otelo',4,2008,'Tragedija'),
(322178108125,'Prokleta Avlija',1,2001,'Istorijski roman'),
(329927325863,'Kralj Lir',4,2001,'Tragedija'),
(406080394346,'Saptac',3,2015,'Misterija'),
(445170050514,'Romeo i Julija',4,1996,'Drama'),
(518181915889,'Ostrvo',2,2009,'Roman'),
(668292533052,'Skrivanje',3,2019,'Triler'),
(734975536943,'Krug',2,2016,'Istorijski roman'),
(816541728063,'Hamlet',4,1996,'Drama'),
(1234567891234,'Dervis i smrt',2,2016,'Istorijski roman'),
(1234567898765,'Vatra',3,2009,'Drama'),
(1547628946352,'Vladar iz senke',3,2015,'Misterija'),
(2142367896543,'Decak od stakla',3,2015,'Misterija'),
(4568345678976,'Tvrdjava',2,2008,'Istorijski roman');

/*Table structure for table `pozajmica` */

DROP TABLE IF EXISTS `pozajmica`;

CREATE TABLE `pozajmica` (
  `PozajmicaID` int(10) NOT NULL AUTO_INCREMENT,
  `DatumPozajmice` date DEFAULT NULL,
  `DatumVracanja` date DEFAULT NULL,
  `AdministratorID` int(10) DEFAULT NULL,
  `ClanBibliotekeID` int(10) DEFAULT NULL,
  PRIMARY KEY (`PozajmicaID`),
  KEY `AdministratorID` (`AdministratorID`),
  KEY `ClanBibliotekeID` (`ClanBibliotekeID`),
  CONSTRAINT `pozajmica_ibfk_1` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`) ON UPDATE NO ACTION,
  CONSTRAINT `pozajmica_ibfk_2` FOREIGN KEY (`ClanBibliotekeID`) REFERENCES `clan_biblioteke` (`ClanBibliotekeID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `pozajmica` */

insert  into `pozajmica`(`PozajmicaID`,`DatumPozajmice`,`DatumVracanja`,`AdministratorID`,`ClanBibliotekeID`) values 
(34,'2024-02-19','2024-03-04',1,39),
(39,'2024-02-19','2024-03-04',1,32),
(41,'2024-02-20','2024-03-05',1,34);

/*Table structure for table `primerak` */

DROP TABLE IF EXISTS `primerak`;

CREATE TABLE `primerak` (
  `PrimerakID` int(10) NOT NULL AUTO_INCREMENT,
  `PozajmicaID` int(11) DEFAULT NULL,
  `ISBN` bigint(13) DEFAULT NULL,
  `Izdavac` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`PrimerakID`),
  KEY `PozajmicaID` (`PozajmicaID`),
  KEY `ISBN` (`ISBN`),
  CONSTRAINT `primerak_ibfk_1` FOREIGN KEY (`PozajmicaID`) REFERENCES `pozajmica` (`PozajmicaID`),
  CONSTRAINT `primerak_ibfk_2` FOREIGN KEY (`ISBN`) REFERENCES `knjiga` (`ISBN`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `primerak` */

insert  into `primerak`(`PrimerakID`,`PozajmicaID`,`ISBN`,`Izdavac`) values 
(8,NULL,816541728063,'Dereta'),
(9,34,4568345678976,'Vulkan'),
(10,NULL,2142367896543,'Laguna'),
(11,NULL,2142367896543,'Vulkan'),
(12,NULL,322178108125,'Vulkan'),
(13,39,518181915889,'Dereta'),
(14,NULL,322178108125,'Laguna'),
(15,41,322178108125,'Dereta'),
(16,NULL,734975536943,'Dereta'),
(17,NULL,734975536943,'Laguna'),
(18,NULL,168562238298,'Dereta'),
(19,NULL,406080394346,'Laguna'),
(20,41,212397223997,'Vulkan');

/*Table structure for table `recenzija` */

DROP TABLE IF EXISTS `recenzija`;

CREATE TABLE `recenzija` (
  `ISBN` bigint(13) NOT NULL,
  `ClanBibliotekeID` int(10) NOT NULL,
  `TekstRecenzije` varchar(200) NOT NULL,
  `Ocena` int(10) NOT NULL,
  PRIMARY KEY (`ISBN`,`ClanBibliotekeID`),
  KEY `ClanBibliotekeID` (`ClanBibliotekeID`),
  CONSTRAINT `recenzija_ibfk_2` FOREIGN KEY (`ClanBibliotekeID`) REFERENCES `clan_biblioteke` (`ClanBibliotekeID`),
  CONSTRAINT `recenzija_ibfk_3` FOREIGN KEY (`ISBN`) REFERENCES `knjiga` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `recenzija` */

insert  into `recenzija`(`ISBN`,`ClanBibliotekeID`,`TekstRecenzije`,`Ocena`) values 
(212397223997,33,'Sta reco',7),
(322178108125,30,'Svidja mi se knjiga.',10),
(322178108125,32,'Odlicna knjiga.',8),
(322178108125,33,'Odlicna knjiga',10),
(322178108125,35,'Odlicna knjiga',2),
(329927325863,38,'Volim Sekspira, ali moze to bolje.',6),
(518181915889,33,'Odlicno',7),
(734975536943,37,'Omiljena knjiga',10),
(1234567891234,30,'Svidja mi se knjiga',10),
(1234567891234,33,'Odlicna knjiga.',10),
(2142367896543,39,'Odlicna knjiga',8);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
