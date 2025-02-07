USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `members`
VALUES
('saikiran','{bcrypt}$2a$12$blNdBlyp.Ex428Nae5E7U.KJVS6Df/ruHM9Cs6t1VKchcnibwvHCy',1),
('shiva','{bcrypt}$2a$12$blNdBlyp.Ex428Nae5E7U.KJVS6Df/ruHM9Cs6t1VKchcnibwvHCy',1),
('vishnu','{bcrypt}$2a$12$blNdBlyp.Ex428Nae5E7U.KJVS6Df/ruHM9Cs6t1VKchcnibwvHCy',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('saikiran','ROLE_EMPLOYEE'),
('shiva','ROLE_EMPLOYEE'),
('shiva','ROLE_MANAGER'),
('vishnu','ROLE_EMPLOYEE'),
('vishnu','ROLE_MANAGER'),
('vishnu','ROLE_ADMIN');
