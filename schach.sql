-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 13. Jan 2022 um 16:42
-- Server-Version: 10.4.19-MariaDB
-- PHP-Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `schach`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `currentmatch`
--

CREATE TABLE `currentmatch` (
  `ID` int(11) NOT NULL,
  `Spielstand` longtext NOT NULL,
  `Spieler` text NOT NULL,
  `Farbe` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `currentmatch`
--

INSERT INTO `currentmatch` (`ID`, `Spielstand`, `Spieler`, `Farbe`) VALUES
(1, 'TPLKQLPT/AAAAAAAA/00000000/00000000/00000000/00000000/aaaaaaaa/tplkqlpt/-w-1', '-', 'w');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `spieler`
--

CREATE TABLE `spieler` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Siege` int(11) NOT NULL,
  `Beitritts Datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `currentmatch`
--
ALTER TABLE `currentmatch`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `spieler`
--
ALTER TABLE `spieler`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `currentmatch`
--
ALTER TABLE `currentmatch`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT für Tabelle `spieler`
--
ALTER TABLE `spieler`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
