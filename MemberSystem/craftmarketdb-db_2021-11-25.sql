-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 25, 2021 at 03:10 AM
-- Server version: 5.5.34
-- PHP Version: 5.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `craftmarketdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `area` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`section_id`, `name`, `size`, `area`) VALUES
(4, 'Art', 2, 'NORTH'),
(5, 'Clothing', 5, 'EAST'),
(3, 'Food', 4, 'WEST');

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) DEFAULT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `shop_name` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `fee_paid` bit(1) DEFAULT NULL,
  `member_type` varchar(4) NOT NULL,
  `joined_date` date DEFAULT NULL,
  `pass_date` date DEFAULT NULL,
  `first_time` bit(1) DEFAULT NULL,
  PRIMARY KEY (`vendor_id`),
  KEY `section_id` (`section_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=112 ;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`vendor_id`, `section_id`, `first_name`, `last_name`, `shop_name`, `email`, `mobile`, `fee_paid`, `member_type`, `joined_date`, `pass_date`, `first_time`) VALUES
(109, 4, 'dfg', 'dfg', 'AAAAAART', 'a@a.a', '1235678888', b'0', 'DAYP', '2010-03-11', '2019-12-12', b'1'),
(105, 3, 'Fred', 'Johhs', 'Frogs Sculptures', 'a@a.com', '1234567890', b'1', 'DAYP', NULL, '2020-04-21', b'1'),
(106, 4, 'Bread', 'Man', 'Cakes and paintings', 'trim@trim.flam', '1234567891', b'1', 'PERM', '2021-10-09', NULL, NULL),
(107, 5, 'Johna', 'Smith', 'Hats World', 'a@a.hat', '1234123456', b'1', 'DAYP', NULL, '2020-11-12', b'0'),
(108, 3, 'Bron', 'Smith', 'Bron flake', 'fish@fish.fish', '1235679804', b'0', 'DAYP', NULL, '2011-12-01', b'1'),
(110, 5, 'a', 'a', 'a', 'e@e.eeee', '1234567890', b'0', 'PERM', '2020-11-11', NULL, NULL),
(111, 3, 'a', 'a', 'a', 'a@aaaa.aaaaaa', '1234567890', b'1', 'DAYP', NULL, '2020-03-03', b'1');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
