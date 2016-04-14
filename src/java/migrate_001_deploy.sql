CREATE TABLE `tentag`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`));

INSERT INTO `tentag`.`user` (`user_name`, `role_name`, `password`) VALUES ('Jesus','Admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO `tentag`.`user` (`user_name`, `role_name`, `password`) VALUES ('Mary','Property','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO `tentag`.`user` (`user_name`, `role_name`, `password`) VALUES ('Joseph','Printer','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO `tentag`.`user` (`user_name`, `role_name`, `password`) VALUES ('Ralph','Property','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO `tentag`.`user` (`user_name`, `role_name`, `password`) VALUES ('Ed','Printer','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO `tentag`.`user` (`user_name`, `role_name`, `password`) VALUES ('Admin','Admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

CREATE TABLE `tentag`.`group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,   
  `group_name` VARCHAR(50) NOT NULL,
  `role_name` VARCHAR(50) NOT NULL DEFAULT 'Property',
  PRIMARY KEY (`group_id`));

--INSERT INTO `tentag`.`group` (`group_name`, `role_name`) VALUES ('Admin Group', 'Admin');
INSERT INTO `tentag`.`group` (`group_name`, `role_name`) VALUES ('Location1 Property', 'Property');
INSERT INTO `tentag`.`group` (`group_name`, `role_name`) VALUES ('Location2 Property', 'Property');
INSERT INTO `tentag`.`group` (`group_name`, `role_name`) VALUES ('Location1 Printer', 'Printer');
INSERT INTO `tentag`.`group` (`group_name`, `role_name`) VALUES ('Location2 Printer', 'Printer');
INSERT INTO `tentag`.`group` (`group_name`, `role_name`) VALUES ('Location3 Printer', 'Printer');


CREATE TABLE `tentag`.`user_group` (
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
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


INSERT INTO `tentag`.`user_group` (`user_id`, `group_id`, `user_name`) VALUES (2,1,'Mary');
INSERT INTO `tentag`.`user_group` (`user_id`, `group_id`, `user_name`) VALUES (3,3,'Joseph');
INSERT INTO `tentag`.`user_group` (`user_id`, `group_id`, `user_name`) VALUES (4,1,'Ralph');
INSERT INTO `tentag`.`user_group` (`user_id`, `group_id`, `user_name`) VALUES (4,2,'Ralph');
INSERT INTO `tentag`.`user_group` (`user_id`, `group_id`, `user_name`) VALUES (5,3,'Ed');
INSERT INTO `tentag`.`user_group` (`user_id`, `group_id`, `user_name`) VALUES (5,4,'Ed');
INSERT INTO `tentag`.`user_group` (`user_id`, `group_id`, `user_name`) VALUES (5,5,'Ed');

CREATE TABLE `tentag`.`state` (
  `state_id` INT NOT NULL AUTO_INCREMENT,
  `state_name` VARCHAR(50) NOT NULL,
  `state_abbr` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`state_id`));

INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Alabama','AL');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Alaska','AK');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Arizona','AZ');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Arkansas','AR');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('California','CA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Colorado','CO');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Connecticut','CT');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Delaware','DE');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Florida','FL');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Georgia','GA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Hawaii','HI');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Idaho','ID');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Illinois','IL');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Indiana','IN');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Iowa','IA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Kansas','KS');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Kentucky','KY');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Louisiana','LA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Maine','ME');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Maryland','MD');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Massachusetts','MA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Michigan','MI');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Minnesota','MN');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Mississippi','MS');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Missouri','MO');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Montana','MT');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Nebraska','NE');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Nevada','NV');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('New Hampshire','NH');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('New Jersey','NJ');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('New Mexico','NM');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('New York','NY');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('North Carolina','NC');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('North Dakota','ND');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Ohio','OH');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Oklahoma','OK');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Oregon','OR');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Pennsylvania','PA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Rhode Island','RI');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('South Carolina','SC');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('South Dakota','SD');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Tennessee','TN');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Texas','TX');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Utah','UT');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Vermont','VT');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Virginia','VA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Washington','WA');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('West Virginia','WV');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Wisconsin','WI');
INSERT INTO `tentag`.`state` (`state_name`, `state_abbr`) VALUES ('Wyoming','WY');


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

