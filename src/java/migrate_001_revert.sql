ALTER TABLE `tentag`.`property` DROP FOREIGN KEY `fk_property_billing_address`;
ALTER TABLE `tentag`.`property` DROP FOREIGN KEY `fk_property_address`;
ALTER TABLE `tentag`.`property` DROP FOREIGN KEY `fk_property_contact`;
DROP TABLE `tentag`.`property`;

ALTER TABLE `tentag`.`printer` DROP FOREIGN KEY `fk_printer_contact`;
ALTER TABLE `tentag`.`printer` DROP FOREIGN KEY `fk_printer_address`;
DROP TABLE `tentag`.`printer`;

DROP TABLE `tentag`.`contact`;

ALTER TABLE `tentag`.`address` DROP FOREIGN KEY `fk_address_state`;
DROP TABLE `tentag`.`address`;

DROP TABLE `tentag`.`state`;

ALTER TABLE `tentag`.`user_group` DROP FOREIGN KEY `fk_usergroup_user`;
ALTER TABLE `tentag`.`user_group` DROP FOREIGN KEY `fk_usergroup_group`;
DROP TABLE `tentag`.`user_group`;

DROP TABLE `tentag`.`group`;

DROP TABLE `tentag`.`user`;