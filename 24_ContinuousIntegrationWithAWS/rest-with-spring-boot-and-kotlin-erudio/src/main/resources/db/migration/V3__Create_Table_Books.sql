CREATE TABLE `books` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `author` longtext NOT NULL,
  `launch_date` datetime(6),
  `price` decimal(65,2) NOT NULL,
  `title` longtext  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
