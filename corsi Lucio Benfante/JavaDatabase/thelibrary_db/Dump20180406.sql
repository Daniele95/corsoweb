CREATE DATABASE  IF NOT EXISTS `thelibrary` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `thelibrary`;
-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: thelibrary
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Author`
--

DROP TABLE IF EXISTS `Author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Author` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(64) DEFAULT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Author`
--

LOCK TABLES `Author` WRITE;
/*!40000 ALTER TABLE `Author` DISABLE KEYS */;
INSERT INTO `Author` VALUES (1,'Agatha 2','Christie');
/*!40000 ALTER TABLE `Author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `id` bigint(20) NOT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  `publisher_id` bigint(20) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `Book_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `Publisher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (1,'1234-4566','Dieci Piccoli Indiani',200,'1951',1,12.30);
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book_Author`
--

DROP TABLE IF EXISTS `Book_Author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book_Author` (
  `book_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `Book_Author_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `Book` (`id`),
  CONSTRAINT `Book_Author_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `Author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book_Author`
--

LOCK TABLES `Book_Author` WRITE;
/*!40000 ALTER TABLE `Book_Author` DISABLE KEYS */;
INSERT INTO `Book_Author` VALUES (1,1);
/*!40000 ALTER TABLE `Book_Author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book_Book_Category`
--

DROP TABLE IF EXISTS `Book_Book_Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book_Book_Category` (
  `book_id` bigint(20) NOT NULL,
  `book_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`book_category_id`),
  KEY `book_category_id` (`book_category_id`),
  CONSTRAINT `Book_Book_Category_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `Book` (`id`),
  CONSTRAINT `Book_Book_Category_ibfk_2` FOREIGN KEY (`book_category_id`) REFERENCES `Book_Category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book_Book_Category`
--

LOCK TABLES `Book_Book_Category` WRITE;
/*!40000 ALTER TABLE `Book_Book_Category` DISABLE KEYS */;
INSERT INTO `Book_Book_Category` VALUES (1,4);
/*!40000 ALTER TABLE `Book_Book_Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book_Category`
--

DROP TABLE IF EXISTS `Book_Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book_Category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book_Category`
--

LOCK TABLES `Book_Category` WRITE;
/*!40000 ALTER TABLE `Book_Category` DISABLE KEYS */;
INSERT INTO `Book_Category` VALUES (0,'OTHER'),(1,'ARTS_AND_PHOTOGRAPHY'),(2,'COMPUTERS_AND_TECHNOLOGY'),(3,'HISTORY'),(4,'LITERATURE_AND_FICTION');
/*!40000 ALTER TABLE `Book_Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Publisher`
--

DROP TABLE IF EXISTS `Publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Publisher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Publisher`
--

LOCK TABLES `Publisher` WRITE;
/*!40000 ALTER TABLE `Publisher` DISABLE KEYS */;
INSERT INTO `Publisher` VALUES (1,'Mondandori'),(2,'Manning');
/*!40000 ALTER TABLE `Publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'thelibrary'
--

--
-- Dumping routines for database 'thelibrary'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-06 16:51:19
