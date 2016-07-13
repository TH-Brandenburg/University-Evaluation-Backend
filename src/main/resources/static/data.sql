-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 06. Jan 2016 um 13:26
-- Server Version: 5.6.20
-- PHP-Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `caeb`
--

--
-- Daten für Tabelle `choice`
--

UPDATE `choice` SET `id` = 1,`grade` = 0,`text` = 'keine Angabe' WHERE `choice`.`id` = 1;
UPDATE `choice` SET `id` = 2,`grade` = 1,`text` = 'sehr gut' WHERE `choice`.`id` = 2;
UPDATE `choice` SET `id` = 3,`grade` = 2,`text` = 'gut' WHERE `choice`.`id` = 3;
UPDATE `choice` SET `id` = 4,`grade` = 3,`text` = 'befriedigend' WHERE `choice`.`id` = 4;
UPDATE `choice` SET `id` = 5,`grade` = 4,`text` = 'ausreichend' WHERE `choice`.`id` = 5;
UPDATE `choice` SET `id` = 6,`grade` = 5,`text` = 'ungenügend' WHERE `choice`.`id` = 6;
UPDATE `choice` SET `id` = 7,`grade` = 1,`text` = 'ja, immer' WHERE `choice`.`id` = 7;
UPDATE `choice` SET `id` = 8,`grade` = 2,`text` = 'sehr häufig' WHERE `choice`.`id` = 8;
UPDATE `choice` SET `id` = 9,`grade` = 3,`text` = 'oft' WHERE `choice`.`id` = 9;
UPDATE `choice` SET `id` = 10,`grade` = 4,`text` = 'selten' WHERE `choice`.`id` = 10;
UPDATE `choice` SET `id` = 11,`grade` = 5,`text` = 'nie' WHERE `choice`.`id` = 11;
UPDATE `choice` SET `id` = 12,`grade` = 1,`text` = 'ja, sehr' WHERE `choice`.`id` = 12;
UPDATE `choice` SET `id` = 13,`grade` = 2,`text` = 'durchaus' WHERE `choice`.`id` = 13;
UPDATE `choice` SET `id` = 14,`grade` = 3,`text` = 'mittelmäßig' WHERE `choice`.`id` = 14;
UPDATE `choice` SET `id` = 15,`grade` = 4,`text` = 'eher nicht' WHERE `choice`.`id` = 15;
UPDATE `choice` SET `id` = 16,`grade` = 5,`text` = 'überhaupt nicht' WHERE `choice`.`id` = 16;
UPDATE `choice` SET `id` = 17,`grade` = 3,`text` = 'zu hoch' WHERE `choice`.`id` = 17;
UPDATE `choice` SET `id` = 18,`grade` = 2,`text` = 'hoch' WHERE `choice`.`id` = 18;
UPDATE `choice` SET `id` = 19,`grade` = 1,`text` = 'angemessen' WHERE `choice`.`id` = 19;
UPDATE `choice` SET `id` = 20,`grade` = 2,`text` = 'niedrig' WHERE `choice`.`id` = 20;
UPDATE `choice` SET `id` = 21,`grade` = 3,`text` = 'zu niedrig' WHERE `choice`.`id` = 21;
UPDATE `choice` SET `id` = 22,`grade` = 1,`text` = 'sehr laut, sehr deutlich' WHERE `choice`.`id` = 22;
UPDATE `choice` SET `id` = 23,`grade` = 2,`text` = 'laut, präzise' WHERE `choice`.`id` = 23;
UPDATE `choice` SET `id` = 24,`grade` = 3,`text` = 'verständlich' WHERE `choice`.`id` = 24;
UPDATE `choice` SET `id` = 25,`grade` = 4,`text` = 'leise, eher undeutlich' WHERE `choice`.`id` = 25;
UPDATE `choice` SET `id` = 26,`grade` = 5,`text` = 'zu leise, undeutlich' WHERE `choice`.`id` = 26;
UPDATE `choice` SET `id` = 27,`grade` = 1,`text` = 'ja, hervorragend' WHERE `choice`.`id` = 27;
UPDATE `choice` SET `id` = 28,`grade` = 2,`text` = 'ja, fast immer' WHERE `choice`.`id` = 28;
UPDATE `choice` SET `id` = 29,`grade` = 3,`text` = 'in der Regel ja' WHERE `choice`.`id` = 29;
UPDATE `choice` SET `id` = 30,`grade` = 4,`text` = 'manchmal klappt es' WHERE `choice`.`id` = 30;
UPDATE `choice` SET `id` = 31,`grade` = 5,`text` = 'nein, nie' WHERE `choice`.`id` = 31;
UPDATE `choice` SET `id` = 32,`grade` = 1,`text` = 'ja, immer Dialog mit Studenten' WHERE `choice`.`id` = 32;
UPDATE `choice` SET `id` = 33,`grade` = 2,`text` = 'überwiegend Dialog' WHERE `choice`.`id` = 33;
UPDATE `choice` SET `id` = 34,`grade` = 3,`text` = 'gute Mischung' WHERE `choice`.`id` = 34;
UPDATE `choice` SET `id` = 35,`grade` = 4,`text` = 'zu oft Monolog' WHERE `choice`.`id` = 35;
UPDATE `choice` SET `id` = 36,`grade` = 5,`text` = 'nein, nur Monolog' WHERE `choice`.`id` = 36;
UPDATE `choice` SET `id` = 37,`grade` = 2,`text` = 'ja, wenn Zeit war' WHERE `choice`.`id` = 37;
UPDATE `choice` SET `id` = 38,`grade` = 1,`text` = 'sehr klar' WHERE `choice`.`id` = 38;
UPDATE `choice` SET `id` = 39,`grade` = 2,`text` = 'gut strukturiert' WHERE `choice`.`id` = 39;
UPDATE `choice` SET `id` = 40,`grade` = 4,`text` = 'sprunghaft' WHERE `choice`.`id` = 40;
UPDATE `choice` SET `id` = 41,`grade` = 5,`text` = 'Roter Faden fehlte' WHERE `choice`.`id` = 41;
UPDATE `choice` SET `id` = 42,`grade` = 5,`text` = 'zu viel Stoff in zuwenig Zeit' WHERE `choice`.`id` = 42;
UPDATE `choice` SET `id` = 43,`grade` = 3,`text` = 'viel Stoff' WHERE `choice`.`id` = 43;
UPDATE `choice` SET `id` = 44,`grade` = 3,`text` = 'wenig Stoff' WHERE `choice`.`id` = 44;
UPDATE `choice` SET `id` = 45,`grade` = 5,`text` = 'zu viel Zeit für zuwenig Stoff' WHERE `choice`.`id` = 45;
UPDATE `choice` SET `id` = 46,`grade` = 0,`text` = 'keine Ü/L vorhanden' WHERE `choice`.`id` = 46;
UPDATE `choice` SET `id` = 47,`grade` = 1,`text` = 'stimme zu' WHERE `choice`.`id` = 47;
UPDATE `choice` SET `id` = 48,`grade` = 2,`text` = 'stimme weitgehend zu' WHERE `choice`.`id` = 48;
UPDATE `choice` SET `id` = 49,`grade` = 3,`text` = 'unentschieden' WHERE `choice`.`id` = 49;
UPDATE `choice` SET `id` = 50,`grade` = 4,`text` = 'stimme weitgehend nicht zu' WHERE `choice`.`id` = 50;
UPDATE `choice` SET `id` = 51,`grade` = 5,`text` = 'stimme nicht zu' WHERE `choice`.`id` = 51;
UPDATE `choice` SET `id` = 52,`grade` = 5,`text` = 'viel zu viele Medien eingesetzt' WHERE `choice`.`id` = 52;
UPDATE `choice` SET `id` = 53,`grade` = 3,`text` = 'etwas zu viele Medien eingesetzt' WHERE `choice`.`id` = 53;
UPDATE `choice` SET `id` = 54,`grade` = 1,`text` = 'Medieneinsatz adäquat' WHERE `choice`.`id` = 54;
UPDATE `choice` SET `id` = 55,`grade` = 3,`text` = 'etwas zu wenige Medien eingesetzt' WHERE `choice`.`id` = 55;
UPDATE `choice` SET `id` = 56,`grade` = 5,`text` = 'viel zu wenig Medien eingesetzt' WHERE `choice`.`id` = 56;
UPDATE `choice` SET `id` = 57,`grade` = 0,`text` = 'weiss ich nicht' WHERE `choice`.`id` = 57;
UPDATE `choice` SET `id` = 58,`grade` = 1,`text` = 'habe sehr viel gelernt' WHERE `choice`.`id` = 58;
UPDATE `choice` SET `id` = 59,`grade` = 2,`text` = 'habe viel gelernt' WHERE `choice`.`id` = 59;
UPDATE `choice` SET `id` = 60,`grade` = 3,`text` = 'habe etwas gelernt' WHERE `choice`.`id` = 60;
UPDATE `choice` SET `id` = 61,`grade` = 4,`text` = 'habe wenig gelernt' WHERE `choice`.`id` = 61;
UPDATE `choice` SET `id` = 62,`grade` = 5,`text` = 'habe sehr wenig gelernt' WHERE `choice`.`id` = 62;
UPDATE `choice` SET `id` = 63,`grade` = 0,`text` = 'No comment' WHERE `choice`.`id` = 63;
UPDATE `choice` SET `id` = 64,`grade` = 1,`text` = 'Positive answer' WHERE `choice`.`id` = 64;
UPDATE `choice` SET `id` = 65,`grade` = 2,`text` = 'Negative answer' WHERE `choice`.`id` = 65;
UPDATE `choice` SET `id` = 66,`grade` = 2,`text` = 'Neutral answer' WHERE `choice`.`id` = 66;
UPDATE `choice` SET `id` = 67,`grade` = 3,`text` = 'Negative answer' WHERE `choice`.`id` = 67;
UPDATE `choice` SET `id` = 68,`grade` = 2,`text` = 'Slightly positive answer' WHERE `choice`.`id` = 68;
UPDATE `choice` SET `id` = 69,`grade` = 3,`text` = 'Slightly negative answer' WHERE `choice`.`id` = 69;
UPDATE `choice` SET `id` = 70,`grade` = 4,`text` = 'Negative answer' WHERE `choice`.`id` = 70;
UPDATE `choice` SET `id` = 71,`grade` = 3,`text` = 'neutral answer' WHERE `choice`.`id` = 71;
UPDATE `choice` SET `id` = 72,`grade` = 4,`text` = 'Slightly negative answer' WHERE `choice`.`id` = 72;
UPDATE `choice` SET `id` = 73,`grade` = 5,`text` = 'Negative answer' WHERE `choice`.`id` = 73;
UPDATE `choice` SET `id` = 74,`grade` = 1,`text` = 'Very positive answer' WHERE `choice`.`id` = 74;
UPDATE `choice` SET `id` = 75,`grade` = 2,`text` = 'positive answer' WHERE `choice`.`id` = 75;
UPDATE `choice` SET `id` = 76,`grade` = 3,`text` = 'Slightly positive answer' WHERE `choice`.`id` = 76;
UPDATE `choice` SET `id` = 77,`grade` = 6,`text` = 'Very negative answer' WHERE `choice`.`id` = 77;
UPDATE `choice` SET `id` = 78,`grade` = 4,`text` = 'Neutral answer' WHERE `choice`.`id` = 78;
UPDATE `choice` SET `id` = 79,`grade` = 5,`text` = 'Slightly negative answer' WHERE `choice`.`id` = 79;
UPDATE `choice` SET `id` = 80,`grade` = 6,`text` = 'Negative answer' WHERE `choice`.`id` = 80;
UPDATE `choice` SET `id` = 81,`grade` = 7,`text` = 'Very negative answer' WHERE `choice`.`id` = 81;

