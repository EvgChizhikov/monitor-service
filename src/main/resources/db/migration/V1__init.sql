DROP TABLE IF EXISTS `endpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client ;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endpoint` (
                            `endpoint_id` int NOT NULL,
                            `date_of_creation` datetime(6) DEFAULT NULL,
                            `date_of_last_check` datetime(6) DEFAULT NULL,
                            `monitored_interval` int DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `url` varchar(255) DEFAULT NULL,
                            `owner` int DEFAULT NULL,
                            PRIMARY KEY (`endpoint_id`),
                            UNIQUE KEY `UK_h208wxajv08uftbtwo5lxg1mn` (`name`),
                            KEY `FK8l9brjajkth0o4t8508vcxoo8` (`owner`),
                            CONSTRAINT `FK8l9brjajkth0o4t8508vcxoo8` FOREIGN KEY (`owner`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `endpoint` WRITE;
/*!40000 ALTER TABLE `endpoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `endpoint` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
                          `result_id` int NOT NULL,
                          `date_of_check` datetime(6) DEFAULT NULL,
                          `payload` varchar(255) DEFAULT NULL,
                          `status_code` int DEFAULT NULL,
                          `endpoint_id` int DEFAULT NULL,
                          PRIMARY KEY (`result_id`),
                          KEY `FKcth9e8orepvkfftoppio1c92r` (`endpoint_id`),
                          CONSTRAINT `FKcth9e8orepvkfftoppio1c92r` FOREIGN KEY (`endpoint_id`) REFERENCES `endpoint` (`endpoint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `user_id` int NOT NULL,
                        `access_token` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `user_name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'batman@example.com','Batman'),(2,NULL,'robin@example.com','Robin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;