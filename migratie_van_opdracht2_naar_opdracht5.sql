-- voer dit script uit voor de databaseschema migratie van het 
-- databaseschema van opdracht 1&2
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