--
-- Daten für Tabelle `mc_question`
--

UPDATE `mc_question` SET `id` = 8,`text` = 'Ging der/die Dozent(in) auf Fragen innerhalb der LV ein?' WHERE `mc_question`.`id` = 8;
UPDATE `mc_question` SET `id` = 2,`text` = 'Haben Sie die Veranstaltung regelmässig besucht?' WHERE `mc_question`.`id` = 2;
UPDATE `mc_question` SET `id` = 3,`text` = 'Haben Sie Interesse an diesem Fach?' WHERE `mc_question`.`id` = 3;
UPDATE `mc_question` SET `id` = 20,`text` = 'Interface for question with 2 + 1 possible answers.' WHERE `mc_question`.`id` = 20;
UPDATE `mc_question` SET `id` = 21,`text` = 'Interface for question with 3 + 1 possible answers.' WHERE `mc_question`.`id` = 21;
UPDATE `mc_question` SET `id` = 22,`text` = 'Interface for question with 3 + 1 possible answers. The best answer placed in the middle.' WHERE `mc_question`.`id` = 22;
UPDATE `mc_question` SET `id` = 23,`text` = 'Interface for question with 4 + 1 possible answers.' WHERE `mc_question`.`id` = 23;
UPDATE `mc_question` SET `id` = 24,`text` = 'Interface for question with 5 + 1 possible answers.' WHERE `mc_question`.`id` = 24;
UPDATE `mc_question` SET `id` = 25,`text` = 'Interface for question with 5 + 1 possible answers. The best answer placed in the middle.' WHERE `mc_question`.`id` = 25;
UPDATE `mc_question` SET `id` = 26,`text` = 'Interface for question with 6 + 1 possible answers.' WHERE `mc_question`.`id` = 26;
UPDATE `mc_question` SET `id` = 28,`text` = 'Interface for question with 6 + 1 possible answers. The best answer placed in the middle.' WHERE `mc_question`.`id` = 28;
UPDATE `mc_question` SET `id` = 27,`text` = 'Interface for question with 7 + 1 possible answers.' WHERE `mc_question`.`id` = 27;
UPDATE `mc_question` SET `id` = 6,`text` = 'Kann er/sie schwierige Sachverhalte verständlich erklären?' WHERE `mc_question`.`id` = 6;
UPDATE `mc_question` SET `id` = 7,`text` = 'Versuchte der/die Dozent(in) festzustellen, ob die Studenten der LV folgen können?' WHERE `mc_question`.`id` = 7;
UPDATE `mc_question` SET `id` = 10,`text` = 'War der/die Dozent(in) gut vorbereitet?' WHERE `mc_question`.`id` = 10;
UPDATE `mc_question` SET `id` = 9,`text` = 'War er/sie auch ausserhalb der LV zu diesen Themen ansprechbar?' WHERE `mc_question`.`id` = 9;
UPDATE `mc_question` SET `id` = 11,`text` = 'Welche Gesamtnote geben Sie dem/der Dozenten(in)?' WHERE `mc_question`.`id` = 11;
UPDATE `mc_question` SET `id` = 12,`text` = 'Welche Gesamtnote geben Sie den Lehrunterlagen?' WHERE `mc_question`.`id` = 12;
UPDATE `mc_question` SET `id` = 19,`text` = 'Welche Gesamtnote geben Sie der Lehrveranstaltung?' WHERE `mc_question`.`id` = 19;
UPDATE `mc_question` SET `id` = 17,`text` = 'Wie beurteilen Sie den Medieneinsatz (Beamer, Tafel, Overhead-Projektor, usw.)?' WHERE `mc_question`.`id` = 17;
UPDATE `mc_question` SET `id` = 16,`text` = 'Wie beurteilen Sie die Ausstattung des Übungs- oder Laborraumes?' WHERE `mc_question`.`id` = 16;
UPDATE `mc_question` SET `id` = 1,`text` = 'Wie beurteilen Sie die Lehrveranstaltung insgesamt? Auf einer Skala von 1 bis 5; 1 = sehr gut, 5 = nicht gut' WHERE `mc_question`.`id` = 1;
UPDATE `mc_question` SET `id` = 18,`text` = 'Wie beurteilen Sie Ihren persönlichen Lernerfolg in dieser Lehrveranstaltung?' WHERE `mc_question`.`id` = 18;
UPDATE `mc_question` SET `id` = 4,`text` = 'Wie empfanden Sie das Niveau der Lehrveranstaltung?' WHERE `mc_question`.`id` = 4;
UPDATE `mc_question` SET `id` = 15,`text` = 'Die Übung war nützlich. Sie war sehr gut geeignet, die Vorlesungsinhalte zu verdeutlichen und zu vertiefen.' WHERE `mc_question`.`id` = 15;
UPDATE `mc_question` SET `id` = 14,`text` = 'Wie war die Stoffmenge im Verhältnis zur verfügbaren Zeit?' WHERE `mc_question`.`id` = 14;
UPDATE `mc_question` SET `id` = 13,`text` = 'Wie war die Vorgehensweise und Stoffpräsentation in der LV?' WHERE `mc_question`.`id` = 13;
UPDATE `mc_question` SET `id` = 5,`text` = 'Wie waren Sprache und Ausdrucksweise des Dozenten/der Dozentin?' WHERE `mc_question`.`id` = 5;

