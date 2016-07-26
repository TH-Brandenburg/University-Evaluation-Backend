-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 17. Mai 2016 um 21:45
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `caeb`
--



--
-- Daten für Tabelle `choice`
--

INSERT INTO `choice` (`id`, `grade`, `text`) VALUES
(1, 0, 'k.A'),
(2, 1, 'immer'),
(3, 2, 'oft'),
(4, 3, 'mittel'),
(5, 4, 'selten'),
(6, 5, 'nie'),
(7, 1, 'sehr groß'),
(8, 2, 'groß'),
(9, 4, 'klein'),
(10, 5, 'sehr klein'),
(11, 1, 'zu hoch'),
(12, 2, 'hoch'),
(13, 3, 'optimal'),
(14, 4, 'niedrig'),
(15, 5, 'zu niedrig'),
(16, 1, 'stimme zu'),
(17, 2, 'stimme eher zu'),
(18, 3, 'unentschieden'),
(19, 4, 'stimme eher nicht zu'),
(20, 5, 'stimme nicht zu'),
(21, 1, 'sehr gut'),
(22, 2, 'gut'),
(23, 3, 'befriedigend'),
(24, 4, 'ausreichend'),
(25, 5, 'ungenügend'),
(26, 1, 'sehr viel Stoff'),
(27, 2, 'viel Stoff'),
(28, 4, 'wenig Stoff'),
(29, 5, 'sehr wenig Stoff'),
(30, 1, 'sehr viel Übung'),
(31, 2, 'viel Übung'),
(32, 4, 'wenig Übung'),
(33, 5, 'zu wenig Übung'),
(34, 1, 'sehr viel Medien'),
(35, 2, 'viel Medien'),
(36, 4, 'wenig Medien'),
(37, 5, 'sehr wenig Medien'),
(38, 0, 'keine Angabe'),
(39, 1, 'ja, immer'),
(40, 2, 'sehr häufig'),
(41, 3, 'oft'),
(42, 1, 'ja, sehr'),
(43, 2, 'durchaus'),
(44, 3, 'mittelmäßig'),
(45, 4, 'eher nicht'),
(46, 5, 'überhaupt nicht'),
(47, 3, 'zu hoch'),
(48, 1, 'angemessen'),
(49, 2, 'niedrig'),
(50, 3, 'zu niedrig'),
(51, 1, 'sehr laut, sehr deutlich'),
(52, 2, 'laut, präzise'),
(53, 3, 'verständlich'),
(54, 4, 'leise, eher undeutlich'),
(55, 5, 'zu leise, undeutlich'),
(56, 1, 'ja, hervorragend'),
(57, 2, 'ja, fast immer'),
(58, 3, 'in der Regel ja'),
(59, 4, 'manchmal klappt es'),
(60, 5, 'nein, nie'),
(61, 1, 'ja, immer Dialog mit Studenten'),
(62, 2, 'überwiegend Dialog'),
(63, 3, 'gute Mischung'),
(64, 4, 'zu oft Monolog'),
(65, 5, 'nein, nur Monolog'),
(66, 2, 'ja, wenn Zeit war'),
(67, 1, 'sehr klar'),
(68, 2, 'gut strukturiert'),
(69, 4, 'sprunghaft'),
(70, 5, 'Roter Faden fehlte'),
(71, 5, 'zu viel Stoff in zuwenig Zeit'),
(72, 3, 'viel Stoff'),
(73, 3, 'wenig Stoff'),
(74, 5, 'zu viel Zeit für zuwenig Stoff'),
(75, 0, 'keine Ü/L vorhanden'),
(76, 2, 'stimme weitgehend zu'),
(77, 4, 'stimme weitgehend nicht zu'),
(78, 5, 'viel zu viele Medien eingesetzt'),
(79, 3, 'etwas zu viele Medien eingesetzt'),
(80, 1, 'Medieneinsatz adäquat'),
(81, 3, 'etwas zu wenige Medien eingesetzt'),
(82, 5, 'viel zu wenig Medien eingesetzt'),
(83, 0, 'weiss ich nicht'),
(84, 1, 'habe sehr viel gelernt'),
(85, 2, 'habe viel gelernt'),
(86, 3, 'habe etwas gelernt'),
(87, 4, 'habe wenig gelernt'),
(88, 5, 'habe sehr wenig gelernt'),
(89, 0, 'No comment'),
(90, 1, 'Positive textAnswer'),
(91, 2, 'Negative textAnswer'),
(92, 2, 'Neutral textAnswer'),
(93, 3, 'Negative textAnswer'),
(94, 2, 'Slightly positive textAnswer'),
(95, 3, 'Slightly negative textAnswer'),
(96, 4, 'Negative textAnswer'),
(97, 3, 'neutral textAnswer'),
(98, 4, 'Slightly negative textAnswer'),
(99, 5, 'Negative textAnswer'),
(100, 1, 'Very positive textAnswer'),
(101, 2, 'positive textAnswer'),
(102, 3, 'Slightly positive textAnswer'),
(103, 6, 'Very negative textAnswer'),
(104, 4, 'Neutral textAnswer'),
(105, 5, 'Slightly negative textAnswer'),
(106, 6, 'Negative textAnswer'),
(107, 7, 'Very negative textAnswer');

