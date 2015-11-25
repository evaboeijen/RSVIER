CREATE TABLE `adres` (
  `klant_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `straatnaam` VARCHAR(26) NULL DEFAULT NULL COMMENT '',
  `postcode` VARCHAR(6) NULL DEFAULT NULL COMMENT '',
  `toevoeging` VARCHAR(6) NULL DEFAULT NULL COMMENT '',
  `huisnummer` VARCHAR(6) NULL DEFAULT NULL COMMENT '',
  `woonplaats` VARCHAR(26) NULL DEFAULT NULL COMMENT '',
  INDEX `klant_id_idx` (`klant_id` ASC)  COMMENT '',
  CONSTRAINT `klant_id`
    FOREIGN KEY (`klant_id`)
    REFERENCES `opdracht1`.`klant` (`klant_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


ALTER TABLE `klant` 
DROP COLUMN `woonplaats`,
DROP COLUMN `huisnummer`,
DROP COLUMN `toevoeging`,
DROP COLUMN `postcode`,
DROP COLUMN `straatnaam`;