GRANT SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT  ON *.* TO 'debezium' IDENTIFIED BY 'dbz';

USE researchdb;

CREATE TABLE `institutes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `researchers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `institute_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_INSTITUTE` (`institute_id`),
  CONSTRAINT `FK_INSTITUTE` FOREIGN KEY (`institute_id`) REFERENCES `institutes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
