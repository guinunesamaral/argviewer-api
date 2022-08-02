-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Elo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Elo` (
  `Id` INT NOT NULL,
  `Titulo` VARCHAR(20) NOT NULL,
  `Descricao` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `Id` INT NOT NULL,
  `Nome` VARCHAR(200) NOT NULL,
  `Nickname` VARCHAR(40) NOT NULL,
  `Email` VARCHAR(200) NOT NULL,
  `Senha` VARCHAR(200) NOT NULL,
  `DataCriacao` DATETIME NOT NULL,
  `DataAlteracao` DATETIME NULL,
  `Foto` MEDIUMBLOB NOT NULL,
  `IsAnonimo` TINYINT NOT NULL,
  `IsModerador` TINYINT NOT NULL,
  `IdElo` INT NOT NULL,
  PRIMARY KEY (`Id`, `IdElo`),
  INDEX `ix_Usuario_Nome` (`Nome` ASC) INVISIBLE,
  INDEX `ix_Usuario_Nickname` (`Nickname` ASC) VISIBLE,
  INDEX `fk_Usuario_Elo` (`IdElo` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Elo`
    FOREIGN KEY (`IdElo`)
    REFERENCES `mydb`.`Elo` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Proposicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Proposicao` (
  `Id` INT NOT NULL,
  `Texto` VARCHAR(400) NOT NULL,
  `Fonte` VARCHAR(300) NULL,
  `DataCriacao` DATETIME NOT NULL,
  `DataAlteracao` DATETIME NULL,
  `QtdUpvotes` INT NOT NULL,
  `QtdDownvotes` INT NOT NULL,
  `Relevancia` INT NOT NULL,
  `Veracidade` INT NOT NULL,
  `IdUsuario` INT NOT NULL,
  PRIMARY KEY (`Id`, `IdUsuario`),
  INDEX `fk_Proposicao_Usuario` (`IdUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Proposicao_Usuario`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `mydb`.`Usuario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tag` (
  `Id` INT NOT NULL,
  `Titulo` VARCHAR(30) NOT NULL,
  `Descricao` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TagProposicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TagProposicao` (
  `IdTag` INT NOT NULL,
  `IdProposicao` INT NOT NULL,
  PRIMARY KEY (`IdTag`, `IdProposicao`),
  INDEX `fk_Proposicao` (`IdProposicao` ASC) VISIBLE,
  INDEX `fk_Tag` (`IdTag` ASC) VISIBLE,
  CONSTRAINT `fk_Tag`
    FOREIGN KEY (`IdTag`)
    REFERENCES `mydb`.`Tag` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Proposicao`
    FOREIGN KEY (`IdProposicao`)
    REFERENCES `mydb`.`Proposicao` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Action`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Action` (
  `Id` INT NOT NULL,
  `Titulo` VARCHAR(45) NOT NULL,
  `Descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Historico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Historico` (
  `Id` INT NOT NULL,
  `Data` DATETIME NOT NULL,
  `IdUsuario` INT NOT NULL,
  `IdAction` INT NOT NULL,
  PRIMARY KEY (`Id`, `IdUsuario`, `IdAction`),
  INDEX `fk_Historico_Usuario` (`IdUsuario` ASC) VISIBLE,
  INDEX `fk_Historico_Action` (`IdAction` ASC) VISIBLE,
  CONSTRAINT `fk_Historico_Usuario`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `mydb`.`Usuario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_Action`
    FOREIGN KEY (`IdAction`)
    REFERENCES `mydb`.`Action` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SeguidorSeguindo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SeguidorSeguindo` (
  `IdUsuario` INT NOT NULL,
  `IdSeguidor` INT NOT NULL,
  PRIMARY KEY (`IdUsuario`, `IdSeguidor`),
  INDEX `fk_Seguidor` (`IdSeguidor` ASC) VISIBLE,
  INDEX `fk_Usuario` (`IdUsuario` ASC) INVISIBLE,
  CONSTRAINT `fk_Usuario`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `mydb`.`Usuario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Seguidor`
    FOREIGN KEY (`IdSeguidor`)
    REFERENCES `mydb`.`Usuario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UsuarioSeguindoProposicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`UsuarioSeguindoProposicao` (
  `IdUsuario` INT NOT NULL,
  `IdProposicao` INT NOT NULL,
  `IdDonoProposicao` INT NOT NULL,
  PRIMARY KEY (`IdUsuario`, `IdProposicao`, `IdDonoProposicao`),
  INDEX `fk_Usuario` (`IdProposicao` ASC, `IdDonoProposicao` ASC) VISIBLE,
  INDEX `fk_Proposicao` (`IdUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `mydb`.`Usuario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Proposicao`
    FOREIGN KEY (`IdProposicao`)
    REFERENCES `mydb`.`Proposicao` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Argumento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Argumento` (
  `IdProposicaoMae` INT NOT NULL,
  `IdUsuarioProposicaoMae` INT NOT NULL,
  `IdProposicaoFilha` INT NOT NULL,
  `IdUsuarioProposicaoFilha` INT NOT NULL,
  PRIMARY KEY (`IdProposicaoMae`, `IdUsuarioProposicaoMae`, `IdProposicaoFilha`, `IdUsuarioProposicaoFilha`),
  INDEX `fk_Argumento_ProposicaoFIlha` (`IdProposicaoFilha` ASC, `IdUsuarioProposicaoFilha` ASC) VISIBLE,
  INDEX `fk_Argumento_ProposicaoMae` (`IdProposicaoMae` ASC, `IdUsuarioProposicaoMae` ASC) VISIBLE,
  CONSTRAINT `fk_ProposicaoMae`
    FOREIGN KEY (`IdProposicaoMae`)
    REFERENCES `mydb`.`Proposicao` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProposicaoFilha`
    FOREIGN KEY (`IdProposicaoFilha`)
    REFERENCES `mydb`.`Proposicao` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb`;

DELIMITER $$
USE `mydb`$$
$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