--
-- Daten für Tabelle `mc_question_choices`
--

UPDATE `mc_question_choices` SET `mc_question_id` = 1,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 1 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 1,`choices_id` = 2 WHERE `mc_question_choices`.`mc_question_id` = 1 AND `mc_question_choices`.`choices_id` = 2;
UPDATE `mc_question_choices` SET `mc_question_id` = 1,`choices_id` = 3 WHERE `mc_question_choices`.`mc_question_id` = 1 AND `mc_question_choices`.`choices_id` = 3;
UPDATE `mc_question_choices` SET `mc_question_id` = 1,`choices_id` = 4 WHERE `mc_question_choices`.`mc_question_id` = 1 AND `mc_question_choices`.`choices_id` = 4;
UPDATE `mc_question_choices` SET `mc_question_id` = 1,`choices_id` = 5 WHERE `mc_question_choices`.`mc_question_id` = 1 AND `mc_question_choices`.`choices_id` = 5;
UPDATE `mc_question_choices` SET `mc_question_id` = 1,`choices_id` = 6 WHERE `mc_question_choices`.`mc_question_id` = 1 AND `mc_question_choices`.`choices_id` = 6;
UPDATE `mc_question_choices` SET `mc_question_id` = 2,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 2 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 2,`choices_id` = 7 WHERE `mc_question_choices`.`mc_question_id` = 2 AND `mc_question_choices`.`choices_id` = 7;
UPDATE `mc_question_choices` SET `mc_question_id` = 2,`choices_id` = 8 WHERE `mc_question_choices`.`mc_question_id` = 2 AND `mc_question_choices`.`choices_id` = 8;
UPDATE `mc_question_choices` SET `mc_question_id` = 2,`choices_id` = 9 WHERE `mc_question_choices`.`mc_question_id` = 2 AND `mc_question_choices`.`choices_id` = 9;
UPDATE `mc_question_choices` SET `mc_question_id` = 2,`choices_id` = 10 WHERE `mc_question_choices`.`mc_question_id` = 2 AND `mc_question_choices`.`choices_id` = 10;
UPDATE `mc_question_choices` SET `mc_question_id` = 2,`choices_id` = 11 WHERE `mc_question_choices`.`mc_question_id` = 2 AND `mc_question_choices`.`choices_id` = 11;
UPDATE `mc_question_choices` SET `mc_question_id` = 3,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 3 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 3,`choices_id` = 12 WHERE `mc_question_choices`.`mc_question_id` = 3 AND `mc_question_choices`.`choices_id` = 12;
UPDATE `mc_question_choices` SET `mc_question_id` = 3,`choices_id` = 13 WHERE `mc_question_choices`.`mc_question_id` = 3 AND `mc_question_choices`.`choices_id` = 13;
UPDATE `mc_question_choices` SET `mc_question_id` = 3,`choices_id` = 14 WHERE `mc_question_choices`.`mc_question_id` = 3 AND `mc_question_choices`.`choices_id` = 14;
UPDATE `mc_question_choices` SET `mc_question_id` = 3,`choices_id` = 15 WHERE `mc_question_choices`.`mc_question_id` = 3 AND `mc_question_choices`.`choices_id` = 15;
UPDATE `mc_question_choices` SET `mc_question_id` = 3,`choices_id` = 16 WHERE `mc_question_choices`.`mc_question_id` = 3 AND `mc_question_choices`.`choices_id` = 16;
UPDATE `mc_question_choices` SET `mc_question_id` = 4,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 4 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 4,`choices_id` = 17 WHERE `mc_question_choices`.`mc_question_id` = 4 AND `mc_question_choices`.`choices_id` = 17;
UPDATE `mc_question_choices` SET `mc_question_id` = 4,`choices_id` = 18 WHERE `mc_question_choices`.`mc_question_id` = 4 AND `mc_question_choices`.`choices_id` = 18;
UPDATE `mc_question_choices` SET `mc_question_id` = 4,`choices_id` = 19 WHERE `mc_question_choices`.`mc_question_id` = 4 AND `mc_question_choices`.`choices_id` = 19;
UPDATE `mc_question_choices` SET `mc_question_id` = 4,`choices_id` = 20 WHERE `mc_question_choices`.`mc_question_id` = 4 AND `mc_question_choices`.`choices_id` = 20;
UPDATE `mc_question_choices` SET `mc_question_id` = 4,`choices_id` = 21 WHERE `mc_question_choices`.`mc_question_id` = 4 AND `mc_question_choices`.`choices_id` = 21;
UPDATE `mc_question_choices` SET `mc_question_id` = 5,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 5 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 5,`choices_id` = 22 WHERE `mc_question_choices`.`mc_question_id` = 5 AND `mc_question_choices`.`choices_id` = 22;
UPDATE `mc_question_choices` SET `mc_question_id` = 5,`choices_id` = 23 WHERE `mc_question_choices`.`mc_question_id` = 5 AND `mc_question_choices`.`choices_id` = 23;
UPDATE `mc_question_choices` SET `mc_question_id` = 5,`choices_id` = 24 WHERE `mc_question_choices`.`mc_question_id` = 5 AND `mc_question_choices`.`choices_id` = 24;
UPDATE `mc_question_choices` SET `mc_question_id` = 5,`choices_id` = 25 WHERE `mc_question_choices`.`mc_question_id` = 5 AND `mc_question_choices`.`choices_id` = 25;
UPDATE `mc_question_choices` SET `mc_question_id` = 5,`choices_id` = 26 WHERE `mc_question_choices`.`mc_question_id` = 5 AND `mc_question_choices`.`choices_id` = 26;
UPDATE `mc_question_choices` SET `mc_question_id` = 6,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 6 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 6,`choices_id` = 27 WHERE `mc_question_choices`.`mc_question_id` = 6 AND `mc_question_choices`.`choices_id` = 27;
UPDATE `mc_question_choices` SET `mc_question_id` = 6,`choices_id` = 28 WHERE `mc_question_choices`.`mc_question_id` = 6 AND `mc_question_choices`.`choices_id` = 28;
UPDATE `mc_question_choices` SET `mc_question_id` = 6,`choices_id` = 29 WHERE `mc_question_choices`.`mc_question_id` = 6 AND `mc_question_choices`.`choices_id` = 29;
UPDATE `mc_question_choices` SET `mc_question_id` = 6,`choices_id` = 30 WHERE `mc_question_choices`.`mc_question_id` = 6 AND `mc_question_choices`.`choices_id` = 30;
UPDATE `mc_question_choices` SET `mc_question_id` = 6,`choices_id` = 31 WHERE `mc_question_choices`.`mc_question_id` = 6 AND `mc_question_choices`.`choices_id` = 31;
UPDATE `mc_question_choices` SET `mc_question_id` = 7,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 7 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 7,`choices_id` = 32 WHERE `mc_question_choices`.`mc_question_id` = 7 AND `mc_question_choices`.`choices_id` = 32;
UPDATE `mc_question_choices` SET `mc_question_id` = 7,`choices_id` = 33 WHERE `mc_question_choices`.`mc_question_id` = 7 AND `mc_question_choices`.`choices_id` = 33;
UPDATE `mc_question_choices` SET `mc_question_id` = 7,`choices_id` = 34 WHERE `mc_question_choices`.`mc_question_id` = 7 AND `mc_question_choices`.`choices_id` = 34;
UPDATE `mc_question_choices` SET `mc_question_id` = 7,`choices_id` = 35 WHERE `mc_question_choices`.`mc_question_id` = 7 AND `mc_question_choices`.`choices_id` = 35;
UPDATE `mc_question_choices` SET `mc_question_id` = 7,`choices_id` = 36 WHERE `mc_question_choices`.`mc_question_id` = 7 AND `mc_question_choices`.`choices_id` = 36;
UPDATE `mc_question_choices` SET `mc_question_id` = 8,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 8 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 8,`choices_id` = 7 WHERE `mc_question_choices`.`mc_question_id` = 8 AND `mc_question_choices`.`choices_id` = 7;
UPDATE `mc_question_choices` SET `mc_question_id` = 8,`choices_id` = 37 WHERE `mc_question_choices`.`mc_question_id` = 8 AND `mc_question_choices`.`choices_id` = 37;
UPDATE `mc_question_choices` SET `mc_question_id` = 8,`choices_id` = 29 WHERE `mc_question_choices`.`mc_question_id` = 8 AND `mc_question_choices`.`choices_id` = 29;
UPDATE `mc_question_choices` SET `mc_question_id` = 8,`choices_id` = 10 WHERE `mc_question_choices`.`mc_question_id` = 8 AND `mc_question_choices`.`choices_id` = 10;
UPDATE `mc_question_choices` SET `mc_question_id` = 8,`choices_id` = 31 WHERE `mc_question_choices`.`mc_question_id` = 8 AND `mc_question_choices`.`choices_id` = 31;
UPDATE `mc_question_choices` SET `mc_question_id` = 9,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 9 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 9,`choices_id` = 7 WHERE `mc_question_choices`.`mc_question_id` = 9 AND `mc_question_choices`.`choices_id` = 7;
UPDATE `mc_question_choices` SET `mc_question_id` = 9,`choices_id` = 37 WHERE `mc_question_choices`.`mc_question_id` = 9 AND `mc_question_choices`.`choices_id` = 37;
UPDATE `mc_question_choices` SET `mc_question_id` = 9,`choices_id` = 29 WHERE `mc_question_choices`.`mc_question_id` = 9 AND `mc_question_choices`.`choices_id` = 29;
UPDATE `mc_question_choices` SET `mc_question_id` = 9,`choices_id` = 10 WHERE `mc_question_choices`.`mc_question_id` = 9 AND `mc_question_choices`.`choices_id` = 10;
UPDATE `mc_question_choices` SET `mc_question_id` = 9,`choices_id` = 31 WHERE `mc_question_choices`.`mc_question_id` = 9 AND `mc_question_choices`.`choices_id` = 31;
UPDATE `mc_question_choices` SET `mc_question_id` = 10,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 10 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 10,`choices_id` = 7 WHERE `mc_question_choices`.`mc_question_id` = 10 AND `mc_question_choices`.`choices_id` = 7;
UPDATE `mc_question_choices` SET `mc_question_id` = 10,`choices_id` = 8 WHERE `mc_question_choices`.`mc_question_id` = 10 AND `mc_question_choices`.`choices_id` = 8;
UPDATE `mc_question_choices` SET `mc_question_id` = 10,`choices_id` = 9 WHERE `mc_question_choices`.`mc_question_id` = 10 AND `mc_question_choices`.`choices_id` = 9;
UPDATE `mc_question_choices` SET `mc_question_id` = 10,`choices_id` = 10 WHERE `mc_question_choices`.`mc_question_id` = 10 AND `mc_question_choices`.`choices_id` = 10;
UPDATE `mc_question_choices` SET `mc_question_id` = 10,`choices_id` = 11 WHERE `mc_question_choices`.`mc_question_id` = 10 AND `mc_question_choices`.`choices_id` = 11;
UPDATE `mc_question_choices` SET `mc_question_id` = 11,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 11 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 11,`choices_id` = 2 WHERE `mc_question_choices`.`mc_question_id` = 11 AND `mc_question_choices`.`choices_id` = 2;
UPDATE `mc_question_choices` SET `mc_question_id` = 11,`choices_id` = 3 WHERE `mc_question_choices`.`mc_question_id` = 11 AND `mc_question_choices`.`choices_id` = 3;
UPDATE `mc_question_choices` SET `mc_question_id` = 11,`choices_id` = 4 WHERE `mc_question_choices`.`mc_question_id` = 11 AND `mc_question_choices`.`choices_id` = 4;
UPDATE `mc_question_choices` SET `mc_question_id` = 11,`choices_id` = 5 WHERE `mc_question_choices`.`mc_question_id` = 11 AND `mc_question_choices`.`choices_id` = 5;
UPDATE `mc_question_choices` SET `mc_question_id` = 11,`choices_id` = 6 WHERE `mc_question_choices`.`mc_question_id` = 11 AND `mc_question_choices`.`choices_id` = 6;
UPDATE `mc_question_choices` SET `mc_question_id` = 12,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 12 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 12,`choices_id` = 2 WHERE `mc_question_choices`.`mc_question_id` = 12 AND `mc_question_choices`.`choices_id` = 2;
UPDATE `mc_question_choices` SET `mc_question_id` = 12,`choices_id` = 3 WHERE `mc_question_choices`.`mc_question_id` = 12 AND `mc_question_choices`.`choices_id` = 3;
UPDATE `mc_question_choices` SET `mc_question_id` = 12,`choices_id` = 4 WHERE `mc_question_choices`.`mc_question_id` = 12 AND `mc_question_choices`.`choices_id` = 4;
UPDATE `mc_question_choices` SET `mc_question_id` = 12,`choices_id` = 5 WHERE `mc_question_choices`.`mc_question_id` = 12 AND `mc_question_choices`.`choices_id` = 5;
UPDATE `mc_question_choices` SET `mc_question_id` = 12,`choices_id` = 6 WHERE `mc_question_choices`.`mc_question_id` = 12 AND `mc_question_choices`.`choices_id` = 6;
UPDATE `mc_question_choices` SET `mc_question_id` = 13,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 13 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 13,`choices_id` = 38 WHERE `mc_question_choices`.`mc_question_id` = 13 AND `mc_question_choices`.`choices_id` = 38;
UPDATE `mc_question_choices` SET `mc_question_id` = 13,`choices_id` = 39 WHERE `mc_question_choices`.`mc_question_id` = 13 AND `mc_question_choices`.`choices_id` = 39;
UPDATE `mc_question_choices` SET `mc_question_id` = 13,`choices_id` = 24 WHERE `mc_question_choices`.`mc_question_id` = 13 AND `mc_question_choices`.`choices_id` = 24;
UPDATE `mc_question_choices` SET `mc_question_id` = 13,`choices_id` = 40 WHERE `mc_question_choices`.`mc_question_id` = 13 AND `mc_question_choices`.`choices_id` = 40;
UPDATE `mc_question_choices` SET `mc_question_id` = 13,`choices_id` = 41 WHERE `mc_question_choices`.`mc_question_id` = 13 AND `mc_question_choices`.`choices_id` = 41;
UPDATE `mc_question_choices` SET `mc_question_id` = 14,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 14 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 14,`choices_id` = 42 WHERE `mc_question_choices`.`mc_question_id` = 14 AND `mc_question_choices`.`choices_id` = 42;
UPDATE `mc_question_choices` SET `mc_question_id` = 14,`choices_id` = 43 WHERE `mc_question_choices`.`mc_question_id` = 14 AND `mc_question_choices`.`choices_id` = 43;
UPDATE `mc_question_choices` SET `mc_question_id` = 14,`choices_id` = 19 WHERE `mc_question_choices`.`mc_question_id` = 14 AND `mc_question_choices`.`choices_id` = 19;
UPDATE `mc_question_choices` SET `mc_question_id` = 14,`choices_id` = 44 WHERE `mc_question_choices`.`mc_question_id` = 14 AND `mc_question_choices`.`choices_id` = 44;
UPDATE `mc_question_choices` SET `mc_question_id` = 14,`choices_id` = 45 WHERE `mc_question_choices`.`mc_question_id` = 14 AND `mc_question_choices`.`choices_id` = 45;
UPDATE `mc_question_choices` SET `mc_question_id` = 15,`choices_id` = 46 WHERE `mc_question_choices`.`mc_question_id` = 15 AND `mc_question_choices`.`choices_id` = 46;
UPDATE `mc_question_choices` SET `mc_question_id` = 15,`choices_id` = 47 WHERE `mc_question_choices`.`mc_question_id` = 15 AND `mc_question_choices`.`choices_id` = 47;
UPDATE `mc_question_choices` SET `mc_question_id` = 15,`choices_id` = 48 WHERE `mc_question_choices`.`mc_question_id` = 15 AND `mc_question_choices`.`choices_id` = 48;
UPDATE `mc_question_choices` SET `mc_question_id` = 15,`choices_id` = 49 WHERE `mc_question_choices`.`mc_question_id` = 15 AND `mc_question_choices`.`choices_id` = 49;
UPDATE `mc_question_choices` SET `mc_question_id` = 15,`choices_id` = 50 WHERE `mc_question_choices`.`mc_question_id` = 15 AND `mc_question_choices`.`choices_id` = 50;
UPDATE `mc_question_choices` SET `mc_question_id` = 15,`choices_id` = 51 WHERE `mc_question_choices`.`mc_question_id` = 15 AND `mc_question_choices`.`choices_id` = 51;
UPDATE `mc_question_choices` SET `mc_question_id` = 16,`choices_id` = 46 WHERE `mc_question_choices`.`mc_question_id` = 16 AND `mc_question_choices`.`choices_id` = 46;
UPDATE `mc_question_choices` SET `mc_question_id` = 16,`choices_id` = 2 WHERE `mc_question_choices`.`mc_question_id` = 16 AND `mc_question_choices`.`choices_id` = 2;
UPDATE `mc_question_choices` SET `mc_question_id` = 16,`choices_id` = 3 WHERE `mc_question_choices`.`mc_question_id` = 16 AND `mc_question_choices`.`choices_id` = 3;
UPDATE `mc_question_choices` SET `mc_question_id` = 16,`choices_id` = 4 WHERE `mc_question_choices`.`mc_question_id` = 16 AND `mc_question_choices`.`choices_id` = 4;
UPDATE `mc_question_choices` SET `mc_question_id` = 16,`choices_id` = 5 WHERE `mc_question_choices`.`mc_question_id` = 16 AND `mc_question_choices`.`choices_id` = 5;
UPDATE `mc_question_choices` SET `mc_question_id` = 16,`choices_id` = 6 WHERE `mc_question_choices`.`mc_question_id` = 16 AND `mc_question_choices`.`choices_id` = 6;
UPDATE `mc_question_choices` SET `mc_question_id` = 17,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 17 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 17,`choices_id` = 52 WHERE `mc_question_choices`.`mc_question_id` = 17 AND `mc_question_choices`.`choices_id` = 52;
UPDATE `mc_question_choices` SET `mc_question_id` = 17,`choices_id` = 53 WHERE `mc_question_choices`.`mc_question_id` = 17 AND `mc_question_choices`.`choices_id` = 53;
UPDATE `mc_question_choices` SET `mc_question_id` = 17,`choices_id` = 54 WHERE `mc_question_choices`.`mc_question_id` = 17 AND `mc_question_choices`.`choices_id` = 54;
UPDATE `mc_question_choices` SET `mc_question_id` = 17,`choices_id` = 55 WHERE `mc_question_choices`.`mc_question_id` = 17 AND `mc_question_choices`.`choices_id` = 55;
UPDATE `mc_question_choices` SET `mc_question_id` = 17,`choices_id` = 56 WHERE `mc_question_choices`.`mc_question_id` = 17 AND `mc_question_choices`.`choices_id` = 56;
UPDATE `mc_question_choices` SET `mc_question_id` = 18,`choices_id` = 57 WHERE `mc_question_choices`.`mc_question_id` = 18 AND `mc_question_choices`.`choices_id` = 57;
UPDATE `mc_question_choices` SET `mc_question_id` = 18,`choices_id` = 58 WHERE `mc_question_choices`.`mc_question_id` = 18 AND `mc_question_choices`.`choices_id` = 58;
UPDATE `mc_question_choices` SET `mc_question_id` = 18,`choices_id` = 59 WHERE `mc_question_choices`.`mc_question_id` = 18 AND `mc_question_choices`.`choices_id` = 59;
UPDATE `mc_question_choices` SET `mc_question_id` = 18,`choices_id` = 60 WHERE `mc_question_choices`.`mc_question_id` = 18 AND `mc_question_choices`.`choices_id` = 60;
UPDATE `mc_question_choices` SET `mc_question_id` = 18,`choices_id` = 61 WHERE `mc_question_choices`.`mc_question_id` = 18 AND `mc_question_choices`.`choices_id` = 61;
UPDATE `mc_question_choices` SET `mc_question_id` = 18,`choices_id` = 62 WHERE `mc_question_choices`.`mc_question_id` = 18 AND `mc_question_choices`.`choices_id` = 62;
UPDATE `mc_question_choices` SET `mc_question_id` = 19,`choices_id` = 1 WHERE `mc_question_choices`.`mc_question_id` = 19 AND `mc_question_choices`.`choices_id` = 1;
UPDATE `mc_question_choices` SET `mc_question_id` = 19,`choices_id` = 2 WHERE `mc_question_choices`.`mc_question_id` = 19 AND `mc_question_choices`.`choices_id` = 2;
UPDATE `mc_question_choices` SET `mc_question_id` = 19,`choices_id` = 3 WHERE `mc_question_choices`.`mc_question_id` = 19 AND `mc_question_choices`.`choices_id` = 3;
UPDATE `mc_question_choices` SET `mc_question_id` = 19,`choices_id` = 4 WHERE `mc_question_choices`.`mc_question_id` = 19 AND `mc_question_choices`.`choices_id` = 4;
UPDATE `mc_question_choices` SET `mc_question_id` = 19,`choices_id` = 5 WHERE `mc_question_choices`.`mc_question_id` = 19 AND `mc_question_choices`.`choices_id` = 5;
UPDATE `mc_question_choices` SET `mc_question_id` = 19,`choices_id` = 6 WHERE `mc_question_choices`.`mc_question_id` = 19 AND `mc_question_choices`.`choices_id` = 6;
UPDATE `mc_question_choices` SET `mc_question_id` = 20,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 20 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 20,`choices_id` = 64 WHERE `mc_question_choices`.`mc_question_id` = 20 AND `mc_question_choices`.`choices_id` = 64;
UPDATE `mc_question_choices` SET `mc_question_id` = 20,`choices_id` = 65 WHERE `mc_question_choices`.`mc_question_id` = 20 AND `mc_question_choices`.`choices_id` = 65;
UPDATE `mc_question_choices` SET `mc_question_id` = 21,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 21 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 21,`choices_id` = 64 WHERE `mc_question_choices`.`mc_question_id` = 21 AND `mc_question_choices`.`choices_id` = 64;
UPDATE `mc_question_choices` SET `mc_question_id` = 21,`choices_id` = 66 WHERE `mc_question_choices`.`mc_question_id` = 21 AND `mc_question_choices`.`choices_id` = 66;
UPDATE `mc_question_choices` SET `mc_question_id` = 21,`choices_id` = 67 WHERE `mc_question_choices`.`mc_question_id` = 21 AND `mc_question_choices`.`choices_id` = 67;
UPDATE `mc_question_choices` SET `mc_question_id` = 22,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 22 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 22,`choices_id` = 67 WHERE `mc_question_choices`.`mc_question_id` = 22 AND `mc_question_choices`.`choices_id` = 67;
UPDATE `mc_question_choices` SET `mc_question_id` = 22,`choices_id` = 64 WHERE `mc_question_choices`.`mc_question_id` = 22 AND `mc_question_choices`.`choices_id` = 64;
UPDATE `mc_question_choices` SET `mc_question_id` = 22,`choices_id` = 67 WHERE `mc_question_choices`.`mc_question_id` = 22 AND `mc_question_choices`.`choices_id` = 67;
UPDATE `mc_question_choices` SET `mc_question_id` = 23,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 23 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 23,`choices_id` = 64 WHERE `mc_question_choices`.`mc_question_id` = 23 AND `mc_question_choices`.`choices_id` = 64;
UPDATE `mc_question_choices` SET `mc_question_id` = 23,`choices_id` = 68 WHERE `mc_question_choices`.`mc_question_id` = 23 AND `mc_question_choices`.`choices_id` = 68;
UPDATE `mc_question_choices` SET `mc_question_id` = 23,`choices_id` = 69 WHERE `mc_question_choices`.`mc_question_id` = 23 AND `mc_question_choices`.`choices_id` = 69;
UPDATE `mc_question_choices` SET `mc_question_id` = 23,`choices_id` = 70 WHERE `mc_question_choices`.`mc_question_id` = 23 AND `mc_question_choices`.`choices_id` = 70;
UPDATE `mc_question_choices` SET `mc_question_id` = 24,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 24 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 24,`choices_id` = 64 WHERE `mc_question_choices`.`mc_question_id` = 24 AND `mc_question_choices`.`choices_id` = 64;
UPDATE `mc_question_choices` SET `mc_question_id` = 24,`choices_id` = 68 WHERE `mc_question_choices`.`mc_question_id` = 24 AND `mc_question_choices`.`choices_id` = 68;
UPDATE `mc_question_choices` SET `mc_question_id` = 24,`choices_id` = 71 WHERE `mc_question_choices`.`mc_question_id` = 24 AND `mc_question_choices`.`choices_id` = 71;
UPDATE `mc_question_choices` SET `mc_question_id` = 24,`choices_id` = 72 WHERE `mc_question_choices`.`mc_question_id` = 24 AND `mc_question_choices`.`choices_id` = 72;
UPDATE `mc_question_choices` SET `mc_question_id` = 24,`choices_id` = 73 WHERE `mc_question_choices`.`mc_question_id` = 24 AND `mc_question_choices`.`choices_id` = 73;
UPDATE `mc_question_choices` SET `mc_question_id` = 25,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 25 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 25,`choices_id` = 73 WHERE `mc_question_choices`.`mc_question_id` = 25 AND `mc_question_choices`.`choices_id` = 73;
UPDATE `mc_question_choices` SET `mc_question_id` = 25,`choices_id` = 69 WHERE `mc_question_choices`.`mc_question_id` = 25 AND `mc_question_choices`.`choices_id` = 69;
UPDATE `mc_question_choices` SET `mc_question_id` = 25,`choices_id` = 64 WHERE `mc_question_choices`.`mc_question_id` = 25 AND `mc_question_choices`.`choices_id` = 64;
UPDATE `mc_question_choices` SET `mc_question_id` = 25,`choices_id` = 69 WHERE `mc_question_choices`.`mc_question_id` = 25 AND `mc_question_choices`.`choices_id` = 69;
UPDATE `mc_question_choices` SET `mc_question_id` = 25,`choices_id` = 73 WHERE `mc_question_choices`.`mc_question_id` = 25 AND `mc_question_choices`.`choices_id` = 73;
UPDATE `mc_question_choices` SET `mc_question_id` = 26,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 26 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 26,`choices_id` = 74 WHERE `mc_question_choices`.`mc_question_id` = 26 AND `mc_question_choices`.`choices_id` = 74;
UPDATE `mc_question_choices` SET `mc_question_id` = 26,`choices_id` = 75 WHERE `mc_question_choices`.`mc_question_id` = 26 AND `mc_question_choices`.`choices_id` = 75;
UPDATE `mc_question_choices` SET `mc_question_id` = 26,`choices_id` = 76 WHERE `mc_question_choices`.`mc_question_id` = 26 AND `mc_question_choices`.`choices_id` = 76;
UPDATE `mc_question_choices` SET `mc_question_id` = 26,`choices_id` = 72 WHERE `mc_question_choices`.`mc_question_id` = 26 AND `mc_question_choices`.`choices_id` = 72;
UPDATE `mc_question_choices` SET `mc_question_id` = 26,`choices_id` = 73 WHERE `mc_question_choices`.`mc_question_id` = 26 AND `mc_question_choices`.`choices_id` = 73;
UPDATE `mc_question_choices` SET `mc_question_id` = 26,`choices_id` = 77 WHERE `mc_question_choices`.`mc_question_id` = 26 AND `mc_question_choices`.`choices_id` = 77;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 74 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 74;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 75 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 75;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 76 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 76;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 78 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 78;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 79 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 79;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 80 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 80;
UPDATE `mc_question_choices` SET `mc_question_id` = 27,`choices_id` = 81 WHERE `mc_question_choices`.`mc_question_id` = 27 AND `mc_question_choices`.`choices_id` = 81;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 63 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 63;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 81 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 81;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 73 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 73;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 69 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 69;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 64 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 64;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 69 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 69;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 73 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 73;
UPDATE `mc_question_choices` SET `mc_question_id` = 28,`choices_id` = 81 WHERE `mc_question_choices`.`mc_question_id` = 28 AND `mc_question_choices`.`choices_id` = 81;

