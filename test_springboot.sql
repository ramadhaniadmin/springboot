-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2022 at 02:54 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_springboot`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(40),
(40),
(40);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_history_id` bigint(20) NOT NULL,
  `activity_date` datetime NOT NULL,
  `amount` bigint(20) NOT NULL,
  `transaction_type_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `trx_type`
--

CREATE TABLE `trx_type` (
  `transaction_type_id` bigint(20) NOT NULL,
  `transaction_code` varchar(255) DEFAULT NULL,
  `transaction_name` varchar(255) DEFAULT NULL,
  `activity_date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trx_type`
--

INSERT INTO `trx_type` (`transaction_type_id`, `transaction_code`, `transaction_name`, `activity_date`) VALUES
(31, '001', 'TRANSFER', NULL),
(32, '002', 'TAGIHAN', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `acces_token` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `acces_token`, `account_number`, `created_at`, `password_hash`, `updated_at`, `username`) VALUES
(39, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMyIsImV4cCI6MTY0OTUzMDY1MiwiaWF0IjoxNjQ5NTEyNjUyfQ.6VMH2AEn42R8wDZgfUEc2_FVMKTbBXiQiyzE0aqtMe3VRfmqgPwV_34mQWWPYjxu92Nq0kpyCaM-hUiMWTSdDA', NULL, '2022-04-09 20:31:25', '19223a7bbd7325516f069df18b50', '2022-04-09 20:57:33', 'admin123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_history_id`);

--
-- Indexes for table `trx_type`
--
ALTER TABLE `trx_type`
  ADD PRIMARY KEY (`transaction_type_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK_9g6twa8nf670dj14oit322ad8` (`acces_token`),
  ADD UNIQUE KEY `UK_d4t5vk5oufhxosw60bxqut2vq` (`account_number`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
