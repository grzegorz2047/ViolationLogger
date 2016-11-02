CREATE TABLE IF NOT EXISTS `ViolationLogger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `player` varchar(32) NOT NULL,
  `violation` varchar(32) NOT NULL,
  `power` bigint(20) NOT NULL,
  `arena` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;