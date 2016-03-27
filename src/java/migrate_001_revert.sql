ALTER TABLE `tentag`.`group_property` DROP FOREIGN KEY `fk_groupproperty_property`;
ALTER TABLE `tentag`.`group_property` DROP FOREIGN KEY `fk_groupproperty_group`;
ALTER TABLE `tentag`.`group_printer` DROP FOREIGN KEY `fk_groupprinter_printer`;
ALTER TABLE `tentag`.`group_printer` DROP FOREIGN KEY `fk_groupprinter_group`;
ALTER TABLE `tentag`.`permit_history` DROP FOREIGN KEY `fk_permithistory_permit`;
ALTER TABLE `tentag`.`permit` DROP FOREIGN KEY `fk_permit_state`;
ALTER TABLE `tentag`.`permit` DROP FOREIGN KEY `fk_permit_unit`;
ALTER TABLE `tentag`.`permit` DROP FOREIGN KEY `fk_permit_batch`;
ALTER TABLE `tentag`.`unit` DROP FOREIGN KEY `fk_unit_property`;
ALTER TABLE `tentag`.`batch_history` DROP FOREIGN KEY `fk_batchhistory_batch`;
ALTER TABLE `tentag`.`batch` DROP FOREIGN KEY `fk_batch_printer`;
ALTER TABLE `tentag`.`batch` DROP FOREIGN KEY `fk_batch_property`;
ALTER TABLE `tentag`.`property` DROP FOREIGN KEY `fk_property_billing_address`;
ALTER TABLE `tentag`.`property` DROP FOREIGN KEY `fk_property_address`;
ALTER TABLE `tentag`.`property` DROP FOREIGN KEY `fk_property_contact`;
ALTER TABLE `tentag`.`printer` DROP FOREIGN KEY `fk_printer_contact`;
ALTER TABLE `tentag`.`printer` DROP FOREIGN KEY `fk_printer_address`;
ALTER TABLE `tentag`.`address` DROP FOREIGN KEY `fk_address_state`;
ALTER TABLE `tentag`.`user_group` DROP FOREIGN KEY `fk_usergroup_user`;
ALTER TABLE `tentag`.`user_group` DROP FOREIGN KEY `fk_usergroup_group`;

DROP TABLE `tentag`.`group_property`;
DROP TABLE `tentag`.`group_printer`;
DROP TABLE `tentag`.`permit_history`;
DROP TABLE `tentag`.`permit`;
DROP TABLE `tentag`.`unit`;
DROP TABLE `tentag`.`batch_history`;
DROP TABLE `tentag`.`batch`;
DROP TABLE `tentag`.`permit_status_bit_map`;
DROP TABLE `tentag`.`batch_status_bit_map`;
DROP TABLE `tentag`.`property`;
DROP TABLE `tentag`.`printer`;
DROP TABLE `tentag`.`contact`;
DROP TABLE `tentag`.`address`;
DROP TABLE `tentag`.`state`;
DROP TABLE `tentag`.`user_group`;
DROP TABLE `tentag`.`group`;
DROP TABLE `tentag`.`user`;



