CREATE USER 'debezium'@'%' IDENTIFIED BY 'dbz';

GRANT SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'debezium'@'%';

FLUSH PRIVILEGES;

USE researchdb;

CREATE TABLE `institutes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `researchers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `institute_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_INSTITUTE` (`institute_id`),
  CONSTRAINT `FK_INSTITUTE` FOREIGN KEY (`institute_id`) REFERENCES `institutes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `researcher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ARTICLE` (`article_id`),
  KEY `FK_RESEARCHER` (`researcher_id`),
  CONSTRAINT `FK_ARTICLE` FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`),
  CONSTRAINT `FK_RESEARCHER` FOREIGN KEY (`researcher_id`) REFERENCES `researchers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
