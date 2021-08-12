-- MySQL dump 10.13  Distrib 8.0.26, for Linux (x86_64)
--
-- Host: localhost    Database: algafood
-- ------------------------------------------------------
-- Server version	8.0.26-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `algafood`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `algafood` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `algafood`;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `estado_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cidade_estado_01` (`estado_id`),
  CONSTRAINT `FK_cidade_estado_01` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1001,'Cidade 1',1001),(1002,'Cidade 2',1001),(1003,'Cidade 3',1002),(1004,'Cidade 4',1003);
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cozinha`
--

DROP TABLE IF EXISTS `cozinha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cozinha` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cozinha`
--

LOCK TABLES `cozinha` WRITE;
/*!40000 ALTER TABLE `cozinha` DISABLE KEYS */;
INSERT INTO `cozinha` VALUES (1001,'Tailandesa'),(1002,'Irlandesa');
/*!40000 ALTER TABLE `cozinha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1001,'São Paulo'),(1002,'Rio de Janeiro'),(1003,'Rio Grande do Sul'),(1004,'Minas Gerais');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'001','criacao-inicial','SQL','V001__criacao-inicial.sql',-920666611,'root','2021-07-26 23:34:43',855,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_pagamento`
--

DROP TABLE IF EXISTS `forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forma_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pagamento`
--

LOCK TABLES `forma_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_pagamento` DISABLE KEYS */;
INSERT INTO `forma_pagamento` VALUES (1001,'DINHEIRO'),(1002,'CARTÂO'),(1003,'CHEQUE');
/*!40000 ALTER TABLE `forma_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_permissao`
--

DROP TABLE IF EXISTS `grupo_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_permissao` (
  `grupo_id` bigint NOT NULL,
  `permissao_id` bigint NOT NULL,
  PRIMARY KEY (`grupo_id`,`permissao_id`),
  KEY `FK_grupo_permissao_permissao_01` (`permissao_id`),
  CONSTRAINT `FK_grupo_permissao_grupo_01` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK_grupo_permissao_permissao_01` FOREIGN KEY (`permissao_id`) REFERENCES `permissao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_permissao`
--

LOCK TABLES `grupo_permissao` WRITE;
/*!40000 ALTER TABLE `grupo_permissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissao`
--

LOCK TABLES `permissao` WRITE;
/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` VALUES (9,'Permissão para consultar cadastros','PERMISSAO_CONSULTA'),(10,'Permissão para incluir cadastros','PERMISSAO_INCLUSAO');
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `restaurante_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_produto_restaurante_01` (`restaurante_id`),
  CONSTRAINT `FK_produto_restaurante_01` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1001,_binary '','Xbox one',2450.99,1001),(1002,_binary '','Panela de inox',323.5,1001),(1003,_binary '','Pasta de dente',7.5,1002);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_atualizacao` datetime NOT NULL,
  `data_cadastro` datetime NOT NULL,
  `endereco_bairro` varchar(255) DEFAULT NULL,
  `endereco_cep` varchar(255) DEFAULT NULL,
  `endereco_complemento` varchar(255) DEFAULT NULL,
  `endereco_logradouro` varchar(255) DEFAULT NULL,
  `endereco_numero` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `taxa_frete` decimal(19,2) NOT NULL,
  `cozinha_id` bigint NOT NULL,
  `endereco_cidade_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_restaurante_cozinha_01` (`cozinha_id`),
  KEY `FK_restaurante_cidade_02` (`endereco_cidade_id`),
  CONSTRAINT `FK_restaurante_cidade_02` FOREIGN KEY (`endereco_cidade_id`) REFERENCES `cidade` (`id`),
  CONSTRAINT `FK_restaurante_cozinha_01` FOREIGN KEY (`cozinha_id`) REFERENCES `cozinha` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante`
--

LOCK TABLES `restaurante` WRITE;
/*!40000 ALTER TABLE `restaurante` DISABLE KEYS */;
INSERT INTO `restaurante` VALUES (1001,'2021-07-27 00:10:44','2021-07-27 00:10:44',NULL,NULL,NULL,NULL,NULL,'Almanara',25550.00,1001,NULL),(1002,'2021-07-27 00:10:44','2021-07-27 00:10:44',NULL,NULL,NULL,NULL,NULL,'Burger King',1000.00,1002,NULL),(1003,'2021-07-27 00:10:44','2021-07-27 00:10:44','Paraisópolis','05659595','bloco 1','Rua Ernest Renam 954','156','Burger King',1000.00,1002,1001);
/*!40000 ALTER TABLE `restaurante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante_forma_pagamento`
--

DROP TABLE IF EXISTS `restaurante_forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante_forma_pagamento` (
  `restaurante_id` bigint NOT NULL,
  `forma_pagamento_id` bigint NOT NULL,
  PRIMARY KEY (`restaurante_id`,`forma_pagamento_id`),
  KEY `FK_restaurante_forma_pagamento_forma_pagamento_01` (`forma_pagamento_id`),
  CONSTRAINT `FK_restaurante_forma_pagamento_forma_pagamento_01` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
  CONSTRAINT `FK_restaurante_forma_pagamento_restaurante_02` FOREIGN KEY (`restaurante_id`) REFERENCES `restaurante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante_forma_pagamento`
--

LOCK TABLES `restaurante_forma_pagamento` WRITE;
/*!40000 ALTER TABLE `restaurante_forma_pagamento` DISABLE KEYS */;
INSERT INTO `restaurante_forma_pagamento` VALUES (1001,1001),(1001,1002),(1002,1003);
/*!40000 ALTER TABLE `restaurante_forma_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupo`
--

DROP TABLE IF EXISTS `usuario_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_grupo` (
  `usuario_id` bigint NOT NULL,
  `grupo_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`grupo_id`),
  KEY `FK_usuario_grupo_grupo_01` (`grupo_id`),
  CONSTRAINT `FK_usuario_grupo_grupo_01` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK_usuario_grupo_usuario_02` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupo`
--

LOCK TABLES `usuario_grupo` WRITE;
/*!40000 ALTER TABLE `usuario_grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-26 22:13:19
