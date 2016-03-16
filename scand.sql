# SQL Manager Lite for MySQL 5.5.3.46192
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : scand


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `scand`;

CREATE DATABASE `scand`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_unicode_ci';

USE `scand`;

#
# Удаление объектов БД
#

DROP TABLE IF EXISTS `reports`;

#
# Структура для таблицы `reports`: 
#

CREATE TABLE `reports` (
  `id` INTEGER(20) NOT NULL AUTO_INCREMENT,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  `performer` VARCHAR(50) COLLATE utf8_unicode_ci NOT NULL,
  `activity` VARCHAR(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB
AUTO_INCREMENT=8 CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci'
;

#
# Data for the `reports` table  (LIMIT 0,500)
#

INSERT INTO `reports` (`id`, `startDate`, `endDate`, `performer`, `activity`) VALUES
  (1,'2016-01-12','2016-01-25','Шагов А.А.','Празднование ДР.'),
  (2,'2014-01-15','2014-12-19','Приходченко Д.М.','Раскрутка сайта магазина.'),
  (3,'2013-01-02','2014-06-13','Шагова А.Н.','Открытие магазина.'),
  (4,'2016-01-05','2016-07-20','Веселов Д.М.','Заключение договоров.'),
  (5,'2015-01-06','2015-11-27','Буланович В.Н.','Пиар.'),
  (6,'2012-03-13','2013-06-15','Волчок А.П.','Создание сайта.'),
  (7,'2016-03-17','2019-01-24','Снытко А.В.','Создание бизнес-плана.');
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;