INSERT INTO `tentag`.`address` (
    `address_line_1`,
    `address_line_2`,
    `address_city`,
    `address_state_id`,
    `address_zip`) 
VALUES (
    '123 NW Stree Street',
    'Suite 4',
    'Edmond',
    36,
    '73013');

INSERT INTO `tentag`.`address` (
    `address_line_1`,
    `address_line_2`,
    `address_city`,
    `address_state_id`,
    `address_zip`) 
VALUES (
    '1900 W. Ave. A',
    NULL,
    'Austn',
    43,
    '73013');

INSERT INTO `tentag`.`address` (
    `address_line_1`,
    `address_line_2`,
    `address_city`,
    `address_state_id`,
    `address_zip`) 
VALUES (
    '123 NW Stree Street',
    'Suite 4',
    'Austin',
    43,
    '73013');

INSERT INTO `tentag`.`address` (
    `address_line_1`,
    `address_line_2`,
    `address_city`,
    `address_state_id`,
    `address_zip`) 
VALUES (
    '1900 W. Ave. A',
    NULL,
    'Edmond',
    36,
    '73013');

CREATE TABLE `tentag`.`contact` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `contact_last` VARCHAR(50) NOT NULL,
  `contact_first` VARCHAR(50) NOT NULL,
  `contact_email` VARCHAR(255) NOT NULL,
  `contact_phone_p` VARCHAR(20) NOT NULL,
  `contact_phone_s` VARCHAR(20),
  PRIMARY KEY (`contact_id`));

INSERT INTO `tentag`.`contact` (
  `contact_last`,
  `contact_first`,
  `contact_email`,
  `contact_phone_p`,
  `contact_phone_s`)
VALUES (
      'Klundt'
    , 'Gordon'
    , 'gklundt@gmail.com'
    , '123-456-7890'
    , '321-654-0987');

INSERT INTO `tentag`.`contact` (
  `contact_last`,
  `contact_first`,
  `contact_email`,
  `contact_phone_p`,
  `contact_phone_s`)
VALUES (
      'Lumplump'
    , 'Willie'
    , 'w.lumplump@gmail.com'
    , '123-456-7890'
    , NULL);


INSERT INTO `tentag`.`contact` (
  `contact_last`,
  `contact_first`,
  `contact_email`,
  `contact_phone_p`,
  `contact_phone_s`)
VALUES (
      'Einstein'
    , 'Albert'
    , 'aeinstein@gmail.com'
    , '123-456-7890'
    , '321-654-0987');

INSERT INTO `tentag`.`contact` (
  `contact_last`,
  `contact_first`,
  `contact_email`,
  `contact_phone_p`,
  `contact_phone_s`)
VALUES (
      'Drew'
    , 'Nancy'
    , 'ndrew@gmail.com'
    , '123-456-7890'
    , NULL);


CREATE TABLE `tentag`.`printer` (
  `printer_id` INT NOT NULL AUTO_INCREMENT,
  `printer_contact_id` INT NOT NULL,
  `printer_address_id` INT NOT NULL,
  `printer_name` VARCHAR(50) NOT NULL,
  `printer_is_active` BIT NOT NULL DEFAULT 1,
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

INSERT `tentag`.`printer` (  
  `printer_contact_id`,
  `printer_address_id`,
  `printer_name`,
  `printer_is_active`) 
VALUES (1,1,'Kwic Print', 1);

INSERT `tentag`.`printer` (  
  `printer_contact_id`,
  `printer_address_id`,
  `printer_name`) 
VALUES (2,2,'Slow Roll Print');


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

INSERT `tentag`.`property` (  
  `property_name`,
  `property_contact_id`,
  `property_address_id`,
  `property_billing_address_id`
) 
VALUES ('Project Apartments', 3,3,3);

INSERT `tentag`.`property` (  
  `property_name`,
  `property_contact_id`,
  `property_address_id`,
  `property_billing_address_id`) 
VALUES ('Bellemont Estates', 4,4,4);

CREATE TABLE `tentag`.`batch_status_bit_map` (
  `bit` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`bit`));

INSERT `tentag`.`batch_status_bit_map` (`value`) VALUES ('Claimed');
INSERT `tentag`.`batch_status_bit_map` (`value`) VALUES ('Printed');
INSERT `tentag`.`batch_status_bit_map` (`value`) VALUES ('Delivered');
INSERT `tentag`.`batch_status_bit_map` (`value`) VALUES ('Late');

