CREATE USER 'tester'@'localhost' IDENTIFIED BY 'test';
GRANT ALL PRIVILEGES ON * . * TO 'tester'@'localhost';

DROP DATABASE IF EXISTS `postcodeApi`;

CREATE DATABASE `postcodeApi`;

DROP TABLE IF EXISTS `postcodeApi`.`postcodeApi` ;

CREATE TABLE IF NOT EXISTS `postcodeApi`.`postcodeApi` (
  `id` INT,
  `text` VARCHAR(45) NULL,
  `number` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

USE `postcodeApi`;
INSERT INTO `postcodeApi`.`postcodeApi` (`id`, `text`, `number`) VALUES (1, 'some text', 105);
INSERT INTO `postcodeApi`.`postcodeApi` (`id`, `text`, `number`) VALUES (2, 'some text 2', 106);
INSERT INTO `postcodeApi`.`postcodeApi` (`id`, `text`, `number`) VALUES (3, 'someText3', 125);
INSERT INTO `postcodeApi`.`postcodeApi` (`id`, `text`, `number`) VALUES (4, 'some_text4', 2);
INSERT INTO `postcodeApi`.`postcodeApi` (`id`, `text`, `number`) VALUES (5, 'some-text5', -5);

COMMIT;