--
-- Daten für Tabelle `question`
--

UPDATE `question` SET `id` = 1,`max_length` = 2,`only_numbers` = b'1',`text` = 'Wie hoch ist Ihr gesamter Arbeitsaufwand für die Lehrveranstaltung(inkl. Vor- und Nachbereitung) in Stunden pro Woche?' WHERE `question`.`id` = 1;
UPDATE `question` SET `id` = 2,`max_length` = 1000,`only_numbers` = b'0',`text` = 'Was fanden Sie an der Lehrveranstaltung gut?' WHERE `question`.`id` = 2;
UPDATE `question` SET `id` = 3,`max_length` = 1000,`only_numbers` = b'0',`text` = 'Was fanden Sie an der Lehrveranstaltung weniger gut?' WHERE `question`.`id` = 3;
UPDATE `question` SET `id` = 4,`max_length` = 1000,`only_numbers` = b'0',`text` = 'Welche Verbesserungsvorschläge für die Lehrveranstaltung haben Sie?' WHERE `question`.`id` = 4;
UPDATE `question` SET `id` = 5,`max_length` = 1000,`only_numbers` = b'0',`text` = 'Was fanden Sie positiv?' WHERE `question`.`id` = 5;
UPDATE `question` SET `id` = 6,`max_length` = 1000,`only_numbers` = b'0',`text` = 'Was fanden Sie negativ?' WHERE `question`.`id` = 6;
UPDATE `question` SET `id` = 7,`max_length` = 1000,`only_numbers` = b'0',`text` = 'Welche Verbesserungsvorschläge würden Sie unterbreiten?' WHERE `question`.`id` = 7;
UPDATE `question` SET `id` = 8,`max_length` = 1000,`only_numbers` = b'0',`text` = 'Haben Sie weitere Anmerkungen?' WHERE `question`.`id` = 8;
UPDATE `question` SET `id` = 9,`max_length` = 1000,`only_numbers` = b'0',`text` = 'This shows the interface for a question which can be answered by text or with a photo.' WHERE `question`.`id` = 9;
UPDATE `question` SET `id` = 10,`max_length` = 1000,`only_numbers` = b'0',`text` = 'This shows how text questions behave when next to each other.' WHERE `question`.`id` = 10;

