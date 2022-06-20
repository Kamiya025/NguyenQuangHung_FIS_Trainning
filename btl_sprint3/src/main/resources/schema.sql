CREATE TABLE `detective` (
	`id` BIGINT AUTO_INCREMENT,
	`version` INT(11),
	`create_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`username` VARCHAR(50),
	`firstname` VARCHAR(50),
	`lastname` VARCHAR(50),
	`password` VARCHAR(50),
	`hiring_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`badge_number` VARCHAR(50),
	`rank` VARCHAR(20),
	`armed` BOOLEAN,
	`status` VARCHAR(20),
	PRIMARY KEY(id)
);

CREATE TABLE `criminal_case` (
	`id` BIGINT AUTO_INCREMENT,
	`version` INT(11),
	`create_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`number` VARCHAR(50),
	`type` VARCHAR(20),
	`short_descripton` VARCHAR(50),
	`deteiled_descripton` VARCHAR(50),
	`status` VARCHAR(255),
	`notes` VARCHAR(50),
--	`evidence_id` INT(11),
	`detective_id` INT(11),
	PRIMARY KEY(id)
);

CREATE TABLE working_detective_case
(
	detective_id int(11) NOT NULL,
	criminal_case_id INT(11) NOT NULL,
	FOREIGN KEY (detective_id) REFERENCES detective(id) ON DELETE CASCADE,
	FOREIGN KEY (criminal_case_id) REFERENCES criminal_case(id) ON DELETE CASCADE
)


