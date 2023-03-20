-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 23, 2022 at 08:43 AM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jee_vente`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produit_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `qte_com` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `produit_id`, `user_id`, `qte_com`) VALUES
(1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
CREATE TABLE IF NOT EXISTS `cart_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL,
  `qte_com` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `produit_id` (`produit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_complet` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `adresse_complet` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `nom_complet`, `adresse_complet`, `email`, `tel`) VALUES
(1, 'fina', 'adresse', 'fina@gmail.com', '03412222222');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL,
  `qte_paye` int(11) DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `produit_id` (`produit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `user_id`, `produit_id`, `qte_paye`, `date`) VALUES
(1, 7, 1, 2, '2022-07-07 12:45:25'),
(2, 7, 4, 1, '2022-06-23 22:37:34'),
(3, 5, 2, 2, '2022-07-07 11:08:36'),
(4, 5, 3, 2, '2022-07-07 11:08:36'),
(5, 5, 4, 2, '2022-07-20 12:14:14'),
(6, 5, 2, 2, '2022-07-20 12:14:14'),
(7, 5, 1, 1, '2022-08-01 11:53:37'),
(8, 5, 8, 1, '2022-08-01 11:53:37');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_creation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prixu` float NOT NULL,
  `ref_produit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qte` int(11) DEFAULT NULL,
  `image` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `categorie` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `date_creation`, `nom`, `prixu`, `ref_produit`, `qte`, `image`, `categorie`) VALUES
(1, 'Chine', 'Hp', 15000, 'p1', 9, 'product01.png', 'Laptop'),
(2, 'Chine', 'Samsung', 5000, 'p2', 8, 'product07.png', 'Mobile'),
(3, 'Chine', 'Casque', 200, 'p3', 3, 'product02.png', 'Accesoire'),
(4, 'Chine', 'Acer', 20000, 'p4', 7, 'product08.png', 'Laptop'),
(5, 'Chine', 'Itel', 4000, 'p5', 8, 'product07.png', 'Mobile'),
(6, 'Chine', 'Camera', 2000, 'p6', 11, 'product09.png', 'Accesoire'),
(7, 'Chine', 'Asus', 30000, 'p7', 11, 'product06.png', 'Laptop'),
(8, 'Chine', 'Ecouteur', 500, 'p8', 8, 'product05.png', 'Accesoire'),
(9, 'Chine', 'Asus ', 50000, 'p7', 10, 'product03.png', 'Laptop'),
(10, 'denver', 'canon', 2000, 'p8', 10, 'camera.jpg', 'Accesoire');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_date` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `address`, `phone`, `update_date`, `create_date`, `delete_date`) VALUES
(1, 'test1Update', 'adresse1Update2', '035454541', '2022-06-23 13:16:35', '2022-06-23 00:00:00', '2022-06-23 00:00:00'),
(2, 'test2Update', 'adresse2Update2', '564654651', '2022-06-23 13:16:35', '2022-06-23 00:00:00', '2022-06-23 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adrs` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `ville` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cp` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `adrs`, `ville`, `cp`, `tel`, `email`, `nom`, `password`) VALUES
(1, 'Andrainjato', NULL, NULL, NULL, 'fina@gmail.com', 'fina', '$2a$10$7uAwKsclkzh12I9fl4sKF.0jg76g78sXg.pnjaHzCpCb7zeZLD6nO'),
(2, 'Andrainjato', NULL, NULL, NULL, 'fina1@gmail.com', 'fina1', 'fina1'),
(4, 'Andrainjato', NULL, NULL, NULL, 'fina2@gmail.com', 'fina2', 'fina1'),
(5, 'Andrainjato', 'Fianarantsoa', '302', '0344555555', 'randria@gmail.com', 'randria', '$2a$10$mox3cQGymMCKNJBm2oaEDeRnSIf4Lfe4IWfGJ8Jc0hH9R4iXnINLe'),
(6, 'dndhgzdzgd gdbzgdqzdh gdluzdglqizgd', NULL, NULL, NULL, 'test@gmail.com', 'test', '$2a$10$QYEUpAlXsi1HzbPJ7JRylejm6w55KZRM75wYPZeQO5OJdc7.Nfghu'),
(7, 'Andrainjato', 'Fianarantsoa', '301', '0344552222', 'finaritra@gmail.com', 'finaritra', '$2a$10$9Cm4U07R0z5K.61nQwNp3.zhoZo0WrdMAy.XFccQA867DaE7HSdcy');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
