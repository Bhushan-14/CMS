-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 02, 2023 at 06:57 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ademail` varchar(100) NOT NULL,
  `adpassword` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ademail`, `adpassword`) VALUES
('admin@gmail.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `casedetails`
--

CREATE TABLE `casedetails` (
  `CaseDetailID` int(11) NOT NULL,
  `CaseID` int(11) NOT NULL,
  `PoliceStationID` int(11) NOT NULL,
  `RegistrationDate` date DEFAULT NULL,
  `ReportingOfficer` varchar(100) DEFAULT NULL,
  `Notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `criminalcases`
--

CREATE TABLE `criminalcases` (
  `CaseID` int(11) NOT NULL,
  `CaseNumber` varchar(20) NOT NULL,
  `CaseName` varchar(100) NOT NULL,
  `CaseDescription` text DEFAULT NULL,
  `CaseStatus` enum('Open','Closed','In Progress','Solved','Other') NOT NULL,
  `OpenDate` date DEFAULT NULL,
  `CloseDate` date DEFAULT NULL,
  `LeadInvestigatorID` int(11) DEFAULT NULL,
  `PoliceStationID` int(11) DEFAULT NULL,
  `LastUpdated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `criminals`
--

CREATE TABLE `criminals` (
  `CriminalID` int(11) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Gender` enum('Male','Female','Other') DEFAULT NULL,
  `Nationality` varchar(50) DEFAULT NULL,
  `CrimeDescription` text DEFAULT NULL,
  `ArrestDate` date DEFAULT NULL,
  `PrisonLocation` varchar(100) DEFAULT NULL,
  `Status` enum('In Custody','Released','Deceased','On Parole','Wanted','Other') DEFAULT NULL,
  `PhotoURL` varchar(255) DEFAULT NULL,
  `LastUpdated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evidence`
--

CREATE TABLE `evidence` (
  `EvidenceID` int(11) NOT NULL,
  `CaseID` int(11) NOT NULL,
  `ItemName` varchar(100) NOT NULL,
  `Description` text DEFAULT NULL,
  `EvidenceType` varchar(50) DEFAULT NULL,
  `StorageLocation` varchar(100) DEFAULT NULL,
  `ChainOfCustody` varchar(255) DEFAULT NULL,
  `ReceivedDate` date DEFAULT NULL,
  `ExpiryDate` date DEFAULT NULL,
  `LastUpdated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `policestations`
--

CREATE TABLE `policestations` (
  `StationID` int(11) NOT NULL,
  `StationName` varchar(100) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `ChiefOfficer` varchar(100) DEFAULT NULL,
  `LastUpdated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Role` enum('user','admin','police_station') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `wantedcriminals`
--

CREATE TABLE `wantedcriminals` (
  `WantedID` int(11) NOT NULL,
  `CriminalID` int(11) NOT NULL,
  `PoliceStationID` int(11) NOT NULL,
  `WantedDate` date DEFAULT NULL,
  `Reward` decimal(10,2) DEFAULT NULL,
  `Status` enum('Active','Captured','Removed') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `witnesses`
--

CREATE TABLE `witnesses` (
  `WitnessID` int(11) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `Gender` enum('Male','Female','Other') DEFAULT NULL,
  `Nationality` varchar(50) DEFAULT NULL,
  `ContactInfo` varchar(255) DEFAULT NULL,
  `Statement` text DEFAULT NULL,
  `CaseID` int(11) NOT NULL,
  `LastUpdated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `casedetails`
--
ALTER TABLE `casedetails`
  ADD PRIMARY KEY (`CaseDetailID`),
  ADD KEY `CaseID` (`CaseID`),
  ADD KEY `PoliceStationID` (`PoliceStationID`);

--
-- Indexes for table `criminalcases`
--
ALTER TABLE `criminalcases`
  ADD PRIMARY KEY (`CaseID`),
  ADD UNIQUE KEY `CaseNumber` (`CaseNumber`),
  ADD KEY `LeadInvestigatorID` (`LeadInvestigatorID`),
  ADD KEY `PoliceStationID` (`PoliceStationID`);

--
-- Indexes for table `criminals`
--
ALTER TABLE `criminals`
  ADD PRIMARY KEY (`CriminalID`);

--
-- Indexes for table `evidence`
--
ALTER TABLE `evidence`
  ADD PRIMARY KEY (`EvidenceID`),
  ADD KEY `CaseID` (`CaseID`);

--
-- Indexes for table `policestations`
--
ALTER TABLE `policestations`
  ADD PRIMARY KEY (`StationID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `idx_email` (`Email`);

--
-- Indexes for table `wantedcriminals`
--
ALTER TABLE `wantedcriminals`
  ADD PRIMARY KEY (`WantedID`),
  ADD KEY `CriminalID` (`CriminalID`),
  ADD KEY `PoliceStationID` (`PoliceStationID`);

--
-- Indexes for table `witnesses`
--
ALTER TABLE `witnesses`
  ADD PRIMARY KEY (`WitnessID`),
  ADD KEY `CaseID` (`CaseID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `casedetails`
--
ALTER TABLE `casedetails`
  MODIFY `CaseDetailID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `criminalcases`
--
ALTER TABLE `criminalcases`
  MODIFY `CaseID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `criminals`
--
ALTER TABLE `criminals`
  MODIFY `CriminalID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evidence`
--
ALTER TABLE `evidence`
  MODIFY `EvidenceID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `policestations`
--
ALTER TABLE `policestations`
  MODIFY `StationID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `wantedcriminals`
--
ALTER TABLE `wantedcriminals`
  MODIFY `WantedID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `witnesses`
--
ALTER TABLE `witnesses`
  MODIFY `WitnessID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `casedetails`
--
ALTER TABLE `casedetails`
  ADD CONSTRAINT `casedetails_ibfk_1` FOREIGN KEY (`CaseID`) REFERENCES `criminalcases` (`CaseID`),
  ADD CONSTRAINT `casedetails_ibfk_2` FOREIGN KEY (`PoliceStationID`) REFERENCES `policestations` (`StationID`);

--
-- Constraints for table `criminalcases`
--
ALTER TABLE `criminalcases`
  ADD CONSTRAINT `criminalcases_ibfk_1` FOREIGN KEY (`LeadInvestigatorID`) REFERENCES `users` (`UserID`),
  ADD CONSTRAINT `criminalcases_ibfk_2` FOREIGN KEY (`PoliceStationID`) REFERENCES `policestations` (`StationID`);

--
-- Constraints for table `evidence`
--
ALTER TABLE `evidence`
  ADD CONSTRAINT `evidence_ibfk_1` FOREIGN KEY (`CaseID`) REFERENCES `criminalcases` (`CaseID`);

--
-- Constraints for table `wantedcriminals`
--
ALTER TABLE `wantedcriminals`
  ADD CONSTRAINT `wantedcriminals_ibfk_1` FOREIGN KEY (`CriminalID`) REFERENCES `criminals` (`CriminalID`),
  ADD CONSTRAINT `wantedcriminals_ibfk_2` FOREIGN KEY (`PoliceStationID`) REFERENCES `policestations` (`StationID`);

--
-- Constraints for table `witnesses`
--
ALTER TABLE `witnesses`
  ADD CONSTRAINT `witnesses_ibfk_1` FOREIGN KEY (`CaseID`) REFERENCES `criminalcases` (`CaseID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