-- --------------------------------------------------------

-- --------------------------------------------------------


--
-- Daten für Tabelle `question`
--
--
INSERT INTO `question` (`id`, `adhoc_question`, `question_position`, `text`, `type`) VALUES
  (10, b'0',10,'Die Stoffpräsentation der LV war stets klar und gut strukturiert.',0),
  (12, b'0',12,'Die Übung war nützlich. Sie war sehr gut geeignet, die Vorlesungsinhalte zu verdeutlichen und zu vertiefen.',0),
  (5, b'0',5,'Er/Sie kann schwierige Sachverhalte verständlich erklären.',0),
  (24, b'0',24,'Ging der/die Dozent(in) auf Fragen innerhalb der LV ein?',0),
  (6, b'0',6,'Ging er/sie auf Fragen innerhalb der LV ein?',0),
  (1, b'0',1,'Haben Sie die LV regelmäßig besucht?',0),
  (18, b'0',18,'Haben Sie die Veranstaltung regelmässig besucht?',0),
  (2, b'0',2,'Haben Sie Interesse an dem Fach?',0),
  (19, b'0',19,'Haben Sie Interesse an diesem Fach?',0),
  (33, b'0',33, 'Interface for question with 2 + 1 possible answers.',0),
  (34, b'0',34,'Interface for question with 3 + 1 possible answers.',0),
  (35, b'0',35,'Interface for question with 3 + 1 possible answers. The best answer placed in the middle.',0),
  (36, b'0',36,'Interface for question with 4 + 1 possible answers.',0),
  (37, b'0',37,'Interface for question with 5 + 1 possible answers.',0),
  (38, b'0',38,'Interface for question with 5 + 1 possible answers. The best answer placed in the middle.',0),
  (39, b'0',39,'Interface for question with 6 + 1 possible answers.',0),
  (41, b'0',41,'Interface for question with 7 + 1 possible answers. The best answer placed in the middle.',0),
  (40, b'0',40,'Interface for question with 7 + 1 possible answers.',0),
  (22, b'0',22,'Kann er/sie schwierige Sachverhalte verständlich erklären?',0),
  (4, b'0',4,'Seine/Ihre Sprache und Ausdrucksweise sind stets klar verständlich.',0),
  (23, b'0',23,'Versuchte der/die Dozent(in) festzustellen, ob die Studenten der LV folgen können?',0),
  (26, b'0',26,'War der/die Dozent(in) gut vorbereitet?',0),
  (25, b'0',25,'War er/sie auch ausserhalb der LV zu diesen Themen ansprechbar?',0),
  (7, b'0',7,'War er/sie stets gut auf die LV vorbereitet?',0),
  (8, b'0',8,'Welche Gesamtnote geben Sie dem Dozenten/der Dozentin?',0),
  (27, b'0',27,'Welche Gesamtnote geben Sie dem/der Dozenten(in)?',0),
  (9, b'0',9,'Welche Gesamtnote geben Sie den Lehrunterlagen?',0),
  (32, b'0',32,'Welche Gesamtnote geben Sie der Lehrveranstaltung?',0),
  (16, b'0',16,'Welche Gesamtnote geben Sie der LV?',0),
  (30, b'0',30,'Wie beurteilen Sie den Medieneinsatz (Beamer, Tafel, Overhead-Projektor, usw.)?',0),
  (14, b'0',14,'Wie beurteilen Sie den Medieneinsatz der LV? (Beamer, Tafel, Overheadprojektor, Mobil-Telefone...)',0),
  (13, b'0',13,'Wie beurteilen Sie den Übungsanteil im Vergleich zum Vorlesungsanteil?',0),
  (29, b'0',29,'Wie beurteilen Sie die Ausstattung des Übungs- oder Laborraumes?',0),
  (17, b'0',17,'Wie beurteilen Sie die Lehrveranstaltung insgesamt? Auf einer Skala von 1 bis 5; 1 = sehr gut, 5 = nicht gut',0),
  (31, b'0',31,'Wie beurteilen Sie Ihren persönlichen Lernerfolg in dieser Lehrveranstaltung?',0),
  (15, b'0',15,'Wie beurteilen Sie Ihren persönlichen Lernerfolg in dieser LV?',0),
  (20, b'0',20,'Wie empfanden Sie das Niveau der Lehrveranstaltung?',0),
  (3, b'0',3,'Wie fanden Sie das Niveau der Lehrveranstaltung?',0),
  (11, b'0',11,'Wie war die Stoffmenge im Verhältnis zur verfügbaren Zeit?',0),
  (28, b'0',28,'Wie war die Vorgehensweise und Stoffpräsentation in der LV?',0),
  (21, b'0',21,'Wie waren Sprache und Ausdrucksweise des Dozenten/der Dozentin?',0);


