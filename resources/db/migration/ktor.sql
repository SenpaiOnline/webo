-- MySQL Script generated by MySQL Workbench
-- Пн 15 июл 2019 17:33:27
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ktor` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ktor` DEFAULT CHARACTER SET utf8 ;
USE `ktor` ;

-- -----------------------------------------------------
-- Table `ktor`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ktor`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `avatar` VARCHAR(255) NOT NULL DEFAULT 'default_avatar.png',
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ktor`.`code_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ktor`.`code_language` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ktor`.`paste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ktor`.`paste` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` TEXT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT now(),
  `expired_at` DATETIME NULL,
  `delete_after_number_of_views` INT NULL,
  `is_private` TINYINT(1) NOT NULL,
  `link` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL DEFAULT 'Untitled',
  `total_views` INT NOT NULL DEFAULT 0,
  `user_id` BIGINT NOT NULL,
  `code_language_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unique_link` (`link` ASC) VISIBLE,
  INDEX `index_user_id` (`user_id` ASC) VISIBLE,
  INDEX `index_code_language_id` (`code_language_id` ASC) VISIBLE,
  CONSTRAINT `fk_code_language_id`
    FOREIGN KEY (`code_language_id`)
    REFERENCES `ktor`.`code_language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ktor`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ktor`.`ouath_github`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ktor`.`ouath_github` (
  `github_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`github_id`),
  INDEX `index_associed_user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `associed_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ktor`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
