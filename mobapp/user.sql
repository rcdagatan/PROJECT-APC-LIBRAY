-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2016 at 12:35 PM
-- Server version: 5.6.25
-- PHP Version: 5.5.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `age` tinyint(4) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `name`, `username`, `age`, `password`) VALUES
(12, 'qwerty', 'qwerty', 20, '$2y$10$yJ5saUR.El80nw10rcMIMuVZL/r1sOIDREFw.UWDTzoyUOIGbYaQO'),
(13, 'neil cueto', 'nrcueto', 20, '$2y$10$yfmXQGsn2SB/TMZor3rCzuMpN5VQSzk5lTwafJWVBVPPkJr3wIyfC'),
(15, 'hey', 'hey', 2, '$2y$10$lBcOV46jM8F.6ipglWntC.tC5.MJdNMsmGZ3Cit84skYUOdebVshe'),
(16, 'Sample', 'sample@gmail.com', 20, '$2y$10$/YOVkX2MTOOXf.uyVRHQeOV0MfNzPa59gPZM./B2PCbOciE0H8t4C'),
(20, 'Imagine Dragons', 'demons', 127, '$2y$10$sg1bTSC1UY1VWfg2J75VP.CyMRbtazFiux.7or9x9QI/lO.PIzBTm'),
(21, 'Coldplay', 'viva', 127, '$2y$10$HbbSorpJfcrEDsorHg/Dy.Rrsn6Xsc.uxCcBbzcDIPvIIBM5XujuW'),
(22, 'Student', 'studentsample', 127, '$2y$10$FrK4BLQwKFLyLGxF5pOiPuE3YbIqIEb59oquS1TMZa7I1yIJygDB.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
