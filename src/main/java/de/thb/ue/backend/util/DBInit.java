/*
 * Copyright 2016 Max Gregor, Sebastian Müller
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.thb.ue.backend.util;

import de.thb.ue.backend.model.*;
import de.thb.ue.dto.util.Department;

import java.util.ArrayList;
import java.util.List;


public class DBInit {

    /******************************************************************
     * Demo Questionnaire - For showing off Client capabilities
     ******************************************************************/

    public static final boolean TEXT_QUESTIONS_FIRST_DEMO = false;
    /******************************************************************
     * Business administration Department related Questions
     ******************************************************************/

    public static final boolean TEXT_QUESTIONS_FIRST_BUSINESS_ADMINISTRATION = true;
    /******************************************************************
     * Computer Science and Media Department related Questions
     ******************************************************************/

    public static final boolean TEXT_QUESTIONS_FIRST_COMPUTER_SCIENCE_V1 = false;
    public static final boolean TEXT_QUESTIONS_FIRST_COMPUTER_SCIENCE_V2 = false;

    /******************************************************************
     * Merged Lists
     ******************************************************************/


    public static List<StudyPath> getAllStudyPaths() {
        List<StudyPath> out = new ArrayList<>();
        out.addAll(getBusinessAdministrationStudyPaths());
        out.addAll(getComputerScienceStudyPaths());
        return out;
    }

    public static List<Subject> getAllSubjects() {
        List<Subject> out = new ArrayList<>();
        out.addAll(getBusinessAdministrationSubjects());
        out.addAll(getComputerScienceSubjects());
        return out;
    }

    public static List<Tutor> getAllTutors() {
        List<Tutor> out = new ArrayList<>();
        out.addAll(getBusinessAdministrationTutors());
        out.addAll(getComputerScienceTutors());
        return out;
    }

    public static List<Question> getDemoQuestions() {
        return new ArrayList<Question>() {{
            add(new Question("This shows the interface for a question which can be answered by text or with a photo.", false, 1000));
            add(new Question("This shows how text questions behave when next to each other.", false, 1000));
        }};
    }

    public static List<MCQuestion> getDemoMCQuestions() {
        List<MCQuestion> out = new ArrayList<>();
        out.add(new MCQuestion("Interface for question with 2 + 1 possible answers.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Positive answer", (short) 1));
            add(new Choice("Negative answer", (short) 2));
        }}));

        out.add(new MCQuestion("Interface for question with 3 + 1 possible answers.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Positive answer", (short) 1));
            add(new Choice("Neutral answer", (short) 2));
            add(new Choice("Negative answer", (short) 3));
        }}));

        out.add(new MCQuestion("Interface for question with 3 + 1 possible answers. The best answer placed in the middle.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Negative answer", (short) 3));
            add(new Choice("Positive answer", (short) 1));
            add(new Choice("Negative answer", (short) 3));
        }}));

        out.add(new MCQuestion("Interface for question with 4 + 1 possible answers.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Positive answer", (short) 1));
            add(new Choice("Slightly positive answer", (short) 2));
            add(new Choice("Slightly negative answer", (short) 3));
            add(new Choice("Negative answer", (short) 4));
        }}));

        out.add(new MCQuestion("Interface for question with 5 + 1 possible answers.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("positive answer", (short) 1));
            add(new Choice("Slightly positive answer", (short) 2));
            add(new Choice("neutral answer", (short) 3));
            add(new Choice("Slightly negative answer", (short) 4));
            add(new Choice("Negative answer", (short) 5));
        }}));

        out.add(new MCQuestion("Interface for question with 5 + 1 possible answers. The best answer placed in the middle.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Negative answer", (short) 5));
            add(new Choice("Slightly negative answer", (short) 3));
            add(new Choice("positive answer", (short) 1));
            add(new Choice("Slightly negative answer", (short) 3));
            add(new Choice("Negative answer", (short) 5));
        }}));

        out.add(new MCQuestion("Interface for question with 6 + 1 possible answers.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Very positive answer", (short) 1));
            add(new Choice("positive answer", (short) 2));
            add(new Choice("Slightly positive answer", (short) 3));
            add(new Choice("Slightly negative answer", (short) 4));
            add(new Choice("Negative answer", (short) 5));
            add(new Choice("Very negative answer", (short) 6));
        }}));

        out.add(new MCQuestion("Interface for question with 7 + 1 possible answers.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Very positive answer", (short) 1));
            add(new Choice("positive answer", (short) 2));
            add(new Choice("Slightly positive answer", (short) 3));
            add(new Choice("Neutral answer", (short) 4));
            add(new Choice("Slightly negative answer", (short) 5));
            add(new Choice("Negative answer", (short) 6));
            add(new Choice("Very negative answer", (short) 7));
        }}));


        out.add(new MCQuestion("Interface for question with 6 + 1 possible answers. The best answer placed in the middle.", new ArrayList<Choice>() {{
            add(new Choice("No comment", (short) 0));
            add(new Choice("Very Negative answer", (short) 7));
            add(new Choice("Negative answer", (short) 5));
            add(new Choice("Slightly negative answer", (short) 3));
            add(new Choice("positive answer", (short) 1));
            add(new Choice("Slightly negative answer", (short) 3));
            add(new Choice("Negative answer", (short) 5));
            add(new Choice("Very Negative answer", (short) 7));
        }}));
        return out;
    }

    public static List<Question> getBusinessAdministrationQuestions() {
        return new ArrayList<Question>() {{
            add(new Question("Wie hoch ist Ihr gesamter Arbeitsaufwand für die Lehrveranstaltung(inkl. Vor- und Nachbereitung) in Stunden pro Woche?", true, 2));
            add(new Question("Was fanden Sie an der Lehrveranstaltung gut?", false, 1000));
            add(new Question("Was fanden Sie an der Lehrveranstaltung weniger gut?", false, 1000));
            add(new Question("Welche Verbesserungsvorschläge für die Lehrveranstaltung haben Sie?", false, 1000));
        }};
    }

    public static List<MCQuestion> getBusinessAdministrationMCQuestions() {
        List<MCQuestion> out = new ArrayList<>();
        out.add(new MCQuestion("Wie beurteilen Sie die Lehrveranstaltung insgesamt? Auf einer Skala von 1 bis 5; 1 = sehr gut, 5 = nicht gut", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));

        return out;
    }

    public static List<Tutor> getBusinessAdministrationTutors() {
        List<Tutor> out = new ArrayList<>();
        out.add(new Tutor("franz", "Robert", "Franz", Department.BUSINESS_ADMINISTRATION, null));
        out.add(new Tutor("scheeg", "Jochen", "Scheeg", Department.BUSINESS_ADMINISTRATION, null));
        out.add(new Tutor("hoeding", "Michael", "Hoeding", Department.BUSINESS_ADMINISTRATION, null));
        out.add(new Tutor("pfister", "Winfried", "Pfister", Department.BUSINESS_ADMINISTRATION, null));
        out.add(new Tutor("wikarski", "Dietmar", "Wikarski", Department.BUSINESS_ADMINISTRATION, null));
        out.add(new Tutor("hausmann", "Dietmar", "Hausmann", Department.BUSINESS_ADMINISTRATION, null));
        out.add(new Tutor("sens", "Katrin", "Sens", Department.BUSINESS_ADMINISTRATION, null));


        return out;
    }

    public static List<Subject> getBusinessAdministrationSubjects() {
        List<Subject> out = new ArrayList<>();

         /*
        * ***************************************
        *                 Master                *
        * ***************************************
        * */

        // 1. Semester
        out.add(new Subject("Unternehmensführung", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        out.add(new Subject("Wertorientiertes IT-Management", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        out.add(new Subject("Theorien der Informatik", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        out.add(new Subject("Advanced Software Engineering", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        out.add(new Subject("Modellierung und Analyse von Prozessoren", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        return out;
    }

    public static List<StudyPath> getBusinessAdministrationStudyPaths() {
        List<StudyPath> out = new ArrayList<>();
        out.add(new StudyPath("Wirtschaftsinformatik", Department.BUSINESS_ADMINISTRATION, Degree.Bachelor));
        out.add(new StudyPath("Berufsbegleitender Bachelor Betriebswirtschaftslehre", Department.BUSINESS_ADMINISTRATION, Degree.Bachelor));
        out.add(new StudyPath("Betriebswirtschaftslehre", Department.BUSINESS_ADMINISTRATION, Degree.Bachelor));

        out.add(new StudyPath("Betriebswirtschaftslehre", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        out.add(new StudyPath("Security Management", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        out.add(new StudyPath("Technologie- und Innovationsmanagement", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        out.add(new StudyPath("Wirtschaftsinformatik", Department.BUSINESS_ADMINISTRATION, Degree.Master));
        return out;
    }

    public static List<Question> getComputerScienceQuestions() {
        return new ArrayList<Question>() {{
            add(new Question("Was fanden Sie positiv?", false, 1000));
            add(new Question("Was fanden Sie negativ?", false, 1000));
            add(new Question("Welche Verbesserungsvorschläge würden Sie unterbreiten?", false, 1000));
            add(new Question("Haben Sie weitere Anmerkungen?", false, 1000));
        }};
    }

    public static List<MCQuestion> getComputerScienceMCQuestions() {

        List<MCQuestion> out = new ArrayList<>();
        //Studentin
        out.add(new MCQuestion("Haben Sie die Veranstaltung regelmässig besucht?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("ja, immer", (short) 1));
            add(new Choice("sehr häufig", (short) 2));
            add(new Choice("oft", (short) 3));
            add(new Choice("selten", (short) 4));
            add(new Choice("nie", (short) 5));
        }}));
        out.add(new MCQuestion("Haben Sie Interesse an diesem Fach?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("ja, sehr", (short) 1));
            add(new Choice("durchaus", (short) 2));
            add(new Choice("mittelmäßig", (short) 3));
            add(new Choice("eher nicht", (short) 4));
            add(new Choice("überhaupt nicht", (short) 5));
        }}));
        //TODO sort
        out.add(new MCQuestion("Wie empfanden Sie das Niveau der Lehrveranstaltung?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("zu hoch", (short) 3));
            add(new Choice("hoch", (short) 2));
            add(new Choice("angemessen", (short) 1));
            add(new Choice("niedrig", (short) 2));
            add(new Choice("zu niedrig", (short) 3));
        }}));
        //Dozentin
        out.add(new MCQuestion("Wie waren Sprache und Ausdrucksweise des Dozenten/der Dozentin?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("sehr laut, sehr deutlich", (short) 1));
            add(new Choice("laut, präzise", (short) 2));
            add(new Choice("verständlich", (short) 3));
            add(new Choice("leise, eher undeutlich", (short) 4));
            add(new Choice("zu leise, undeutlich", (short) 5));
        }}));
        out.add(new MCQuestion("Kann er/sie schwierige Sachverhalte verständlich erklären?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("ja, hervorragend", (short) 1));
            add(new Choice("ja, fast immer", (short) 2));
            add(new Choice("in der Regel ja", (short) 3));
            add(new Choice("manchmal klappt es", (short) 4));
            add(new Choice("nein, nie", (short) 5));
        }}));
        //TODO sort + frage DozentIn
        out.add(new MCQuestion("Versuchte der/die Dozent(in) festzustellen, ob die Studenten der LV folgen können?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("ja, immer Dialog mit Studenten", (short) 1));
            add(new Choice("überwiegend Dialog", (short) 2));
            add(new Choice("gute Mischung", (short) 3));
            add(new Choice("zu oft Monolog", (short) 4));
            add(new Choice("nein, nur Monolog", (short) 5));
        }}));
        out.add(new MCQuestion("Ging der/die Dozent(in) auf Fragen innerhalb der LV ein?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("ja, immer", (short) 1));
            add(new Choice("ja, wenn Zeit war", (short) 2));
            add(new Choice("in der Regel ja", (short) 3));
            add(new Choice("selten", (short) 4));
            add(new Choice("nein, nie", (short) 5));
        }}));
        out.add(new MCQuestion("War er/sie auch ausserhalb der LV zu diesen Themen ansprechbar?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("ja, immer", (short) 1));
            add(new Choice("ja, wenn Zeit war", (short) 2));
            add(new Choice("in der Regel ja", (short) 3));
            add(new Choice("selten", (short) 4));
            add(new Choice("nein, nie", (short) 5));
        }}));
        out.add(new MCQuestion("War der/die Dozent(in) gut vorbereitet?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("ja, immer", (short) 1));
            add(new Choice("sehr häufig", (short) 2));
            add(new Choice("oft", (short) 3));
            add(new Choice("selten", (short) 4));
            add(new Choice("nie", (short) 5));
        }}));
        out.add(new MCQuestion("Welche Gesamtnote geben Sie dem/der Dozenten(in)?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));
        //Lehrunterlagen
        out.add(new MCQuestion("Welche Gesamtnote geben Sie den Lehrunterlagen?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));
        //Lehrveranstaltung
        out.add(new MCQuestion("Wie war die Vorgehensweise und Stoffpräsentation in der LV?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("sehr klar", (short) 1));
            add(new Choice("gut strukturiert", (short) 2));
            add(new Choice("verständlich", (short) 3));
            add(new Choice("sprunghaft", (short) 4));
            add(new Choice("Roter Faden fehlte", (short) 5));
        }}));
        //TODO sort
        out.add(new MCQuestion("Wie war die Stoffmenge im Verhältnis zur verfügbaren Zeit?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("zu viel Stoff in zuwenig Zeit", (short) 5));
            add(new Choice("viel Stoff", (short) 3));
            add(new Choice("angemessen", (short) 1));
            add(new Choice("wenig Stoff", (short) 3));
            add(new Choice("zu viel Zeit für zuwenig Stoff", (short) 5));
        }}));
        //TODO sort
        out.add(new MCQuestion("Die Übung war nützlich. Sie war sehr gut geeignet, die Vorlesungsinhalte zu verdeutlichen und zu vertiefen.", new ArrayList<Choice>() {{
            add(new Choice("keine Ü/L vorhanden", (short) 0));
            add(new Choice("stimme zu", (short) 1));
            add(new Choice("stimme weitgehend zu", (short) 2));
            add(new Choice("unentschieden", (short) 3));
            add(new Choice("stimme weitgehend nicht zu", (short) 4));
            add(new Choice("stimme nicht zu", (short) 5));
        }}));
        out.add(new MCQuestion("Wie beurteilen Sie die Ausstattung des Übungs- oder Laborraumes?", new ArrayList<Choice>() {{
            add(new Choice("keine Ü/L vorhanden", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));
        //TODO sort
        out.add(new MCQuestion("Wie beurteilen Sie den Medieneinsatz (Beamer, Tafel, Overhead-Projektor, usw.)?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("viel zu viele Medien eingesetzt"/*"zuviele Medien eingesetzt"*/, (short) 5));
            add(new Choice("etwas zu viele Medien eingesetzt"/*"reichhaltiger Medieneinsatz"*/, (short) 3));
            add(new Choice("Medieneinsatz adäquat"/*"o.k., dem Stoff angemessen"*/, (short) 1));
            add(new Choice("etwas zu wenige Medien eingesetzt"/*"wenig Medien genutzt"*/, (short) 3)); // genutzt genutzt ;)
            add(new Choice("viel zu wenig Medien eingesetzt"/*"keine Medien"*/, (short) 5));
        }}));
        out.add(new MCQuestion("Wie beurteilen Sie Ihren persönlichen Lernerfolg in dieser Lehrveranstaltung?", new ArrayList<Choice>() {{
            add(new Choice("weiss ich nicht", (short) 0));
            add(new Choice("habe sehr viel gelernt", (short) 1));
            add(new Choice("habe viel gelernt", (short) 2));
            add(new Choice("habe etwas gelernt", (short) 3));
            add(new Choice("habe wenig gelernt", (short) 4));
            add(new Choice("habe sehr wenig gelernt", (short) 5));
        }}));
        out.add(new MCQuestion("Welche Gesamtnote geben Sie der Lehrveranstaltung?", new ArrayList<Choice>() {{
            add(new Choice("keine Angabe", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));
        return out;
    }

    /*
    * Kindsmueller/Socher approved! Update of old questionnaire
     */

    public static List<MCQuestion> getComputerScienceMCQuestionsV2(){
        List<MCQuestion> out = new ArrayList<>();
        //Studentin
        out.add(new MCQuestion("Haben Sie die LV regelmäßig besucht?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("immer", (short) 1));
            add(new Choice("oft", (short) 2));
            add(new Choice("mittel", (short) 3));
            add(new Choice("selten", (short) 4));
            add(new Choice("nie", (short) 5));
        }}));

        out.add(new MCQuestion("Haben Sie Interesse an dem Fach?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr groß", (short) 1));
            add(new Choice("groß", (short) 2));
            add(new Choice("mittel", (short) 3));
            add(new Choice("klein", (short) 4));
            add(new Choice("sehr klein", (short) 5));
        }}));

        out.add(new MCQuestion("Wie fanden Sie das Niveau der Lehrveranstaltung?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("zu hoch", (short) 1));
            add(new Choice("hoch", (short) 2));
            add(new Choice("optimal", (short) 3));
            add(new Choice("niedrig", (short) 4));
            add(new Choice("zu niedrig", (short) 5));
        }}));

        out.add(new MCQuestion("Seine/Ihre Sprache und Ausdrucksweise sind stets klar verständlich.", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("stimme zu", (short) 1));
            add(new Choice("stimme eher zu", (short) 2));
            add(new Choice("unentschieden", (short) 3));
            add(new Choice("stimme eher nicht zu", (short) 4));
            add(new Choice("stimme nicht zu", (short) 5));
        }}));

        out.add(new MCQuestion("Er/Sie kann schwierige Sachverhalte verständlich erklären.", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("stimme zu", (short) 1));
            add(new Choice("stimme eher zu", (short) 2));
            add(new Choice("unentschieden", (short) 3));
            add(new Choice("stimme eher nicht zu", (short) 4));
            add(new Choice("stimme nicht zu", (short) 5));
        }}));

        out.add(new MCQuestion("Ging er/sie auf Fragen innerhalb der LV ein?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("immer", (short) 1));
            add(new Choice("oft", (short) 2));
            add(new Choice("mittel", (short) 3));
            add(new Choice("selten", (short) 4));
            add(new Choice("nie", (short) 5));
        }}));

        out.add(new MCQuestion("War er/sie stets gut auf die LV vorbereitet?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("immer", (short) 1));
            add(new Choice("oft", (short) 2));
            add(new Choice("mittel", (short) 3));
            add(new Choice("selten", (short) 4));
            add(new Choice("nie", (short) 5));
        }}));

        out.add(new MCQuestion("Welche Gesamtnote geben Sie dem Dozenten/der Dozentin?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));

        out.add(new MCQuestion("Welche Gesamtnote geben Sie den Lehrunterlagen?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));

        out.add(new MCQuestion("Die Stoffpräsentation der LV war stets klar und gut strukturiert.", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("stimme zu", (short) 1));
            add(new Choice("stimme eher zu", (short) 2));
            add(new Choice("unentschieden", (short) 3));
            add(new Choice("stimme eher nicht zu", (short) 4));
            add(new Choice("stimme nicht zu", (short) 5));
        }}));

        out.add(new MCQuestion("Wie war die Stoffmenge im Verhältnis zur verfügbaren Zeit?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr viel Stoff", (short) 1));
            add(new Choice("viel Stoff", (short) 2));
            add(new Choice("optimal", (short) 3));
            add(new Choice("wenig Stoff", (short) 4));
            add(new Choice("sehr wenig Stoff", (short) 5));
        }}));

        out.add(new MCQuestion("Die Übung war nützlich. Sie war sehr gut geeignet, die Vorlesungsinhalte zu verdeutlichen und zu vertiefen.", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("stimme zu", (short) 1));
            add(new Choice("stimme eher zu", (short) 2));
            add(new Choice("unentschieden", (short) 3));
            add(new Choice("stimme eher nicht zu", (short) 4));
            add(new Choice("stimme nicht zu", (short) 5));
        }}));

        out.add(new MCQuestion("Wie beurteilen Sie den Übungsanteil im Vergleich zum Vorlesungsanteil?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr viel Übung", (short) 1));
            add(new Choice("viel Übung", (short) 2));
            add(new Choice("optimal", (short) 3));
            add(new Choice("wenig Übung", (short) 4));
            add(new Choice("zu wenig Übung", (short) 5));
        }}));

        out.add(new MCQuestion("Wie beurteilen Sie den Medieneinsatz der LV? (Beamer, Tafel, Overheadprojektor, Mobil-Telefone...)", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr viel Medien", (short) 1));
            add(new Choice("viel Medien", (short) 2));
            add(new Choice("optimal", (short) 3));
            add(new Choice("wenig Medien", (short) 4));
            add(new Choice("sehr wenig Medien", (short) 5));
        }}));

        out.add(new MCQuestion("Wie beurteilen Sie Ihren persönlichen Lernerfolg in dieser LV?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr groß", (short) 1));
            add(new Choice("groß", (short) 2));
            add(new Choice("mittel", (short) 3));
            add(new Choice("klein", (short) 4));
            add(new Choice("sehr klein", (short) 5));
        }}));

        out.add(new MCQuestion("Welche Gesamtnote geben Sie der LV?", new ArrayList<Choice>() {{
            add(new Choice("k.A", (short) 0));
            add(new Choice("sehr gut", (short) 1));
            add(new Choice("gut", (short) 2));
            add(new Choice("befriedigend", (short) 3));
            add(new Choice("ausreichend", (short) 4));
            add(new Choice("ungenügend", (short) 5));
        }}));

        return out;
    }

    public static  List<Question> getComputerScienceQuestionsV2(){
        return new ArrayList<Question>() {{
            add(new Question("Was fanden Sie positiv?", false, 1000));
            add(new Question("Was fanden Sie negativ?", false, 1000));
            add(new Question("Welche Verbesserungsvorschläge würden Sie machen?", false, 1000));
            add(new Question("Weitere Anmerkungen?", false, 1000));
        }};
    }


    public static List<Tutor> getComputerScienceTutors() {
        List<Tutor> out = new ArrayList<>();
        out.add(new Tutor("socher", "Rolf", "Socher", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("boersc", "Ingo", "Boersch", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("heinsohn", "Jochen", "Heinsohn", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("loose", "Harald", "Loose", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("preuss", "Thomas", "Preuss", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("kindsmueller", "Martin Christof", "Kindsmueller", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("buchholz", "Sven", "Buchholz", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("schmidt", "Gabriele", "Schmidt", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("busse", "Susanne", "Busse", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("kim", "Stefan", "Kim", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("syrjakow", "Michael", "Syrjakow", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("vielhaauer", "Claus", "Vielhauer", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("homeister", "Mathias", "Homeister", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("kell", "Gerald", "Kell", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("creutzburg", "Reiner", "Creuzburg", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("hasche", "Eberhard", "Hasche", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("urban", "Alexander", "Urban", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("schaffoe", "Martin", "Schafföner", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("beck", "Eberhard", "Beck", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("schrader", "Thomas", "Schrader", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("jaenicke", "Karl-Heinz", "Jänicke", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("developer", "Account", "Developer", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("bartz", "Bartz", "Tobias", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("anderst", "Anders", "Toni", Department.COMPUTER_SCIENCE_MEDIA, null));
        out.add(new Tutor("muellerb", "Müller", "Ben", Department.COMPUTER_SCIENCE_MEDIA, null));

        return out;
    }

    public static List<Subject> getComputerScienceSubjects() {
        List<Subject> out = new ArrayList<>();


         /*
        * ***************************************
        *                 Bachelor              *
        * ***************************************
        * */

        // 1. Semester
        out.add(new Subject("Mathematik I", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Programmieren I", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Englisch I", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Algorithmen und Datenstrukturen", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Technische Informatik und Medientechnik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Informatik und Logik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));

        // 2. Semester
//        out.add(new Subject("Englisch II", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Programmieren II", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Mathematik II", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Betriebssysteme / Webcomputing", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Formale Sprachen / Automatentheorie", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Mediengestaltung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Rechnerorganisation", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));

        // 3.Semester
        out.add(new Subject("Mathematik III", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Programmieren III", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Betriebssysteme / Rechnernetze", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Grundlagen der Sicherhet", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Datenbanken I", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Alternative Programmierparadigmen", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Grundlagen Audio und Video", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Mikrocomputertechnik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Computeranimation", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Grundlagen der digitalen Signal- und Bildverarbeitung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Mathematische Programmierung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Human-Computer Interaction", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Mensch-Computer-Interaktion", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));

        // 4. Semester
//        out.add(new Subject("Recht", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Software Engineering", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("C# und .NET-Programmierung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Digitale Signal- und Bildverarbeitung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Grundlagen der Wissensverarbeitung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("International Media Camp", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Maschinenorientierte Programmmierung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Rechnerarchitektur", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Datenvisualisierung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Digitales Filmen", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Grundlagen interaktiver Medien", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("JEE-Technologien und Anwendungen", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Objektorientierte Skriptsprachen", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Sicherheit verteilter Systeme", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Communicative Competence", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Grundlagen des Projektmanagement", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Medienrecht", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Werbefilm - Psychologie und Tricktechnik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));

        // 5.Semester
//        out.add(new Subject("BWL", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Informatik und Gesellschaft", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Autonome Mobile Systeme", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Eingebettete Systeme", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Medientechnik Audio", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Multimedia Produktoin", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Systementwurf", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Cross-Media-Publishing", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Medienpsychologie", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Mobile Anwendungen und Systeme", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Software Qualität", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Wissensbasierte Systeme in der Medizin", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Anwendungen in der IT-Sicherheit", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Interactive Storytelling", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Management von Eye-Traching-Daten", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Mobile Applikationen", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Responsive WebApplications", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Multimedia Applications", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Mobile Anwendungen, Kinect, Ganganalyse und Biofeedback", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Künstliche Intelligenz", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("Applikationen mit Raspberrry Pi und STM32", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
//        out.add(new Subject("360° Movies", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));

        // stuff

        out.add(new Subject("Dummy1",  Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Dummy2",  Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new Subject("Dummy3",  Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));

        /*
        * ***************************************
        *                   Master              *
        * ***************************************
        * */

        // 1. Semester Informatik
        out.add(new Subject("Mathematik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Projektmanagement", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Softwarearchitektur und Qualitätssicherung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Prozessmodellierung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Systemintegration", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("IT- und Medienforensik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Telemedizinische Dienste", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Mathematik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));

        // 2. Semester Informatik
//        out.add(new Subject("Datenbanken und Informationssysteme", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
        out.add(new Subject("Künstliche Intelligenz", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
        out.add(new Subject("Einführung in Wissenschaftliches Arbeiten und Schreiben", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Sicherheits- und Qualitätsmanagement", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
        out.add(new Subject("Mobile Informationssysteme", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Mediensicherheit", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Biosignalanalyse", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Applied Mobile Programming", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Data Mining", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));

        // 3. Semester Informatik
//        out.add(new Subject("Digitale Medien", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Informatiktheorie", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Wissenschaftliches Arbeiten und Schreiben", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Soft- und Hardwareauswahl", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Modellgetriebene Softwareentwicklung", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Kryptographie und Netwerksicherheit", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
//        out.add(new Subject("Quantencomputer", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));

        return out;
    }

    public static List<StudyPath> getComputerScienceStudyPaths() {
        List<StudyPath> out = new ArrayList<>();
        out.add(new StudyPath("Applied Computer Science", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new StudyPath("Informatik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new StudyPath("Medizininformatik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new StudyPath("Medieninformatik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Bachelor));
        out.add(new StudyPath("Informatik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
        out.add(new StudyPath("Digitale Medien", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));
        out.add(new StudyPath("Medieninformatik", Department.COMPUTER_SCIENCE_MEDIA, Degree.Master));

        return out;
    }

}
