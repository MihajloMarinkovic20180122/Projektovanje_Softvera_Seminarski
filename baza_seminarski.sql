/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - projektovanje_softvera_seminarski
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`projektovanje_softvera_seminarski` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `projektovanje_softvera_seminarski`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `administratorId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `lozinka` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`administratorId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `administrator` */

insert  into `administrator`(`administratorId`,`email`,`lozinka`) values 
(1,'miha@gmail.com','miha123'),
(2,'kaca@gmail.com','kaca123'),
(3,'bole@gmail.com','bole123'),
(4,'sanja@gmail.com','sanja123');

/*Table structure for table `angazovanje` */

DROP TABLE IF EXISTS `angazovanje`;

CREATE TABLE `angazovanje` (
  `zaposleniId` int(11) NOT NULL,
  `projekatId` int(11) NOT NULL,
  `angazovanjeId` int(11) NOT NULL AUTO_INCREMENT,
  `pocetakAngazovanja` datetime DEFAULT NULL,
  `krajAngazovanja` datetime DEFAULT NULL,
  PRIMARY KEY (`zaposleniId`,`projekatId`,`angazovanjeId`),
  KEY `angazovanjeId` (`angazovanjeId`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `angazovanje` */

insert  into `angazovanje`(`zaposleniId`,`projekatId`,`angazovanjeId`,`pocetakAngazovanja`,`krajAngazovanja`) values 
(1,1,1,'2024-11-18 00:00:00',NULL),
(1,2,2,'2024-11-19 00:00:00',NULL),
(1,3,51,'2024-12-09 00:00:00','2024-12-14 00:00:00'),
(1,4,31,'2024-12-12 00:00:00','2024-12-14 00:00:00'),
(1,5,41,'2024-12-14 00:00:00','2024-12-14 00:00:00'),
(1,6,54,'2024-12-12 00:00:00',NULL),
(1,8,55,'2024-12-10 00:00:00','2024-12-14 00:00:00'),
(2,2,3,'2024-11-19 00:00:00',NULL),
(2,4,32,'2024-12-12 00:00:00','2024-12-14 00:00:00'),
(2,7,57,'2024-12-11 00:00:00','2024-12-14 00:00:00'),
(3,1,13,'2024-11-17 00:00:00',NULL),
(3,3,52,'2024-12-09 00:00:00','2024-12-14 00:00:00'),
(4,7,58,'2024-12-11 00:00:00','2024-12-14 00:00:00');

/*Table structure for table `organizacionacelina` */

DROP TABLE IF EXISTS `organizacionacelina`;

CREATE TABLE `organizacionacelina` (
  `organizacionaCelinaId` int(11) NOT NULL AUTO_INCREMENT,
  `nazivOrganizacioneCeline` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`organizacionaCelinaId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `organizacionacelina` */

insert  into `organizacionacelina`(`organizacionaCelinaId`,`nazivOrganizacioneCeline`) values 
(1,'Razvojni Tim'),
(2,'Proizvodni Tim'),
(3,'Infrastrukturni Tim');

/*Table structure for table `projekat` */

DROP TABLE IF EXISTS `projekat`;

CREATE TABLE `projekat` (
  `projekatId` int(11) NOT NULL AUTO_INCREMENT,
  `nazivProjekta` varchar(255) DEFAULT NULL,
  `pocetakRealizacije` datetime DEFAULT NULL,
  `rukovodilacId` int(11) DEFAULT NULL,
  `prioritet` varchar(255) DEFAULT NULL,
  `stanje` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`projekatId`),
  KEY `fk_projekat_zaposleniId` (`rukovodilacId`),
  CONSTRAINT `fk_projekat_rukovodilacId` FOREIGN KEY (`rukovodilacId`) REFERENCES `zaposleni` (`zaposleniId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `projekat` */

insert  into `projekat`(`projekatId`,`nazivProjekta`,`pocetakRealizacije`,`rukovodilacId`,`prioritet`,`stanje`) values 
(1,'Projekat 1','2024-11-13 00:00:00',1,'Visok','Aktivan'),
(2,'Projekat 2','2024-11-19 00:00:00',2,'Srednji','Aktivan'),
(3,'Projekat 3','2024-12-09 00:00:00',1,'Nizak','Realizovan'),
(4,'Projekat 4','2024-12-12 00:00:00',1,'Srednji','Otkazan'),
(5,'Projekat 5','2024-12-14 00:00:00',1,'Nizak','Realizovan'),
(6,'Projekat 6','2024-12-12 00:00:00',2,'Nizak','Aktivan'),
(7,'Projekat 7','2024-12-11 00:00:00',4,'Srednji','Realizovan'),
(8,'Projekat 8','2024-12-10 00:00:00',1,'Nizak','Realizovan');

/*Table structure for table `radnomesto` */

DROP TABLE IF EXISTS `radnomesto`;

CREATE TABLE `radnomesto` (
  `organizacionaCelinaId` int(11) NOT NULL,
  `radnoMestoId` int(11) NOT NULL AUTO_INCREMENT,
  `nazivRadnogMesta` varchar(255) DEFAULT NULL,
  `brojZaposlenih` int(11) DEFAULT NULL,
  PRIMARY KEY (`organizacionaCelinaId`,`radnoMestoId`),
  KEY `radnoMestoId` (`radnoMestoId`),
  CONSTRAINT `fk_radnoMesto_organizacionaCelinaId` FOREIGN KEY (`organizacionaCelinaId`) REFERENCES `organizacionacelina` (`organizacionaCelinaId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `radnomesto` */

insert  into `radnomesto`(`organizacionaCelinaId`,`radnoMestoId`,`nazivRadnogMesta`,`brojZaposlenih`) values 
(1,1,'Backend Developer',0),
(1,2,'Frontend Developer',1),
(1,3,'Software Architect',0),
(2,4,'Product Owner',1),
(2,5,'Business Analyst',0),
(3,6,'DB Administrator',1),
(3,7,'Network Engineer',1);

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `zaposleniId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `datumZaposlenja` datetime DEFAULT NULL,
  `organizacionaCelinaId` int(11) DEFAULT NULL,
  `radnoMestoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`zaposleniId`),
  KEY `fk_zaposleni_organizacionaCelinaId` (`organizacionaCelinaId`),
  KEY `fk_zaposleni_radnoMestoId` (`radnoMestoId`),
  CONSTRAINT `fk_zaposleni_organizacionaCelinaId` FOREIGN KEY (`organizacionaCelinaId`) REFERENCES `organizacionacelina` (`organizacionaCelinaId`),
  CONSTRAINT `fk_zaposleni_radnoMestoId` FOREIGN KEY (`radnoMestoId`) REFERENCES `radnomesto` (`radnoMestoId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`zaposleniId`,`ime`,`prezime`,`email`,`datumZaposlenja`,`organizacionaCelinaId`,`radnoMestoId`) values 
(1,'Mihajlo','Marinkovic','miha@gmail.com','2024-11-17 00:00:00',1,2),
(2,'Katarina','Jorovic','kaca@gmail.com','2024-11-19 00:00:00',2,4),
(3,'Boban','Marinkovic','bole@gmail.com','2024-11-19 00:00:00',3,6),
(4,'Sanja','Marinkovic','sanja@gmail.com','2024-12-01 00:00:00',3,7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
