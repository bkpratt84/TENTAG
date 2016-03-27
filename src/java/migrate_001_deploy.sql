CREATE TABLE `tentag`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`));

CREATE TABLE `tentag`.`group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`group_id`));

CREATE TABLE `tentag`.`user_group` (
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`),
  CONSTRAINT `fk_usergroup_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `tentag`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usergroup_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `tentag`.`group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`state` (
  `state_id` INT NOT NULL AUTO_INCREMENT,
  `state_name` VARCHAR(50) NOT NULL,
  `state_abbr` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`state_id`));

CREATE TABLE `tentag`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `address_line_1` VARCHAR(100) NOT NULL,
  `address_line_2` VARCHAR(100) NULL,
  `address_city` VARCHAR(100) NOT NULL,
  `address_state_id` INT NOT NULL,
  `address_zip` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`address_id`),
  CONSTRAINT `fk_address_state`
    FOREIGN KEY (`address_state_id`)
    REFERENCES `tentag`.`state` (`state_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`contact` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `contact_last` VARCHAR(50) NOT NULL,
  `contact_first` VARCHAR(50) NOT NULL,
  `contact_phone_p` VARCHAR(50) NULL,
  `contact_phone_s` VARCHAR(20) NULL,
  PRIMARY KEY (`contact_id`));

CREATE TABLE `tentag`.`printer` (
  `printer_id` INT NOT NULL AUTO_INCREMENT,
  `printer_contact_id` INT NULL,
  `printer_address_id` INT NULL,
  `printer_name` VARCHAR(50) NOT NULL,
  `printer_is_active` BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (`printer_id`),
  CONSTRAINT `fk_printer_contact`
    FOREIGN KEY (`printer_contact_id`)
    REFERENCES `tentag`.`contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_printer_address`
    FOREIGN KEY (`printer_address_id`)
    REFERENCES `tentag`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `tentag`.`property` (
  `property_id` INT NOT NULL AUTO_INCREMENT,
  `property_name` VARCHAR(50) NOT NULL,
  `property_contact_id` INT NOT NULL,
  `property_address_id` INT NOT NULL,
  `property_billing_address_id` INT NOT NULL,
  PRIMARY KEY (`property_id`),
  CONSTRAINT `fk_property_contact`
    FOREIGN KEY (`property_contact_id`)
    REFERENCES `tentag`.`contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_property_address`
    FOREIGN KEY (`property_address_id`)
    REFERENCES `tentag`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_property_billing_address`
    FOREIGN KEY (`property_billing_address_id`)
    REFERENCES `tentag`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
