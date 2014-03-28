-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: tickets_service
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `level` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','123',2),(2,'ccc','123',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'南京','NJ'),(2,'北京','BJ'),(3,'广州','GZ'),(4,'上海','SH');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `city_id` int(32) NOT NULL,
  `info` text,
  PRIMARY KEY (`id`),
  KEY `hotel_ibfk_1_idx` (`city_id`),
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'中心大酒店',1,'位域新街口市中心，四星级酒店'),(2,'珠江1号',1,'就是拽'),(3,'环球国际酒店',2,'环球国际');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_hotel_type`
--

DROP TABLE IF EXISTS `hotel_hotel_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_hotel_type` (
  `hotel_id` bigint(20) NOT NULL,
  `hotelTypes_id` bigint(20) NOT NULL,
  UNIQUE KEY `hotelTypes_id` (`hotelTypes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_hotel_type`
--

LOCK TABLES `hotel_hotel_type` WRITE;
/*!40000 ALTER TABLE `hotel_hotel_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_hotel_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_order`
--

DROP TABLE IF EXISTS `hotel_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_order` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `hotel_type_id` int(32) NOT NULL,
  `checkin_date` date NOT NULL,
  `total_price` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_account` int(32) NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `hotel_order_ibfk_1_idx` (`hotel_type_id`),
  KEY `hotel_order_ibfk_2_idx` (`create_account`),
  CONSTRAINT `hotel_order_ibfk_1` FOREIGN KEY (`hotel_type_id`) REFERENCES `hotel_type` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `hotel_order_ibfk_2` FOREIGN KEY (`create_account`) REFERENCES `account` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_order`
--

LOCK TABLES `hotel_order` WRITE;
/*!40000 ALTER TABLE `hotel_order` DISABLE KEYS */;
INSERT INTO `hotel_order` VALUES (1,1,'2014-04-01',398,'2014-03-28 12:00:00',2,-1),(2,1,'2014-04-01',398,'2014-03-28 14:24:03',2,0),(3,2,'2014-04-01',500,'2014-03-28 14:38:18',2,0);
/*!40000 ALTER TABLE `hotel_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_type`
--

DROP TABLE IF EXISTS `hotel_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_type` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `hotel_id` int(32) NOT NULL,
  `name` varchar(45) NOT NULL,
  `num` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hotel_type_ibfk_1_idx` (`hotel_id`),
  CONSTRAINT `hotel_type_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_type`
--

LOCK TABLES `hotel_type` WRITE;
/*!40000 ALTER TABLE `hotel_type` DISABLE KEYS */;
INSERT INTO `hotel_type` VALUES (1,1,'标间',80,398,NULL),(2,1,'商务大床房',50,500,NULL),(3,2,'标准间',200,468,NULL),(4,2,'商务套间',100,888,NULL),(5,3,'总统套房',20,1288,NULL);
/*!40000 ALTER TABLE `hotel_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `true_name` varchar(45) NOT NULL,
  `id_card` varchar(45) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `account_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_fk_1_idx` (`account_id`),
  CONSTRAINT `role_fk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'方芳芳','320500199010101234',0,'152808080',2);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spot`
--

DROP TABLE IF EXISTS `spot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spot` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `city_id` int(32) NOT NULL,
  `info` text,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `spot_ibfk_1_idx` (`city_id`),
  CONSTRAINT `spot_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot`
--

LOCK TABLES `spot` WRITE;
/*!40000 ALTER TABLE `spot` DISABLE KEYS */;
INSERT INTO `spot` VALUES (1,'总统府',1,'省略三千字',40),(2,'紫峰观光层',1,'曾经的亚洲第三高',50),(3,'颐和园',2,'皇家园林',260);
/*!40000 ALTER TABLE `spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spot_order`
--

DROP TABLE IF EXISTS `spot_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spot_order` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `spot_id` int(32) NOT NULL,
  `num` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_account` int(32) NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `spot_order_ibfk_1_idx` (`create_account`),
  KEY `spot_order_ibfk_2_idx` (`spot_id`),
  CONSTRAINT `spot_order_ibfk_2` FOREIGN KEY (`spot_id`) REFERENCES `spot` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `spot_order_ibfk_1` FOREIGN KEY (`create_account`) REFERENCES `account` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot_order`
--

LOCK TABLES `spot_order` WRITE;
/*!40000 ALTER TABLE `spot_order` DISABLE KEYS */;
INSERT INTO `spot_order` VALUES (1,1,2,80,'2014-03-28 19:00:00',2,0),(2,1,1,40,'2014-03-28 19:24:33',2,0),(3,1,342,13680,'2014-03-28 19:24:39',2,-1),(4,2,3,150,'2014-03-28 19:25:32',2,0),(5,3,1,260,'2014-03-28 20:13:22',2,0);
/*!40000 ALTER TABLE `spot_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_order`
--

DROP TABLE IF EXISTS `train_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train_order` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `train_id` int(32) NOT NULL,
  `leave_date` date NOT NULL,
  `passenger_id` int(32) NOT NULL,
  `total_price` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_account` int(32) NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `train_order_idx` (`train_id`),
  KEY `train_order_ibfk__idx` (`passenger_id`),
  KEY `train_order_ibfk_3_idx` (`create_account`),
  CONSTRAINT `train_order_ibfk_1` FOREIGN KEY (`train_id`) REFERENCES `train_schedule` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `train_order_ibfk_2` FOREIGN KEY (`passenger_id`) REFERENCES `role` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `train_order_ibfk_3` FOREIGN KEY (`create_account`) REFERENCES `account` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_order`
--

LOCK TABLES `train_order` WRITE;
/*!40000 ALTER TABLE `train_order` DISABLE KEYS */;
INSERT INTO `train_order` VALUES (1,1,'2014-04-02',1,145,'2014-03-26 19:00:00',2,1),(2,1,'2014-04-01',1,145,'2014-03-27 00:00:00',2,0),(3,4,'2014-04-01',1,145,'2014-03-27 16:51:20',2,0),(4,1,'2014-04-01',1,145,'2014-03-27 21:00:12',2,-1);
/*!40000 ALTER TABLE `train_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_schedule`
--

DROP TABLE IF EXISTS `train_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train_schedule` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `train_code` varchar(45) NOT NULL,
  `start_city` int(32) NOT NULL,
  `end_city` int(32) NOT NULL,
  `start_hh` int(11) NOT NULL,
  `start_mm` int(11) NOT NULL,
  `end_hh` int(11) NOT NULL,
  `end_mm` int(11) NOT NULL,
  `end_day` int(11) DEFAULT '0',
  `capacity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `train_schedule_ibfk_1_idx` (`start_city`),
  KEY `train_schedule_ibfk_2_idx` (`end_city`),
  CONSTRAINT `train_schedule_ibfk_1` FOREIGN KEY (`start_city`) REFERENCES `city` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `train_schedule_ibfk_2` FOREIGN KEY (`end_city`) REFERENCES `city` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_schedule`
--

LOCK TABLES `train_schedule` WRITE;
/*!40000 ALTER TABLE `train_schedule` DISABLE KEYS */;
INSERT INTO `train_schedule` VALUES (1,'G7001',1,4,8,10,9,47,0,1000,145,NULL),(2,'G53',1,2,8,13,14,2,0,1200,499,NULL),(3,'T172',1,3,14,25,11,55,1,3000,200,NULL),(4,'G7005',1,4,9,0,10,15,0,1000,145,NULL),(5,'G29',1,2,12,12,20,0,0,1500,499,NULL);
/*!40000 ALTER TABLE `train_schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-28 20:15:25
