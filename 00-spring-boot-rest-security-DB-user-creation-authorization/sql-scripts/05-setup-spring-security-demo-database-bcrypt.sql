USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('saikiran','{bcrypt}$2a$12$kd6eTTH4Wz3lrDKno1dRAu7iwg2dxvGkDxFuUr.Sf.s4WHQzCSgTi',1),
('shiva','{bcrypt}$2a$12$kd6eTTH4Wz3lrDKno1dRAu7iwg2dxvGkDxFuUr.Sf.s4WHQzCSgTi',1),
('vishnu','{bcrypt}$2a$12$kd6eTTH4Wz3lrDKno1dRAu7iwg2dxvGkDxFuUr.Sf.s4WHQzCSgTi',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('saikiran','ROLE_EMPLOYEE'),
('shiva','ROLE_EMPLOYEE'),
('shiva','ROLE_MANAGER'),
('vishnu','ROLE_EMPLOYEE'),
('vishnu','ROLE_MANAGER'),
('vishnu','ROLE_ADMIN');