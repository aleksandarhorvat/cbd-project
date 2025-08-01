-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cbd_projectdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cbd_projectdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cbd_projectdb` DEFAULT CHARACTER SET utf8 ;
USE `cbd_projectdb` ;

-- -----------------------------------------------------
-- Table `cbd_projectdb`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cbd_projectdb`.`Book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NULL,
  `year_written` YEAR NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cbd_projectdb`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cbd_projectdb`.`Role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cbd_projectdb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cbd_projectdb`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `Role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Role1_idx` (`Role_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`Role_id`)
    REFERENCES `cbd_projectdb`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cbd_projectdb`.`Copy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cbd_projectdb`.`Copy` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Book_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Copy_Book_idx` (`Book_id` ASC) VISIBLE,
  CONSTRAINT `fk_Copy_Book`
    FOREIGN KEY (`Book_id`)
    REFERENCES `cbd_projectdb`.`Book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cbd_projectdb`.`Loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cbd_projectdb`.`Loan` (
  `User_id` INT NOT NULL,
  `Copy_id` INT NOT NULL,
  `loan_date` DATE NOT NULL,
  `return_date` DATE NOT NULL,
  PRIMARY KEY (`User_id`, `Copy_id`),
  INDEX `fk_Loan_User1_idx` (`User_id` ASC) VISIBLE,
  INDEX `fk_Loan_Copy1_idx` (`Copy_id` ASC) VISIBLE,
  CONSTRAINT `fk_Loan_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `cbd_projectdb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Loan_Copy1`
    FOREIGN KEY (`Copy_id`)
    REFERENCES `cbd_projectdb`.`Copy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