--
-- Daten für Tabelle `single_choice_question`
--
--
INSERT INTO `single_choice_question` (`question_id`) VALUES
  (10),
  (12),
  (5),
  (24),
  (6),
  (1),
  (18),
  (2),
  (19),
  (33),
  (34),
  (35),
  (36),
  (37),
  (38),
  (39),
  (41),
  (40),
  (22),
  (4),
  (23),
  (26),
  (25),
  (7),
  (8),
  (27),
  (9),
  (32),
  (16),
  (30),
  (14),
  (13),
  (29),
  (17),
  (31),
  (15),
  (20),
  (3),
  (11),
  (28),
  (21);

--
-- Daten für Tabelle `question` ...Textquestions
-- id's ab 42
INSERT INTO `question` (`id`, `adhoc_question`, `question_position`, `text`, `type`) VALUES
  (42,b'0',42,'Was fanden Sie positiv?',1),
  (43,b'0',43,'Was fanden Sie negativ?',1),
  (44,b'0',44,'Welche Verbesserungsvorschläge würden Sie machen?',1),
  (45,b'0',45,'Weitere Anmerkungen?',1),
  (46,b'0',46,'Wie hoch ist Ihr gesamter Arbeitsaufwand für die Lehrveranstaltung(inkl. Vor- und Nachbereitung) in Stunden pro Woche?',1),
  (47,b'0',47,'Was fanden Sie an der Lehrveranstaltung gut?',1),
  (48,b'0',48,'Was fanden Sie an der Lehrveranstaltung weniger gut?',1),
  (49,b'0',49,'Welche Verbesserungsvorschläge für die Lehrveranstaltung haben Sie?',1),
  (50,b'0',50,'Welche Verbesserungsvorschläge würden Sie unterbreiten?',1),
  (51,b'0',51,'Haben Sie weitere Anmerkungen?',1),
  (52,b'0',52,'This shows the interface for a question which can be answered by text or with a photo.',1),
  (53,b'0',53,'This shows how text questions behave when next to each other.',1);


--
-- Daten für Tabelle `text_question` ...Textquestions
-- id's ab 42

INSERT INTO `text_question` (`question_id`, `max_length`, `only_numbers`) VALUES
(42, 1000, b'0'),
(43, 1000, b'0'),
(44, 1000, b'0'),
(45, 1000, b'0'),
(46, 2, b'1'),
(47, 1000, b'0'),
(48, 1000, b'0'),
(49, 1000, b'0'),
(50, 1000, b'0'),
(51, 1000, b'0'),
(52, 1000, b'0'),
(53, 1000, b'0');


