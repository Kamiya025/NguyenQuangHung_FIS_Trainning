CREATE TABLE `detective` (
	`id` INT(11) AUTO_INCREMENT,
	`version` INT(11),
	`create_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`ussername` VARCHAR(50),
	`firstname` VARCHAR(50),
	`lastname` VARCHAR(50),
	`password` VARCHAR(50),
	`hiring_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`badge_number` VARCHAR(50),
	`rank` ENUM('TRAINEE','JUNIOR','SENIOR','INSPECTOR','CHIEF_INSPECTOR'),
	`armed` BOOLEAN,
	`status` ENUM('ACTIVE','SUSPENCED','VACATION','UNDER_INVESTIGATION','RETIRED'),
	`criminal_case_id` INT(11),
	PRIMARY KEY(id)
);

CREATE TABLE `criminal_case` (
	`id` INT(11) AUTO_INCREMENT,
	`version` INT(11),
	`create_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`number` VARCHAR(50),
	`type` ENUM('UNCATEGORIZED','INFRACTION','MISDEMEANOR','FELONY'),
	`short_descripton` VARCHAR(50),
	`deteiled_descripton` VARCHAR(50),
	`status` ENUM('SUBMITED','UNDER_INVESTIGATION','INCOURT','CLOSED','DISMISSED','COLD'),
	`notes` VARCHAR(50),
	`evidence_id` INT(11),
	`load_investigator` INT(11),
	PRIMARY KEY(id)
);

