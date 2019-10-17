-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 17 oct. 2019 à 06:48
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `dbevaljeu`
--
CREATE DATABASE IF NOT EXISTS `dbevaljeu` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dbevaljeu`;

-- --------------------------------------------------------

--
-- Structure de la table `calcul`
--

DROP TABLE IF EXISTS `calcul`;
CREATE TABLE IF NOT EXISTS `calcul` (
  `idcalcul` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `calcul` varchar(45) DEFAULT NULL,
  `resultat` varchar(45) DEFAULT NULL,
  `reponce` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `idpartie` int(11) NOT NULL,
  PRIMARY KEY (`idcalcul`),
  UNIQUE KEY `idcalcul_UNIQUE` (`idcalcul`),
  KEY `fk_calcul_partie1_idx` (`idpartie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `partie`
--

DROP TABLE IF EXISTS `partie`;
CREATE TABLE IF NOT EXISTS `partie` (
  `idpartie` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `iduser` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idpartie`),
  KEY `fk_partie_user1_idx` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `idbestpartie` int(11) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_partie1_idx` (`idbestpartie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `calcul`
--
ALTER TABLE `calcul`
  ADD CONSTRAINT `fk_calcul_partie1` FOREIGN KEY (`idpartie`) REFERENCES `partie` (`idpartie`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `partie`
--
ALTER TABLE `partie`
  ADD CONSTRAINT `fk_partie_user1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_partie1` FOREIGN KEY (`idbestpartie`) REFERENCES `partie` (`idpartie`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