-- VERALTET
-- Daten für Tabelle `mc_question`
--
--
-- INSERT INTO `mc_question` (`id`, `text`) VALUES
-- (10, 'Die Stoffpräsentation der LV war stets klar und gut strukturiert.'),
-- (12, 'Die Übung war nützlich. Sie war sehr gut geeignet, die Vorlesungsinhalte zu verdeutlichen und zu vertiefen.'),
-- (5, 'Er/Sie kann schwierige Sachverhalte verständlich erklären.'),
-- (24, 'Ging der/die Dozent(in) auf Fragen innerhalb der LV ein?'),
-- (6, 'Ging er/sie auf Fragen innerhalb der LV ein?'),
-- (1, 'Haben Sie die LV regelmäßig besucht?'),
-- (18, 'Haben Sie die Veranstaltung regelmässig besucht?'),
-- (2, 'Haben Sie Interesse an dem Fach?'),
-- (19, 'Haben Sie Interesse an diesem Fach?'),
-- (33, 'Interface for question with 2 + 1 possible textAnswers.'),
-- (34, 'Interface for question with 3 + 1 possible textAnswers.'),
-- (35, 'Interface for question with 3 + 1 possible textAnswers. The best textAnswer placed in the middle.'),
-- (36, 'Interface for question with 4 + 1 possible textAnswers.'),
-- (37, 'Interface for question with 5 + 1 possible textAnswers.'),
-- (38, 'Interface for question with 5 + 1 possible textAnswers. The best textAnswer placed in the middle.'),
-- (39, 'Interface for question with 6 + 1 possible textAnswers.'),
-- (41, 'Interface for question with 7 + 1 possible textAnswers. The best textAnswer placed in the middle.'),
-- (40, 'Interface for question with 7 + 1 possible textAnswers.'),
-- (22, 'Kann er/sie schwierige Sachverhalte verständlich erklären?'),
-- (4, 'Seine/Ihre Sprache und Ausdrucksweise sind stets klar verständlich.'),
-- (23, 'Versuchte der/die Dozent(in) festzustellen, ob die Studenten der LV folgen können?'),
-- (26, 'War der/die Dozent(in) gut vorbereitet?'),
-- (25, 'War er/sie auch ausserhalb der LV zu diesen Themen ansprechbar?'),
-- (7, 'War er/sie stets gut auf die LV vorbereitet?'),
-- (8, 'Welche Gesamtnote geben Sie dem Dozenten/der Dozentin?'),
-- (27, 'Welche Gesamtnote geben Sie dem/der Dozenten(in)?'),
-- (9, 'Welche Gesamtnote geben Sie den Lehrunterlagen?'),
-- (32, 'Welche Gesamtnote geben Sie der Lehrveranstaltung?'),
-- (16, 'Welche Gesamtnote geben Sie der LV?'),
-- (30, 'Wie beurteilen Sie den Medieneinsatz (Beamer, Tafel, Overhead-Projektor, usw.)?'),
-- (14, 'Wie beurteilen Sie den Medieneinsatz der LV? (Beamer, Tafel, Overheadprojektor, Mobil-Telefone...)'),
-- (13, 'Wie beurteilen Sie den Übungsanteil im Vergleich zum Vorlesungsanteil?'),
-- (29, 'Wie beurteilen Sie die Ausstattung des Übungs- oder Laborraumes?'),
-- (17, 'Wie beurteilen Sie die Lehrveranstaltung insgesamt? Auf einer Skala von 1 bis 5; 1 = sehr gut, 5 = nicht gut'),
-- (31, 'Wie beurteilen Sie Ihren persönlichen Lernerfolg in dieser Lehrveranstaltung?'),
-- (15, 'Wie beurteilen Sie Ihren persönlichen Lernerfolg in dieser LV?'),
-- (20, 'Wie empfanden Sie das Niveau der Lehrveranstaltung?'),
-- (3, 'Wie fanden Sie das Niveau der Lehrveranstaltung?'),
-- (11, 'Wie war die Stoffmenge im Verhältnis zur verfügbaren Zeit?'),
-- (28, 'Wie war die Vorgehensweise und Stoffpräsentation in der LV?'),
-- (21, 'Wie waren Sprache und Ausdrucksweise des Dozenten/der Dozentin?');