--
-- Daten für Tabelle `question_revision`
--

UPDATE `question_revision` SET `id` = 1,`name` = 'Wirtschaft',`text_questions_first` = b'1' WHERE `question_revision`.`id` = 1;
UPDATE `question_revision` SET `id` = 2,`name` = 'Informatik und Medien',`text_questions_first` = b'0' WHERE `question_revision`.`id` = 2;
UPDATE `question_revision` SET `id` = 3,`name` = 'Demo Evaluation',`text_questions_first` = b'0' WHERE `question_revision`.`id` = 3;

--
-- Daten für Tabelle `question_revision_choices`
--

UPDATE `question_revision_choices` SET `question_revision_id` = 1,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 1 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 1,`choices_id` = 2 WHERE `question_revision_choices`.`question_revision_id` = 1 AND `question_revision_choices`.`choices_id` = 2;
UPDATE `question_revision_choices` SET `question_revision_id` = 1,`choices_id` = 3 WHERE `question_revision_choices`.`question_revision_id` = 1 AND `question_revision_choices`.`choices_id` = 3;
UPDATE `question_revision_choices` SET `question_revision_id` = 1,`choices_id` = 4 WHERE `question_revision_choices`.`question_revision_id` = 1 AND `question_revision_choices`.`choices_id` = 4;
UPDATE `question_revision_choices` SET `question_revision_id` = 1,`choices_id` = 5 WHERE `question_revision_choices`.`question_revision_id` = 1 AND `question_revision_choices`.`choices_id` = 5;
UPDATE `question_revision_choices` SET `question_revision_id` = 1,`choices_id` = 6 WHERE `question_revision_choices`.`question_revision_id` = 1 AND `question_revision_choices`.`choices_id` = 6;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 7 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 7;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 8 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 8;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 9 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 9;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 10 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 10;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 11 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 11;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 12 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 12;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 13 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 13;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 14 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 14;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 15 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 15;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 16 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 16;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 17 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 17;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 18 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 18;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 19 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 19;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 20 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 20;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 21 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 21;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 22 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 22;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 23 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 23;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 24 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 24;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 25 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 25;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 26 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 26;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 27 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 27;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 28 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 28;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 29 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 29;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 30 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 30;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 31 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 31;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 32 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 32;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 33 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 33;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 34 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 34;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 35 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 35;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 36 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 36;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 7 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 7;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 37 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 37;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 29 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 29;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 10 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 10;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 31 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 31;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 7 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 7;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 37 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 37;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 29 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 29;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 10 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 10;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 31 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 31;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 7 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 7;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 8 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 8;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 9 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 9;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 10 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 10;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 11 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 11;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 2 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 2;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 3 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 3;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 4 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 4;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 5 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 5;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 6 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 6;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 2 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 2;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 3 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 3;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 4 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 4;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 5 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 5;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 6 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 6;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 38 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 38;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 39 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 39;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 24 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 24;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 40 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 40;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 41 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 41;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 42 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 42;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 43 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 43;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 19 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 19;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 44 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 44;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 45 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 45;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 46 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 46;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 47 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 47;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 48 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 48;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 49 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 49;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 50 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 50;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 51 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 51;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 46 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 46;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 2 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 2;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 3 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 3;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 4 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 4;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 5 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 5;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 6 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 6;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 52 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 52;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 53 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 53;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 54 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 54;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 55 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 55;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 56 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 56;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 57 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 57;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 58 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 58;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 59 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 59;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 60 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 60;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 61 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 61;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 62 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 62;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 1 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 1;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 2 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 2;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 3 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 3;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 4 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 4;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 5 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 5;
UPDATE `question_revision_choices` SET `question_revision_id` = 2,`choices_id` = 6 WHERE `question_revision_choices`.`question_revision_id` = 2 AND `question_revision_choices`.`choices_id` = 6;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 64 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 64;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 65 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 65;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 64 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 64;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 66 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 66;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 67 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 67;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 67 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 67;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 64 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 64;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 67 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 67;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 64 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 64;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 68 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 68;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 69 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 69;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 70 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 70;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 64 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 64;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 68 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 68;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 71 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 71;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 72 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 72;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 73 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 73;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 73 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 73;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 69 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 69;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 64 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 64;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 69 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 69;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 73 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 73;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 74 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 74;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 75 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 75;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 76 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 76;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 72 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 72;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 73 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 73;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 77 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 77;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 74 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 74;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 75 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 75;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 76 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 76;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 78 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 78;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 79 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 79;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 80 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 80;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 81 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 81;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 63 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 63;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 81 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 81;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 73 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 73;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 69 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 69;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 64 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 64;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 69 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 69;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 73 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 73;
UPDATE `question_revision_choices` SET `question_revision_id` = 3,`choices_id` = 81 WHERE `question_revision_choices`.`question_revision_id` = 3 AND `question_revision_choices`.`choices_id` = 81;