CREATE TABLE `tentag`.`permit_status_bit_map` (
  `bit` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`bit`));

INSERT `tentag`.`permit_status_bit_map` (`value`) VALUES ('Order');
INSERT `tentag`.`permit_status_bit_map` (`value`) VALUES ('Resubmit');
INSERT `tentag`.`permit_status_bit_map` (`value`) VALUES ('Revoked');

CREATE TABLE `tentag`.`batch` (
  `batch_id` INT NOT NULL AUTO_INCREMENT,
  `property_id` INT NOT NULL,
  `printer_id` INT NULL,
  `batch_status` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`batch_id`),
  CONSTRAINT `fk_batch_property`
    FOREIGN KEY (`property_id`)
    REFERENCES `tentag`.`property` (`property_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_batch_printer`
    FOREIGN KEY (`printer_id`)
    REFERENCES `tentag`.`printer` (`printer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`batch_history` (
  `sequence_id` INT NOT NULL AUTO_INCREMENT,
  `batch_id` INT NOT NULL,
  `from_status` INT NOT NULL,
  `to_status` INT NOT NULL,
  `change_dt` DATETIME NOT NULL,
  PRIMARY KEY (`sequence_id`),
  CONSTRAINT `fk_batchhistory_batch`
    FOREIGN KEY (`batch_id`)
    REFERENCES `tentag`.`batch` (`batch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`unit` (
  `unit_id` INT NOT NULL AUTO_INCREMENT,
  `property_id` INT NOT NULL,
  `unit_name` VARCHAR(50) NOT NULL,
  `unit_notes` VARCHAR(140) NULL,
  PRIMARY KEY (`unit_id`),
  CONSTRAINT `fk_unit_property`
    FOREIGN KEY (`property_id`)
    REFERENCES `tentag`.`property` (`property_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`permit` (
  `permit_id` INT NOT NULL AUTO_INCREMENT,
  `batch_id` INT NULL,
  `unit_id` INT NULL,
  `permit_status` INT NOT NULL DEFAULT 1,
  `permit_is_open_parking` BIT NOT NULL DEFAULT 1,
  `permit_assignment` VARCHAR(50) NULL,
  `permit_veh_make` VARCHAR(50) NULL,
  `permit_veh_model` VARCHAR(50) NULL,
  `permit_veh_color` VARCHAR(50) NULL,
  `permit_veh_plate_number` VARCHAR(50) NULL,
  `permit_veh_plate_state_id` INT NULL,
  PRIMARY KEY (`permit_id`),
  CONSTRAINT `fk_permit_batch`
    FOREIGN KEY (`batch_id`)
    REFERENCES `tentag`.`batch` (`batch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_permit_unit`
    FOREIGN KEY (`unit_id`)
    REFERENCES `tentag`.`unit` (`unit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_permit_state`
    FOREIGN KEY (`permit_veh_plate_state_id`)
    REFERENCES `tentag`.`state` (`state_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`permit_history` (
  `sequence_id` INT NOT NULL AUTO_INCREMENT,
  `permit_id` INT NOT NULL,
  `from_status` INT NOT NULL,
  `to_status` INT NOT NULL,
  `change_dt` DATETIME NOT NULL,
  PRIMARY KEY (`sequence_id`),
  CONSTRAINT `fk_permithistory_permit`
    FOREIGN KEY (`permit_id`)
    REFERENCES `tentag`.`permit` (`permit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`group_printer` (
  `group_id` INT NOT NULL,
  `printer_id` INT NOT NULL,
  PRIMARY KEY (`group_id`, `printer_id`),
  CONSTRAINT `fk_groupprinter_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `tentag`.`group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_groupprinter_printer`
    FOREIGN KEY (`printer_id`)
    REFERENCES `tentag`.`printer` (`printer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `tentag`.`group_property` (
  `group_id` INT NOT NULL,
  `property_id` INT NOT NULL,
  PRIMARY KEY (`group_id`, `property_id`),
  CONSTRAINT `fk_groupproperty_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `tentag`.`group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_groupproperty_property`
    FOREIGN KEY (`property_id`)
    REFERENCES `tentag`.`property` (`property_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
