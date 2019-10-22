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

-- Panels
CREATE TABLE `pawtrello`.`panels`
(
  `panel_id` INT NOT NULL AUTO_INCREMENT,
  `panel_name` VARCHAR(100) NULL,
  `panel_board_id` INT NOT NULL,
  `panel_audit_cd` DATETIME NULL,
  `panel_audit_md` DATETIME NULL,
  PRIMARY KEY (`panel_id`)
);

-- Tasks
CREATE TABLE `pawtrello`.`tasks`
(
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `task_name` VARCHAR(200) NULL,
  `task_description` VARCHAR(2000) NULL,
  `task_panel_id` INT NOT NULL,
  `task_creator_id` INT NOT NULL,
  `task_members` VARCHAR(2000) NULL,
  `task_category` VARCHAR(100) NULL,
  `task_audit_cd` DATETIME NULL,
  `task_audit_md` DATETIME NULL,
  PRIMARY KEY (`task_id`)
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