--
-- Daten für Tabelle `question_revision_mc_questions`
--

UPDATE `question_revision_mc_questions` SET `question_revision_id` = 1,`mc_questions_id` = 1 WHERE `question_revision_mc_questions`.`question_revision_id` = 1 AND `question_revision_mc_questions`.`mc_questions_id` = 1;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 2 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 2;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 3 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 3;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 4 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 4;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 5 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 5;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 6 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 6;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 7 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 7;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 8 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 8;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 9 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 9;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 10 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 10;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 11 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 11;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 12 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 12;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 13 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 13;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 14 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 14;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 15 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 15;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 16 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 16;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 17 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 17;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 18 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 18;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 2,`mc_questions_id` = 19 WHERE `question_revision_mc_questions`.`question_revision_id` = 2 AND `question_revision_mc_questions`.`mc_questions_id` = 19;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 20 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 20;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 21 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 21;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 22 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 22;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 23 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 23;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 24 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 24;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 25 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 25;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 26 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 26;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 27 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 27;
UPDATE `question_revision_mc_questions` SET `question_revision_id` = 3,`mc_questions_id` = 28 WHERE `question_revision_mc_questions`.`question_revision_id` = 3 AND `question_revision_mc_questions`.`mc_questions_id` = 28;

--
-- Daten für Tabelle `question_revision_questions`
--

