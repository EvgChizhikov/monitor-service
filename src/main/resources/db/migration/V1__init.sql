CREATE TABLE user (
    `user_id` int NOT NULL,
    `access_token` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `user_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`user_id`)
)
DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `result` (
                          `result_id` int NOT NULL,
                          `date_of_check` datetime(6) DEFAULT NULL,
                          `payload` varchar(255) DEFAULT NULL,
                          `status_code` int DEFAULT NULL,
                          `endpoint_id` int DEFAULT NULL,
                          PRIMARY KEY (`result_id`),
                          KEY `FKcth9e8orepvkfftoppio1c92r` (`endpoint_id`),
                          CONSTRAINT `FKcth9e8orepvkfftoppio1c92r` FOREIGN KEY (`endpoint_id`) REFERENCES `endpoint` (`endpoint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` VALUES (1,NULL,'batman@example.com','Batman'),(2,NULL,'robin@example.com','Robin');