-- --------------------------------------------------------


--
-- Daten für Tabelle `single_choice_question_choices`
--

--
INSERT INTO `single_choice_question_choices` (`single_choice_question_QUESTION_ID`, `choices_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 1),
(2, 7),
(2, 8),
(2, 4),
(2, 9),
(2, 10),
(3, 1),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(4, 1),
(4, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20),
(5, 1),
(5, 16),
(5, 17),
(5, 18),
(5, 19),
(5, 20),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(6, 5),
(6, 6),
(7, 1),
(7, 2),
(7, 3),
(7, 4),
(7, 5),
(7, 6),
(8, 1),
(8, 21),
(8, 22),
(8, 23),
(8, 24),
(8, 25),
(9, 1),
(9, 21),
(9, 22),
(9, 23),
(9, 24),
(9, 25),
(10, 1),
(10, 16),
(10, 17),
(10, 18),
(10, 19),
(10, 20),
(11, 1),
(11, 26),
(11, 27),
(11, 13),
(11, 28),
(11, 29),
(12, 1),
(12, 16),
(12, 17),
(12, 18),
(12, 19),
(12, 20),
(13, 1),
(13, 30),
(13, 31),
(13, 13),
(13, 32),
(13, 33),
(14, 1),
(14, 34),
(14, 35),
(14, 13),
(14, 36),
(14, 37),
(15, 1),
(15, 7),
(15, 8),
(15, 4),
(15, 9),
(15, 10),
(16, 1),
(16, 21),
(16, 22),
(16, 23),
(16, 24),
(16, 25),
(17, 38),
(17, 21),
(17, 22),
(17, 23),
(17, 24),
(17, 25),
(18, 38),
(18, 39),
(18, 40),
(18, 41),
(18, 5),
(18, 6),
(19, 38),
(19, 42),
(19, 43),
(19, 44),
(19, 45),
(19, 46),
(20, 38),
(20, 47),
(20, 12),
(20, 48),
(20, 49),
(20, 50),
(21, 38),
(21, 51),
(21, 52),
(21, 53),
(21, 54),
(21, 55),
(22, 38),
(22, 56),
(22, 57),
(22, 58),
(22, 59),
(22, 60),
(23, 38),
(23, 61),
(23, 62),
(23, 63),
(23, 64),
(23, 65),
(24, 38),
(24, 39),
(24, 66),
(24, 58),
(24, 5),
(24, 60),
(25, 38),
(25, 39),
(25, 66),
(25, 58),
(25, 5),
(25, 60),
(26, 38),
(26, 39),
(26, 40),
(26, 41),
(26, 5),
(26, 6),
(27, 38),
(27, 21),
(27, 22),
(27, 23),
(27, 24),
(27, 25),
(28, 38),
(28, 67),
(28, 68),
(28, 53),
(28, 69),
(28, 70),
(29, 75),
(29, 21),
(29, 22),
(29, 23),
(29, 24),
(29, 25),
(30, 38),
(30, 78),
(30, 79),
(30, 80),
(30, 81),
(30, 82),
(31, 83),
(31, 84),
(31, 85),
(31, 86),
(31, 87),
(31, 88),
(32, 38),
(32, 21),
(32, 22),
(32, 23),
(32, 24),
(32, 25),
(33, 89),
(33, 90),
(33, 91),
(34, 89),
(34, 90),
(34, 92),
(34, 93),
(35, 89),
(35, 93),
(35, 90),
(35, 93),
(36, 89),
(36, 90),
(36, 94),
(36, 95),
(36, 96),
(37, 89),
(37, 90),
(37, 94),
(37, 97),
(37, 98),
(37, 99),
(38, 89),
(38, 99),
(38, 95),
(38, 90),
(38, 95),
(38, 99),
(39, 89),
(39, 100),
(39, 101),
(39, 102),
(39, 98),
(39, 99),
(39, 103),
(40, 89),
(40, 100),
(40, 101),
(40, 102),
(40, 104),
(40, 105),
(40, 106),
(40, 107),
(41, 89),
(41, 107),
(41, 99),
(41, 95),
(41, 90),
(41, 95),
(41, 99),
(41, 107);

-- --------------------------------------------------------


-- VERALTET
-- Daten für Tabelle `question`
--
--
-- INSERT INTO `question` (`id`, `max_length`, `only_numbers`, `text`) VALUES
-- (1, 1000, b'0', 'Was fanden Sie positiv?'),
-- (2, 1000, b'0', 'Was fanden Sie negativ?'),
-- (3, 1000, b'0', 'Welche Verbesserungsvorschläge würden Sie machen?'),
-- (4, 1000, b'0', 'Weitere Anmerkungen?'),
-- (5, 2, b'1', 'Wie hoch ist Ihr gesamter Arbeitsaufwand für die Lehrveranstaltung(inkl. Vor- und Nachbereitung) in Stunden pro Woche?'),
-- (6, 1000, b'0', 'Was fanden Sie an der Lehrveranstaltung gut?'),
-- (7, 1000, b'0', 'Was fanden Sie an der Lehrveranstaltung weniger gut?'),
-- (8, 1000, b'0', 'Welche Verbesserungsvorschläge für die Lehrveranstaltung haben Sie?'),
-- (9, 1000, b'0', 'Welche Verbesserungsvorschläge würden Sie unterbreiten?'),
-- (10, 1000, b'0', 'Haben Sie weitere Anmerkungen?'),
-- (11, 1000, b'0', 'This shows the interface for a question which can be answered by text or with a photo.'),
-- (12, 1000, b'0', 'This shows how text questions behave when next to each other.');

-- --------------------------------------------------------


--
-- Daten für Tabelle `question_revision`
--

INSERT INTO `question_revision` (`id`, `name`, `text_questions_first`) VALUES
(1, 'Computer Science and Media v2.1 (2016-05-16)', b'0');
-- Nur ein Fragebogen wird als Vorlage in die DB geschieben(2, 'Business Administration', b'1'),
-- Nur ein Fragebogen wird als Vorlage in die DB geschieben(3, 'Computer Science and Media', b'0'),
-- Nur ein Fragebogen wird als Vorlage in die DB geschieben(4, 'Demo Evaluation', b'0');

-- --------------------------------------------------------

--
-- Daten für Tabelle `question_revision_choices`
--

INSERT INTO `question_revision_choices` (`question_revision_id`, `choices_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 1),
(1, 7),
(1, 8),
(1, 4),
(1, 9),
(1, 10),
(1, 1),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 1),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 1),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 1),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 1),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 1),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 1),
(1, 26),
(1, 27),
(1, 13),
(1, 28),
(1, 29),
(1, 1),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 1),
(1, 30),
(1, 31),
(1, 13),
(1, 32),
(1, 33),
(1, 1),
(1, 34),
(1, 35),
(1, 13),
(1, 36),
(1, 37),
(1, 1),
(1, 7),
(1, 8),
(1, 4),
(1, 9),
(1, 10),
(1, 1),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25);
-- (2, 38),
-- (2, 21),
-- (2, 22),
-- (2, 23),
-- (2, 24),
-- (2, 25),
-- (3, 38),
-- (3, 39),
-- (3, 40),
-- (3, 41),
-- (3, 5),
-- (3, 6),
-- (3, 38),
-- (3, 42),
-- (3, 43),
-- (3, 44),
-- (3, 45),
-- (3, 46),
-- (3, 38),
-- (3, 47),
-- (3, 12),
-- (3, 48),
-- (3, 49),
-- (3, 50),
-- (3, 38),
-- (3, 51),
-- (3, 52),
-- (3, 53),
-- (3, 54),
-- (3, 55),
-- (3, 38),
-- (3, 56),
-- (3, 57),
-- (3, 58),
-- (3, 59),
-- (3, 60),
-- (3, 38),
-- (3, 61),
-- (3, 62),
-- (3, 63),
-- (3, 64),
-- (3, 65),
-- (3, 38),
-- (3, 39),
-- (3, 66),
-- (3, 58),
-- (3, 5),
-- (3, 60),
-- (3, 38),
-- (3, 39),
-- (3, 66),
-- (3, 58),
-- (3, 5),
-- (3, 60),
-- (3, 38),
-- (3, 39),
-- (3, 40),
-- (3, 41),
-- (3, 5),
-- (3, 6),
-- (3, 38),
-- (3, 21),
-- (3, 22),
-- (3, 23),
-- (3, 24),
-- (3, 25),
-- (3, 38),
-- (3, 21),
-- (3, 22),
-- (3, 23),
-- (3, 24),
-- (3, 25),
-- (3, 38),
-- (3, 67),
-- (3, 68),
-- (3, 53),
-- (3, 69),
-- (3, 70),
-- (3, 38),
-- (3, 71),
-- (3, 72),
-- (3, 48),
-- (3, 73),
-- (3, 74),
-- (3, 75),
-- (3, 16),
-- (3, 76),
-- (3, 18),
-- (3, 77),
-- (3, 20),
-- (3, 75),
-- (3, 21),
-- (3, 22),
-- (3, 23),
-- (3, 24),
-- (3, 25),
-- (3, 38),
-- (3, 78),
-- (3, 79),
-- (3, 80),
-- (3, 81),
-- (3, 82),
-- (3, 83),
-- (3, 84),
-- (3, 85),
-- (3, 86),
-- (3, 87),
-- (3, 88),
-- (3, 38),
-- (3, 21),
-- (3, 22),
-- (3, 23),
-- (3, 24),
-- (3, 25),
-- (4, 89),
-- (4, 90),
-- (4, 91),
-- (4, 89),
-- (4, 90),
-- (4, 92),
-- (4, 93),
-- (4, 89),
-- (4, 93),
-- (4, 90),
-- (4, 93),
-- (4, 89),
-- (4, 90),
-- (4, 94),
-- (4, 95),
-- (4, 96),
-- (4, 89),
-- (4, 90),
-- (4, 94),
-- (4, 97),
-- (4, 98),
-- (4, 99),
-- (4, 89),
-- (4, 99),
-- (4, 95),
-- (4, 90),
-- (4, 95),
-- (4, 99),
-- (4, 89),
-- (4, 100),
-- (4, 101),
-- (4, 102),
-- (4, 98),
-- (4, 99),
-- (4, 103),
-- (4, 89),
-- (4, 100),
-- (4, 101),
-- (4, 102),
-- (4, 104),
-- (4, 105),
-- (4, 106),
-- (4, 107),
-- (4, 89),
-- (4, 107),
-- (4, 99),
-- (4, 95),
-- (4, 90),
-- (4, 95),
-- (4, 99),
-- (4, 107);
--
-- -- --------------------------------------------------------
--
--
--
-- --
-- -- Daten für Tabelle `question_revision_questions`
-- --