UPDATE `question_revision_questions` SET `question_revision_id` = 1,`questions_id` = 1 WHERE `question_revision_questions`.`question_revision_id` = 1 AND `question_revision_questions`.`questions_id` = 1;
UPDATE `question_revision_questions` SET `question_revision_id` = 1,`questions_id` = 2 WHERE `question_revision_questions`.`question_revision_id` = 1 AND `question_revision_questions`.`questions_id` = 2;
UPDATE `question_revision_questions` SET `question_revision_id` = 1,`questions_id` = 3 WHERE `question_revision_questions`.`question_revision_id` = 1 AND `question_revision_questions`.`questions_id` = 3;
UPDATE `question_revision_questions` SET `question_revision_id` = 1,`questions_id` = 4 WHERE `question_revision_questions`.`question_revision_id` = 1 AND `question_revision_questions`.`questions_id` = 4;
UPDATE `question_revision_questions` SET `question_revision_id` = 2,`questions_id` = 5 WHERE `question_revision_questions`.`question_revision_id` = 2 AND `question_revision_questions`.`questions_id` = 5;
UPDATE `question_revision_questions` SET `question_revision_id` = 2,`questions_id` = 6 WHERE `question_revision_questions`.`question_revision_id` = 2 AND `question_revision_questions`.`questions_id` = 6;
UPDATE `question_revision_questions` SET `question_revision_id` = 2,`questions_id` = 7 WHERE `question_revision_questions`.`question_revision_id` = 2 AND `question_revision_questions`.`questions_id` = 7;
UPDATE `question_revision_questions` SET `question_revision_id` = 2,`questions_id` = 8 WHERE `question_revision_questions`.`question_revision_id` = 2 AND `question_revision_questions`.`questions_id` = 8;
UPDATE `question_revision_questions` SET `question_revision_id` = 3,`questions_id` = 9 WHERE `question_revision_questions`.`question_revision_id` = 3 AND `question_revision_questions`.`questions_id` = 9;
UPDATE `question_revision_questions` SET `question_revision_id` = 3,`questions_id` = 10 WHERE `question_revision_questions`.`question_revision_id` = 3 AND `question_revision_questions`.`questions_id` = 10;

--
-- Daten für Tabelle `study_path`
--

UPDATE `study_path` SET `id` = 1,`degree` = 0,`department` = 2,`name` = 'Wirtschaftsinformatik' WHERE `study_path`.`id` = 1;
UPDATE `study_path` SET `id` = 2,`degree` = 0,`department` = 2,`name` = 'Berufsbegleitender Bachelor Betriebswirtschaftslehre' WHERE `study_path`.`id` = 2;
UPDATE `study_path` SET `id` = 3,`degree` = 0,`department` = 2,`name` = 'Betriebswirtschaftslehre' WHERE `study_path`.`id` = 3;
UPDATE `study_path` SET `id` = 4,`degree` = 1,`department` = 2,`name` = 'Betriebswirtschaftslehre' WHERE `study_path`.`id` = 4;
UPDATE `study_path` SET `id` = 5,`degree` = 1,`department` = 2,`name` = 'Security Management' WHERE `study_path`.`id` = 5;
UPDATE `study_path` SET `id` = 6,`degree` = 1,`department` = 2,`name` = 'Technologie- und Innovationsmanagement' WHERE `study_path`.`id` = 6;
UPDATE `study_path` SET `id` = 7,`degree` = 1,`department` = 2,`name` = 'Wirtschaftsinformatik' WHERE `study_path`.`id` = 7;
UPDATE `study_path` SET `id` = 8,`degree` = 0,`department` = 0,`name` = 'Applied Computer Science' WHERE `study_path`.`id` = 8;
UPDATE `study_path` SET `id` = 9,`degree` = 0,`department` = 0,`name` = 'Informatik' WHERE `study_path`.`id` = 9;
UPDATE `study_path` SET `id` = 10,`degree` = 0,`department` = 0,`name` = 'Medizininformatik' WHERE `study_path`.`id` = 10;
UPDATE `study_path` SET `id` = 11,`degree` = 0,`department` = 0,`name` = 'Medieninformatik' WHERE `study_path`.`id` = 11;
UPDATE `study_path` SET `id` = 12,`degree` = 1,`department` = 0,`name` = 'Informatik' WHERE `study_path`.`id` = 12;
UPDATE `study_path` SET `id` = 13,`degree` = 1,`department` = 0,`name` = 'Digitale Medien' WHERE `study_path`.`id` = 13;
UPDATE `study_path` SET `id` = 14,`degree` = 1,`department` = 0,`name` = 'Medieninformatik' WHERE `study_path`.`id` = 14;

--
-- Daten für Tabelle `subject`
--

