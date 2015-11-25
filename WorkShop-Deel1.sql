-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2015 at 12:53 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `opdracht1`
--

-- --------------------------------------------------------
--
-- Table structure for table `bestelling`
--

CREATE TABLE IF NOT EXISTS `bestelling` (
  `bestelling_id` int(11) NOT NULL,
  `klant_id` int(11) NOT NULL,
  `artikel1_id` int(11) NOT NULL,
  `artikel1_naam` varchar(20) NOT NULL,
  `artikel1_aantal` int(11) NOT NULL,
  `artikel1_prijs` double NOT NULL,
  `artikel2_id` int(11),
  `artikel2_naam` varchar(20),
  `artikel2_aantal` int(11),
  `artikel2_prijs` double,
  `artikel3_id` int(11),
  `artikel3_naam` varchar(20),
  `artikel3_aantal` int(11),
  `artikel3_prijs` double
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bestelling`
--

INSERT INTO `bestelling` (`bestelling_id`, `klant_id`, `artikel1_id`, `artikel1_naam`, `artikel1_aantal`, `artikel1_prijs`, `artikel2_id`, `artikel2_naam`, `artikel2_aantal`, `artikel2_prijs`, `artikel3_id`, `artikel3_naam`, `artikel3_aantal`, `artikel3_prijs`) VALUES
(1, 0, 0, '', 0, 0, 0, '', 0, 0, 0, '', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `klant`
--

CREATE TABLE IF NOT EXISTS `klant` (
  `klant_id` int(11) NOT NULL,
  `voornaam` varchar(50) NOT NULL,
  `achternaam` varchar(51) NOT NULL,
  `tussenvoegsel` varchar(10) NOT NULL,
  `email` varchar(320) NOT NULL,
  `straatnaam` varchar(26),
  `postcode` varchar(6),
  `toevoeging` varchar(6),
  `huisnummer` varchar(6),
  `woonplaats` varchar(26),
  CONSTRAINT klant_constraint UNIQUE (voornaam,email,achternaam)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `klant`
--

INSERT INTO `klant` (`klant_id`, `voornaam`, `achternaam`, `tussenvoegsel`, `email`, `straatnaam`, `postcode`, `toevoeging`, `huisnummer`, `woonplaats`) VALUES
(1, 'Eva', 'Boeijen', '', 'eva.e.boeijen@gmail.com', 'Atjehstraat ', '2315CS', '', '87', 'Leiden');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bestelling`
--
ALTER TABLE `bestelling`
  ADD PRIMARY KEY (`bestelling_id`),
  ADD UNIQUE KEY `bestelling_id` (`bestelling_id`),
  ADD KEY `klant_id_idx` (`klant_id`);

--
-- Indexes for table `klant`
--
ALTER TABLE `klant`
  ADD PRIMARY KEY (`klant_id`),
  ADD UNIQUE KEY `klant_id_UNIQUE` (`klant_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bestelling`
--
ALTER TABLE `bestelling`
  MODIFY `bestelling_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `klant`
--
ALTER TABLE `klant`
  MODIFY `klant_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


-- het volgende stuk migreert het 
-- databaseschema van opdracht 1&2 (dat wat hierboven staat)
-- naar het databaseschema van opdracht 5 (many to many database schema)

set foreign_key_checks=0;  
 
CREATE TABLE IF NOT EXISTS `adres` (
  `adres_id` INT(11) NOT NULL ,
  `straatnaam` VARCHAR(26) NULL DEFAULT NULL,
  `postcode` VARCHAR(6) NULL DEFAULT NULL,
  `toevoeging` VARCHAR(6) NULL DEFAULT NULL,
  `huisnummer` VARCHAR(6) NULL DEFAULT NULL,
  `woonplaats` VARCHAR(26) NULL DEFAULT NULL,
	INDEX `adres_id_idx` (`adres_id` ASC),
	CONSTRAINT `adres_id`
		FOREIGN KEY (`adres_id`) REFERENCES `opdracht1`.`klant_adres` (`adres_id`)	 
  );
    
CREATE TABLE IF NOT EXISTS klant_adres (
         `klant_id`   INT(11) NOT NULL,
         `adres_id`  INT(11) NOT NULL,
         PRIMARY KEY (`klant_id`, `adres_id`),
         FOREIGN KEY (`klant_id`) REFERENCES `opdracht1`.`klant` (`klant_id`),
         FOREIGN KEY (`adres_id`) REFERENCES `opdracht1`.`adres` (`adres_id`)
       );
	
ALTER TABLE `klant` 
DROP COLUMN `woonplaats`,
DROP COLUMN `huisnummer`,
DROP COLUMN `toevoeging`,
DROP COLUMN `postcode`,
DROP COLUMN `straatnaam`;

CREATE TABLE IF NOT EXISTS `artikel` (
  `artikel_id` INT(11) NOT NULL ,
  `artikel_naam` VARCHAR(20) NOT NULL ,
  `artikel_prijs` DOUBLE NOT NULL,
	INDEX `artikel_id_idx` (`artikel_id` ASC) ,
	CONSTRAINT `artikel_id`
		FOREIGN KEY (`artikel_id`) REFERENCES `opdracht1`.`bestelling_artikel` (`artikel_id`)	 
  );
    
CREATE TABLE IF NOT EXISTS bestelling_artikel (
         `bestelling_id` INT(11) NOT NULL,
         `artikel_id` INT(11) NOT NULL,
         `artikel_aantal` INT(11) NOT NULL,
         PRIMARY KEY (`bestelling_id`, `artikel_id`),
         FOREIGN KEY (`bestelling_id`) REFERENCES `opdracht1`.`bestelling` (`bestelling_id`),
         FOREIGN KEY (`artikel_id`) REFERENCES `opdracht1`.`artikel` (`artikel_id`)
       );
	
ALTER TABLE `bestelling` 
DROP COLUMN `artikel1_id`,
DROP COLUMN `artikel1_naam` ,
DROP COLUMN `artikel1_aantal` ,
DROP COLUMN `artikel1_prijs`,
DROP COLUMN `artikel2_id` ,
DROP COLUMN `artikel2_naam` ,
DROP COLUMN `artikel2_aantal`, 
DROP COLUMN `artikel2_prijs`, 
DROP COLUMN `artikel3_id` ,
DROP COLUMN `artikel3_naam` ,
DROP COLUMN `artikel3_aantal`, 
DROP COLUMN `artikel3_prijs`; 
