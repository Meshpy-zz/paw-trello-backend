-- Test Entity
CREATE TABLE `pawtrello`.`test_entities`
(
  `test_id` INT NOT NULL AUTO_INCREMENT,
  `test_property` VARCHAR(45) NULL,
  PRIMARY KEY (`test_id`)
);

-- Users
CREATE TABLE `pawtrello`.`users`
(
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(100) NULL,
  `user_email` VARCHAR(250) NULL,
  `user_password` VARCHAR(50) NULL,
  `user_audit_cd` DATETIME NULL,
  `user_audit_md` DATETIME NULL,
  PRIMARY KEY (`user_id`)
);

-- Boards
CREATE TABLE `pawtrello`.`boards`
(
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `board_creator_id` INT NOT NULL,
  `board_name` VARCHAR(100) NULL,
  `board_allowed_users` VARCHAR(2000) NULL,
  `board_audit_cd` DATETIME NULL,
  `board_audit_md` DATETIME NULL,
  PRIMARY KEY (`board_id`)
);

-- Lists
CREATE TABLE `pawtrello`.`lists`
(
  `list_id` INT NOT NULL AUTO_INCREMENT,
  `list_name` VARCHAR(100) NULL,
  `list_board_id` INT NOT NULL,
  `list_audit_cd` DATETIME NULL,
  `list_audit_md` DATETIME NULL,
  PRIMARY KEY (`list_id`)
);

-- Cards
CREATE TABLE `pawtrello`.`cards`
(
  `card_id` INT NOT NULL AUTO_INCREMENT,
  `card_name` VARCHAR(200) NULL,
  `card_description` VARCHAR(2000) NULL,
  `card_list_id` INT NOT NULL,
  `card_creator_id` INT NOT NULL,
  `card_members` VARCHAR(2000) NULL,
  `card_category` VARCHAR(100) NULL,
  `card_audit_cd` DATETIME NULL,
  `card_audit_md` DATETIME NULL,
  PRIMARY KEY (`card_id`)
);

-- Comments
CREATE TABLE `pawtrello`.`comments`
(
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `comment_content` VARCHAR(2000) NULL,
  `comment_creator_id` INT NOT NULL,
  `comment_task_id` INT NOT NULL,
  `comment_audit_cd` DATETIME NULL,
  `comment_audit_md` DATETIME NULL,
  PRIMARY KEY (`comment_id`)
);