UPDATE `subject` SET `id` = 1,`degree` = 1,`department` = 2,`name` = 'Unternehmensführung' WHERE `subject`.`id` = 1;
UPDATE `subject` SET `id` = 2,`degree` = 1,`department` = 2,`name` = 'Wertorientiertes IT-Management' WHERE `subject`.`id` = 2;
UPDATE `subject` SET `id` = 3,`degree` = 1,`department` = 2,`name` = 'Theorien der Informatik' WHERE `subject`.`id` = 3;
UPDATE `subject` SET `id` = 4,`degree` = 1,`department` = 2,`name` = 'Advanced Software Engineering' WHERE `subject`.`id` = 4;
UPDATE `subject` SET `id` = 5,`degree` = 1,`department` = 2,`name` = 'Modellierung und Analyse von Prozessen' WHERE `subject`.`id` = 5;
UPDATE `subject` SET `id` = 6,`degree` = 0,`department` = 0,`name` = 'Mathematik I' WHERE `subject`.`id` = 6;
UPDATE `subject` SET `id` = 7,`degree` = 0,`department` = 0,`name` = 'Informatik und Logik' WHERE `subject`.`id` = 7;
UPDATE `subject` SET `id` = 8,`degree` = 0,`department` = 0,`name` = 'Mathematik II' WHERE `subject`.`id` = 8;
UPDATE `subject` SET `id` = 9,`degree` = 0,`department` = 0,`name` = 'Mathematik III' WHERE `subject`.`id` = 9;
UPDATE `subject` SET `id` = 10,`degree` = 0,`department` = 0,`name` = 'Alternative Programmierparadigmen' WHERE `subject`.`id` = 10;
UPDATE `subject` SET `id` = 11,`degree` = 0,`department` = 0,`name` = 'Mathematische Programmierung' WHERE `subject`.`id` = 11;
UPDATE `subject` SET `id` = 12,`degree` = 0,`department` = 0,`name` = 'Human-Computer Interaction' WHERE `subject`.`id` = 12;
UPDATE `subject` SET `id` = 13,`degree` = 0,`department` = 0,`name` = 'Grundlagen der Wissensverarbeitung' WHERE `subject`.`id` = 13;
UPDATE `subject` SET `id` = 14,`degree` = 0,`department` = 0,`name` = 'Autonome Mobile Systeme' WHERE `subject`.`id` = 14;
UPDATE `subject` SET `id` = 15,`degree` = 0,`department` = 0,`name` = 'Wissensbasierte Systeme in der Medizin' WHERE `subject`.`id` = 15;
UPDATE `subject` SET `id` = 16,`degree` = 0,`department` = 0,`name` = 'Künstliche Intelligenz' WHERE `subject`.`id` = 16;
UPDATE `subject` SET `id` = 17,`degree` = 1,`department` = 0,`name` = 'Mathematik' WHERE `subject`.`id` = 17;
UPDATE `subject` SET `id` = 18,`degree` = 1,`department` = 0,`name` = 'Künstliche Intelligenz' WHERE `subject`.`id` = 18;
UPDATE `subject` SET `id` = 19,`degree` = 1,`department` = 0,`name` = 'Einführung in Wissenschaftliches Arbeiten und Schreiben' WHERE `subject`.`id` = 19;
UPDATE `subject` SET `id` = 20,`degree` = 1,`department` = 0,`name` = 'Mobile Informationssysteme' WHERE `subject`.`id` = 20;

INSERT INTO `subject` (`id`, `degree`, `department`, `name`) VALUES
  (21, 0, 0, 'Dummy1') ON DUPLICATE KEY UPDATE `id` = 21,`degree` = 0, `department` = 0, `name` = 'Dummy1';

INSERT INTO `subject` (`id`, `degree`, `department`, `name`) VALUES
  (22, 0, 0, 'Dummy2') ON DUPLICATE KEY UPDATE `id` = 22,`degree` = 0, `department` = 0, `name` = 'Dummy2';

INSERT INTO `subject` (`id`, `degree`, `department`, `name`) VALUES
  (23, 0, 0, 'Dummy3') ON DUPLICATE KEY UPDATE `id` = 23,`degree` = 0, `department` = 0, `name` = 'Dummy3';

INSERT INTO `subject` (`id`, `degree`, `department`, `name`) VALUES
  (24, 0, 0, 'Mensch-Computer-Interaktion') ON DUPLICATE KEY UPDATE `id` = 24,`degree` = 0, `department` = 0, `name` = 'Mensch-Computer-Interaktion';

--
-- Daten für Tabelle `tutor`
--

UPDATE `tutor` SET `id` = 1,`department` = 2,`family_name` = 'Franz',`name` = 'Robert' WHERE `tutor`.`id` = 1;
UPDATE `tutor` SET `id` = 2,`department` = 2,`family_name` = 'Scheeg',`name` = 'Jochen' WHERE `tutor`.`id` = 2;
UPDATE `tutor` SET `id` = 3,`department` = 2,`family_name` = 'Hoeding',`name` = 'Michael' WHERE `tutor`.`id` = 3;
UPDATE `tutor` SET `id` = 4,`department` = 2,`family_name` = 'Pfister',`name` = 'Winfried' WHERE `tutor`.`id` = 4;
UPDATE `tutor` SET `id` = 5,`department` = 2,`family_name` = 'Wikarski',`name` = 'Dietmar' WHERE `tutor`.`id` = 5;
UPDATE `tutor` SET `id` = 6,`department` = 0,`family_name` = 'Socher',`name` = 'Rolf' WHERE `tutor`.`id` = 6;
UPDATE `tutor` SET `id` = 7,`department` = 0,`family_name` = 'Boersch',`name` = 'Ingo' WHERE `tutor`.`id` = 7;
UPDATE `tutor` SET `id` = 8,`department` = 0,`family_name` = 'Heinsohn',`name` = 'Jochen' WHERE `tutor`.`id` = 8;
UPDATE `tutor` SET `id` = 9,`department` = 0,`family_name` = 'Loose',`name` = 'Harald' WHERE `tutor`.`id` = 9;
UPDATE `tutor` SET `id` = 10,`department` = 0,`family_name` = 'Preuss',`name` = 'Thomas' WHERE `tutor`.`id` = 10;
UPDATE `tutor` SET `id` = 11,`department` = 0,`family_name` = 'Kindsmueller',`name` = 'Martin Christof' WHERE `tutor`.`id` = 11;
UPDATE `tutor` SET `id` = 12,`department` = 0,`family_name` = 'Buchholz',`name` = 'Sven' WHERE `tutor`.`id` = 12;
UPDATE `tutor` SET `id` = 13,`department` = 0,`family_name` = 'Schmidt',`name` = 'Gabriele' WHERE `tutor`.`id` = 13;
UPDATE `tutor` SET `id` = 14,`department` = 0,`family_name` = 'Busse',`name` = 'Susanne' WHERE `tutor`.`id` = 14;
UPDATE `tutor` SET `id` = 15,`department` = 0,`family_name` = 'Kim',`name` = 'Stefan' WHERE `tutor`.`id` = 15;
UPDATE `tutor` SET `id` = 16,`department` = 0,`family_name` = 'Syrjakow',`name` = 'Michael' WHERE `tutor`.`id` = 16;
UPDATE `tutor` SET `id` = 17,`department` = 0,`family_name` = 'Vielhauer',`name` = 'Claus' WHERE `tutor`.`id` = 17;
UPDATE `tutor` SET `id` = 18,`department` = 0,`family_name` = 'Homeister',`name` = 'Mathias' WHERE `tutor`.`id` = 18;
UPDATE `tutor` SET `id` = 19,`department` = 0,`family_name` = 'Kell',`name` = 'Gerald' WHERE `tutor`.`id` = 19;
UPDATE `tutor` SET `id` = 20,`department` = 0,`family_name` = 'Creuzburg',`name` = 'Reiner' WHERE `tutor`.`id` = 20;
UPDATE `tutor` SET `id` = 21,`department` = 0,`family_name` = 'Hasche',`name` = 'Eberhard' WHERE `tutor`.`id` = 21;
UPDATE `tutor` SET `id` = 22,`department` = 0,`family_name` = 'Urban',`name` = 'Alexander' WHERE `tutor`.`id` = 22;
UPDATE `tutor` SET `id` = 23,`department` = 0,`family_name` = 'Schafföner',`name` = 'Martin' WHERE `tutor`.`id` = 23;
UPDATE `tutor` SET `id` = 24,`department` = 0,`family_name` = 'Beck',`name` = 'Eberhard' WHERE `tutor`.`id` = 24;
UPDATE `tutor` SET `id` = 25,`department` = 0,`family_name` = 'Schrader',`name` = 'Thomas' WHERE `tutor`.`id` = 25;
UPDATE `tutor` SET `id` = 26,`department` = 0,`family_name` = 'Jänicke',`name` = 'Karl-Heinz' WHERE `tutor`.`id` = 26;

INSERT INTO `tutor` (`id`, `department`, `family_name`, `name`) VALUES
  (27, 2, 'Hausmann', 'Dietmar') ON DUPLICATE KEY UPDATE `id` = 27,`department` = 2, `family_name` = 'Hausmann', `name` = 'Dietmar';
INSERT INTO `tutor` (`id`, `department`, `family_name`, `name`) VALUES
  (28, 2, 'Sens', 'Katrin') ON DUPLICATE KEY UPDATE `id` = 28,`department` = 2,`family_name` = 'Sens',`name` = 'Katrin';
INSERT INTO `tutor` (`id`, `department`, `family_name`, `name`) VALUES
  (29, 0, 'Developer', 'Account') ON DUPLICATE KEY UPDATE `id` = 29,`department` = 0,`family_name` = 'Developer',`name` = 'Account';


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