INSERT INTO `question_revision_questions` (`question_revision_id`, `questions_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16);
-- (2, 17),
-- (3, 18),
-- (3, 19),
-- (3, 20),
-- (3, 21),
-- (3, 22),
-- (3, 23),
-- (3, 24),
-- (3, 25),
-- (3, 26),
-- (3, 27),
-- (3, 9),
-- (3, 28),
-- (3, 11),
-- (3, 12),
-- (3, 29),
-- (3, 30),
-- (3, 31),
-- (3, 32),
-- (4, 33),
-- (4, 34),
-- (4, 35),
-- (4, 36),
-- (4, 37),
-- (4, 38),
-- (4, 39),
-- (4, 40),
-- (4, 41);
--
-- -- --------------------------------------------------------
--
--
-- --
-- -- Daten für Tabelle `question_revision_questions`
-- --

-- INSERT INTO `question_revision_questions` (`question_revision_id`, `questions_id`) VALUES
-- (3, 1),
-- (3, 2);
--
-- -- --------------------------------------------------------
--
-- --
-- -- Daten für Tabelle `study_path`
-- --
--
 INSERT INTO `study_path` (`id`, `degree`, `department`, `name`) VALUES
 (1, 0, 2, 'Wirtschaftsinformatik'),
 (2, 0, 2, 'Berufsbegleitender Bachelor Betriebswirtschaftslehre'),
 (3, 0, 2, 'Betriebswirtschaftslehre'),
 (4, 1, 2, 'Betriebswirtschaftslehre'),
 (5, 1, 2, 'Security Management'),
 (6, 1, 2, 'Technologie- und Innovationsmanagement'),
 (7, 1, 2, 'Wirtschaftsinformatik'),
 (8, 0, 0, 'Applied Computer Science'),
 (9, 0, 0, 'Informatik'),
 (10, 0, 0, 'Medizininformatik'),
 (11, 0, 0, 'Medieninformatik'),
 (12, 1, 0, 'Informatik'),
 (13, 1, 0, 'Digitale Medien'),
 (14, 1, 0, 'Medieninformatik');
--
-- -- --------------------------------------------------------
--
-- --
-- -- Daten für Tabelle `subject`
-- --
--
 INSERT INTO `subject` (`id`, `degree`, `department`, `name`) VALUES
 (1, 1, 2, 'Unternehmensführung'),
 (2, 1, 2, 'Wertorientiertes IT-Management'),
 (3, 1, 2, 'Theorien der Informatik'),
 (4, 1, 2, 'Advanced Software Engineering'),
 (5, 1, 2, 'Modellierung und Analyse von Prozessoren'),
 (6, 0, 0, 'Mathematik I'),
 (7, 0, 0, 'Informatik und Logik'),
 (8, 0, 0, 'Mathematik II'),
 (9, 0, 0, 'Mathematik III'),
 (10, 0, 0, 'Alternative Programmierparadigmen'),
 (11, 0, 0, 'Mathematische Programmierung'),
 (12, 0, 0, 'Human-Computer Interaction'),
 (13, 0, 0, 'Mensch-Computer-Interaktion'),
 (14, 0, 0, 'Grundlagen der Wissensverarbeitung'),
 (15, 0, 0, 'Autonome Mobile Systeme'),
 (16, 0, 0, 'Wissensbasierte Systeme in der Medizin'),
 (17, 0, 0, 'Künstliche Intelligenz'),
 (18, 0, 0, 'Dummy1'),
 (19, 0, 0, 'Dummy2'),
 (20, 0, 0, 'Dummy3'),
 (21, 1, 0, 'Mathematik'),
 (22, 1, 0, 'Künstliche Intelligenz'),
 (23, 1, 0, 'Einführung in Wissenschaftliches Arbeiten und Schreiben'),
 (24, 1, 0, 'Mobile Informationssysteme');
--
-- -- --------------------------------------------------------
--

--
-- Daten für Tabelle `tutor`
--

INSERT INTO `tutor` (`id`, `department`, `family_name`, `name`) VALUES
(1, 2, 'Franz', 'Robert'),
(2, 2, 'Scheeg', 'Jochen'),
(3, 2, 'Hoeding', 'Michael'),
(4, 2, 'Pfister', 'Winfried'),
(5, 2, 'Wikarski', 'Dietmar'),
(6, 2, 'Hausmann', 'Dietmar'),
(7, 2, 'Sens', 'Katrin'),
(8, 0, 'Socher', 'Rolf'),
(9, 0, 'Boersch', 'Ingo'),
(10, 0, 'Heinsohn', 'Jochen'),
(11, 0, 'Loose', 'Harald'),
(12, 0, 'Preuss', 'Thomas'),
(13, 0, 'Kindsmueller', 'Martin Christof'),
(14, 0, 'Buchholz', 'Sven'),
(15, 0, 'Schmidt', 'Gabriele'),
(16, 0, 'Busse', 'Susanne'),
(17, 0, 'Kim', 'Stefan'),
(18, 0, 'Syrjakow', 'Michael'),
(19, 0, 'Vielhauer', 'Claus'),
(20, 0, 'Homeister', 'Mathias'),
(21, 0, 'Kell', 'Gerald'),
(22, 0, 'Creuzburg', 'Reiner'),
(23, 0, 'Hasche', 'Eberhard'),
(24, 0, 'Urban', 'Alexander'),
(25, 0, 'Schafföner', 'Martin'),
(26, 0, 'Beck', 'Eberhard'),
(27, 0, 'Schrader', 'Thomas'),
(28, 0, 'Jänicke', 'Karl-Heinz'),
(29, 0, 'Developer', 'Account');

-- --------------------------------------------------------