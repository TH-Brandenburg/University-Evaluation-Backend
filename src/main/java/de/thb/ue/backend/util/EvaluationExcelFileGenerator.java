/*
 * Copyright 2016 Sebastian Müller
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.LocalDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import de.thb.ue.backend.model.AggregatedMCAnswer;
import de.thb.ue.backend.model.Answer;
import de.thb.ue.backend.model.Choice;
import de.thb.ue.backend.model.MCAnswer;
import de.thb.ue.backend.model.Vote;
import lombok.NonNull;

public class EvaluationExcelFileGenerator {

//    @Value("${working-directory:}") //TODO
    private String workingDirectoryPath = "media";

    // configure cell styles
    private CellStyle commonStyle = null;
    private CellStyle headerStyle = null;
    private CellStyle questionStyle = null;
    private CellStyle positiveStyle = null;
    private CellStyle positiveHeaderStyle = null;
    private CellStyle negativeStyle = null;
    private CellStyle negativeHeaderStyle = null;
    private CellStyle improvementStyle = null;
    private CellStyle improvementHeaderStyle = null;
    private CellStyle furtherStyle = null;
    private CellStyle furtherHeaderStyle = null;

    private final String evaluationUID;
    private final List<AggregatedMCAnswer> aggregatedMCAnswers;
    private final List<String> tutors;
    private final List<String> mcQuestionTexts;
    private final List<String> textualQuestionTexts;
    private final List<Vote> studentVotes;
    private final String subject;
    private final SemesterType semesterType;
    private final LocalDateTime dateOfEvaluation;
    private final int numberStudentsAll;
    private final int numberStudentsVoted;

    public EvaluationExcelFileGenerator(String evaluationUID, List<AggregatedMCAnswer> aggregatedMCAnswers,
                                        List<String> tutors, List<String> mcQuestionTexts, List<String> textualQuestionTexts,
                                        List<Vote> studentVotes, String subject, SemesterType semesterType,
                                        LocalDateTime dateOfEvaluation, int numberStudentsAll, int numberStudentsVoted) {
        this.evaluationUID = evaluationUID;
        this.aggregatedMCAnswers = aggregatedMCAnswers;
        this.tutors = tutors;
        this.mcQuestionTexts = mcQuestionTexts;
        this.textualQuestionTexts = textualQuestionTexts;
        this.studentVotes = studentVotes;
        this.subject = subject;
        this.semesterType = semesterType;
        this.dateOfEvaluation = dateOfEvaluation;
        this.numberStudentsAll = numberStudentsAll;
        this.numberStudentsVoted = numberStudentsVoted;
    }

    public void generateExcelFile() {

        Row row;
        Cell cell;
        int yOffset = 1;
        File workingDirectory = new File((workingDirectoryPath.isEmpty() ? "" : (workingDirectoryPath + File.separatorChar)) + evaluationUID);
        if (!workingDirectory.exists()) {
            workingDirectory.mkdir();
        }
        File file = new File(workingDirectory,  "auswertung.xls");

        try {
            FileOutputStream out = new FileOutputStream(file);
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Evaluation");

            // configure cell styles
            configureCellStyles(wb);


            /*
            * **********************************
            * begin formatting document
            * **********************************
            * */


            //construct first row of infopanel
            yOffset = constructInfoPanelRow("Lehrveranstaltung", subject, numberStudentsAll, yOffset, wb, sheet,
                    InfoPanelBorderStyles.topLeftCorner, InfoPanelBorderStyles.top, InfoPanelBorderStyles.topRightCorner);

            //construct second row of infopanel
            yOffset = constructInfoPanelRow("Semester", semesterType == SemesterType.WINTER ? "Winter" : "Sommer", numberStudentsAll, yOffset, wb, sheet,
                    InfoPanelBorderStyles.left, InfoPanelBorderStyles.none, InfoPanelBorderStyles.right);


            //construct third row of infopanel
            StringBuilder tutors = new StringBuilder();
            for (int i = 0; i < this.tutors.size(); i++) {
                if (i + 1 < this.tutors.size()) {
                    tutors.append(this.tutors.get(i)).append(", ");
                } else {
                    tutors.append(this.tutors.get(i));
                }
            }

            yOffset = constructInfoPanelRow("Lehrende(r)", tutors.toString(), numberStudentsAll, yOffset, wb, sheet,
                    InfoPanelBorderStyles.left, InfoPanelBorderStyles.none, InfoPanelBorderStyles.right);


            //construct fourth row of infopanel
            yOffset = constructInfoPanelRow("Datum der Befragung", dateOfEvaluation.toString("dd.MM.yy HH:mm"), numberStudentsAll, yOffset, wb, sheet,
                    InfoPanelBorderStyles.left, InfoPanelBorderStyles.none, InfoPanelBorderStyles.right);


            //construct fifth row of infopanel
            yOffset = constructInfoPanelRow("Anzahl der Teilnehmer", Integer.toString(numberStudentsAll), numberStudentsAll, yOffset, wb, sheet,
                    InfoPanelBorderStyles.left, InfoPanelBorderStyles.none, InfoPanelBorderStyles.right);

            //construct sixth row of infopanel () last
            yOffset = constructInfoPanelRow("Anzahl der ausgefüllten Fragebögen", Integer.toString(numberStudentsVoted), numberStudentsAll, yOffset, wb, sheet,
                    InfoPanelBorderStyles.bottomLeftCorner, InfoPanelBorderStyles.bottom, InfoPanelBorderStyles.bottomRightCorner);


            //begin construction of evaluationPanel
            yOffset++;
            row = sheet.createRow(yOffset);
            cell = row.createCell(1, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Frage");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(2, Cell.CELL_TYPE_STRING);
            cell.setCellValue("MW");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(3, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Ifd NR.");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(4);
            cell.setCellStyle(commonStyle);

            // add count of valid evaluations (how many students voted) (horizontal)
            for (int i = 0; i < numberStudentsVoted; i++) {
                cell = row.createCell(i + 5);
                cell.setCellValue(i + 1);
                sheet.setColumnWidth(cell.getColumnIndex(), 4 * 256);
                cell.setCellStyle(headerStyle);
            }

            // get letter of last student column
            CellReference cellReference = new CellReference(cell.getRowIndex(), cell.getColumnIndex());
            String endCellName = cellReference.getCellRefParts()[2];

            Row headRow = row;

            for (int i = 1; i < mcQuestionTexts.size() + 1; i++) {
                //add number of questions
                row = sheet.createRow(i + yOffset);
                cell = row.createCell(1, Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(i);
                cell.setCellStyle(commonStyle);

                //add average formula
                cell = row.createCell(2, Cell.CELL_TYPE_FORMULA);
                cell.setCellFormula("AVERAGE(F" + (i + yOffset + 1) + ":" + endCellName + (i + yOffset + 1) + ")");
                cell.setCellStyle(commonStyle);

                //fill blank cells
                cell = row.createCell(3);
                cell.setCellStyle(commonStyle);

                //add question texts
                sheet.setColumnWidth(4, findLongestString(mcQuestionTexts) * 256 * (wb.getFontAt(questionStyle.getFontIndex()).getFontHeightInPoints()) / 10);
                cell = row.createCell(4, Cell.CELL_TYPE_STRING);
                cell.setCellValue(mcQuestionTexts.get(i - 1));
                cell.setCellStyle(questionStyle);
            }

            //add student votes
            for (int i = 0; i < studentVotes.size(); i++) {
                Vote vote = studentVotes.get(i);
                for (int k = 0; k < mcQuestionTexts.size(); k++) {
                    row = sheet.getRow(headRow.getRowNum() + 1 + k);
                    cell = row.createCell(5 + i);
                    for (MCAnswer answer : vote.getMcAnswers()) {
                        //if question of inner loop equals question of outer loop we found
                        // the correct question for this cell
                        if (answer.getQuestion().getText().equals(mcQuestionTexts.get(k))) {
                            Choice choice = answer.getChoice();
                            if (choice != null && choice.getGrade() > 0) {
                                cell = colorizeCell(cell, wb, choice.getGrade());
                                cell.setCellValue(answer.getChoice().getGrade());
                            } else {
                                cell = colorizeCell(cell, wb, -1);
                                cell.setCellValue("");
                            }
                        }
                    }
                }
            }

            // include textual answers
            createTextualAnswers(studentVotes, textualQuestionTexts, sheet, wb);
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int constructInfoPanelRow(String header, String data, int numberStudents, int yOffset, Workbook wb, Sheet sheet,
                                      InfoPanelBorderStyles leftStyle, InfoPanelBorderStyles middleStyle, InfoPanelBorderStyles rightStyle) throws IOException {
        Row row;
        Cell cell;
        CellStyle infoPanelStyle;
        row = sheet.createRow(yOffset);
        yOffset++;
        cell = row.createCell(1);
        cell.setCellValue(header);
        infoPanelStyle = infoPanelStyleConfigurator(leftStyle, wb);
        cell.setCellStyle(infoPanelStyle);

        infoPanelStyle = infoPanelStyleConfigurator(middleStyle, wb);
        cell = createCellsInRow(row, infoPanelStyle, 2, 5);

        cell.setCellStyle(infoPanelStyle);

        cell.setCellValue(data);
        cell = createCellsInRow(row, infoPanelStyle, 6, 10);
        infoPanelStyle = infoPanelStyleConfigurator(rightStyle, wb);
        cell.setCellStyle(infoPanelStyle);
        return yOffset;
    }

    private void configureCellStyles(Workbook wb) {
        commonStyle = wb.createCellStyle();
        headerStyle = wb.createCellStyle();
        questionStyle = wb.createCellStyle();
        positiveStyle = wb.createCellStyle();
        positiveHeaderStyle = wb.createCellStyle();
        negativeStyle = wb.createCellStyle();
        negativeHeaderStyle = wb.createCellStyle();
        improvementStyle = wb.createCellStyle();
        improvementHeaderStyle = wb.createCellStyle();
        furtherStyle = wb.createCellStyle();
        furtherHeaderStyle = wb.createCellStyle();

        Font headerFont = wb.createFont();
        headerFont.setBold(true);

        Font smallFont = wb.createFont();
        smallFont.setFontHeightInPoints((short) 8);

        commonStyle.setBorderBottom(CellStyle.BORDER_HAIR);
        commonStyle.setBorderTop(CellStyle.BORDER_HAIR);
        commonStyle.setBorderLeft(CellStyle.BORDER_HAIR);
        commonStyle.setBorderRight(CellStyle.BORDER_HAIR);
        commonStyle.setAlignment(CellStyle.ALIGN_CENTER);
        questionStyle.cloneStyleFrom(commonStyle);
        questionStyle.setFont(smallFont);
        questionStyle.setAlignment(CellStyle.ALIGN_LEFT);
        headerStyle.cloneStyleFrom(commonStyle);
        headerStyle.setFont(headerFont);

        positiveStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        positiveStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        positiveStyle.setWrapText(true);

        negativeStyle.setFillForegroundColor(HSSFColor.ROSE.index);
        negativeStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        negativeStyle.setWrapText(true);

        improvementStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        improvementStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        improvementStyle.setWrapText(true);

        furtherStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        furtherStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        furtherStyle.setWrapText(true);

        positiveHeaderStyle.cloneStyleFrom(positiveStyle);
        positiveHeaderStyle.setFont(headerFont);

        negativeHeaderStyle.cloneStyleFrom(negativeStyle);
        negativeHeaderStyle.setFont(headerFont);

        improvementHeaderStyle.cloneStyleFrom(improvementStyle);
        improvementHeaderStyle.setFont(headerFont);

        furtherHeaderStyle.cloneStyleFrom(furtherStyle);
        furtherHeaderStyle.setFont(headerFont);
    }

    private void createTextualAnswers(@NonNull List<Vote> answers, @NonNull List<String> questionTexts, Sheet sheet, Workbook wb) {
        Row row;
        Cell cell;
        row = sheet.createRow(sheet.getLastRowNum() + 2);
        cell = row.createCell(1);
        CellStyle helpStyle = wb.createCellStyle();
        helpStyle.cloneStyleFrom(headerStyle);
        helpStyle.setBorderBottom(CellStyle.BORDER_NONE);
        helpStyle.setBorderTop(CellStyle.BORDER_NONE);
        helpStyle.setBorderLeft(CellStyle.BORDER_NONE);
        helpStyle.setBorderRight(CellStyle.BORDER_NONE);
        cell.setCellValue("Kommentare");
        cell.setCellStyle(helpStyle);

        //TODO used to determine style for current line -> its stupid. Think of something better
        int styleCounter = 0;
        for (String textualQuestion : questionTexts) {
            row = sheet.createRow(sheet.getLastRowNum() + 3);
            cell = row.createCell(1);
            cell.setCellValue(textualQuestion);
            setTextQuestionStyle(cell, styleCounter, true);

            //colorize horizontal neighbour cells of headline
            for (int i = 2; i < 5; i++) {
                cell = row.createCell(i);
                setTextQuestionStyle(cell, styleCounter, false);
            }

            int rowNum = sheet.getLastRowNum();
            int counter = 1;

            for (String comment : aggregateTextAnswers(answers, textualQuestion)) {
                row = sheet.createRow(rowNum + 1);
                cell = row.createCell(1, Cell.CELL_TYPE_STRING);

//              introduces line breaks in long comments
                ArrayList<String> commentChunks = splitComment(comment);
                StringBuilder formattedComment = new StringBuilder();
                formattedComment.append(Integer.toString(counter));
                formattedComment.append(": ");

                int chunkCounter = 0;
                for(String chunk : commentChunks){
                    formattedComment.append(chunk);

                    if((chunkCounter + 1) < commentChunks.size()){
                        formattedComment.append(System.lineSeparator());
                    }
                    chunkCounter++;
                }
                cell.setCellValue(formattedComment.toString());

                CellStyle style = setTextQuestionStyle(cell, styleCounter, false);

                // increase height of row based on font size, number of lines and line spacing
                // the origin of 140 % -> http://superuser.com/questions/337181/how-many-pts-is-1-5-line-spacing-in-microsoft-word-2007
                float pointsPerLine = (wb.getFontAt(style.getFontIndex()).getFontHeightInPoints() * 140) / 100;
                row.setHeightInPoints(pointsPerLine * commentChunks.size());

                //colorize horizontal neighbour cells of comment
                for (int i = 2; i < 17; i++) {
                    cell = row.createCell(i);
                    setTextQuestionStyle(cell, styleCounter, false);
                }
                sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 1, 17));

                rowNum++;
                counter++;
            }
            styleCounter++;
        }
    }

    /*
        splits the students text answers into chunks of 20 words.
        returns Arraylist of those chunks as Strings
     */
    private ArrayList<String> splitComment(String comment){
        StringTokenizer tokenizer = new StringTokenizer(comment);
        ArrayList<String> result = new ArrayList<>();

        while (tokenizer.hasMoreTokens()){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i <= 20 && tokenizer.hasMoreTokens(); i++){
                builder.append(" ");
                builder.append(tokenizer.nextToken());
                builder.append(" ");
            }
            result.add(builder.toString());
        }
        return result;
    }

    private int findLongestString(List<String> mcQuestionsTexts) {
        int maxLength = 0;

        for (String question : mcQuestionsTexts) {
            if (maxLength < question.length()) {
                maxLength = question.length();
            }
        }
        return maxLength;
    }

    /**
        returns all comments of the students concerning one of the textual questions
        It is ensured that only comments for the given question (key) are returned.
     */
    private ArrayList<String> aggregateTextAnswers(List<Vote> votes, String key) {
        ArrayList<String> aggregation = new ArrayList<>();
        for (Vote vote : votes) {
            for (Answer answer : vote.getAnswers()) {
                if (answer.getQuestion().getText().equals(key) && !answer.getText().isEmpty()) {
                    aggregation.add(answer.getText());
                }
            }
        }

        return aggregation;
    }

    private CellStyle setTextQuestionStyle(Cell cell, int styleCounter, boolean header) {
        switch (styleCounter) {
            case 0:
                if (header) {
                    cell.setCellStyle(positiveHeaderStyle);
                    return positiveHeaderStyle;
                } else {
                    cell.setCellStyle(positiveStyle);
                    return positiveStyle;
                }
            case 1:
                if (header) {
                    cell.setCellStyle(negativeHeaderStyle);
                    return negativeHeaderStyle;
                } else {
                    cell.setCellStyle(negativeStyle);
                    return negativeStyle;
                }
            case 2:
                if (header) {
                    cell.setCellStyle(improvementHeaderStyle);
                    return improvementHeaderStyle;
                } else {
                    cell.setCellStyle(improvementStyle);
                    return improvementStyle;
                }
            case 3:
                if (header) {
                    cell.setCellStyle(furtherHeaderStyle);
                    return furtherHeaderStyle;
                } else {
                    cell.setCellStyle(furtherStyle);
                    return furtherStyle;
                }
//                cell.setCellStyle(furtherStyle);
            default:
                cell.setCellStyle(positiveStyle);
                return positiveStyle;
        }
    }

    /*
    Adjusts border settings of given style.
    used to quickly change border settings when constructing infopanel.
     */
    private CellStyle infoPanelStyleConfigurator(InfoPanelBorderStyles style, Workbook wb) throws IOException {
        CellStyle infoPanelStyle = wb.createCellStyle();
        infoPanelStyle.setBorderLeft(CellStyle.BORDER_NONE);
        infoPanelStyle.setBorderRight(CellStyle.BORDER_NONE);
        infoPanelStyle.setBorderBottom(CellStyle.BORDER_NONE);
        infoPanelStyle.setBorderTop(CellStyle.BORDER_NONE);
        switch (style) {
            case topLeftCorner:
                infoPanelStyle.setBorderTop(CellStyle.BORDER_HAIR);
                infoPanelStyle.setBorderLeft(CellStyle.BORDER_HAIR);
                break;
            case topRightCorner:
                infoPanelStyle.setBorderTop(CellStyle.BORDER_HAIR);
                infoPanelStyle.setBorderRight(CellStyle.BORDER_HAIR);
                break;
            case bottomLeftCorner:
                infoPanelStyle.setBorderLeft(CellStyle.BORDER_HAIR);
                infoPanelStyle.setBorderBottom(CellStyle.BORDER_HAIR);
                break;
            case bottomRightCorner:
                infoPanelStyle.setBorderRight(CellStyle.BORDER_HAIR);
                infoPanelStyle.setBorderBottom(CellStyle.BORDER_HAIR);
                break;
            case top:
                infoPanelStyle.setBorderTop(CellStyle.BORDER_HAIR);
                break;
            case bottom:
                infoPanelStyle.setBorderBottom(CellStyle.BORDER_HAIR);
                break;
            case right:
                infoPanelStyle.setBorderRight(CellStyle.BORDER_HAIR);
                break;
            case left:
                infoPanelStyle.setBorderLeft(CellStyle.BORDER_HAIR);
                break;
            default:
                break;
        }

        return infoPanelStyle;
    }

    private Cell createCellsInRow(Row row, CellStyle style, int startColumn, int endColumn) {
        Cell cell = null;
        for (int i = startColumn; i <= endColumn; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
        }
        return cell;
    }

    private Cell colorizeCell(Cell cell, Workbook wb, float grade) {
        CellStyle style = wb.createCellStyle();
        style.cloneStyleFrom(commonStyle);

        if (grade >= 1.0 && grade < 2.0f) {
            style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cell.setCellStyle(style);
        } else if (grade > 3.0 && grade <= 4.0) {
            style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cell.setCellStyle(style);
        } else if (grade > 4.0) {
            style.setFillForegroundColor(HSSFColor.MAROON.index);
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cell.setCellStyle(style);
        } else {
            cell.setCellStyle(style);
        }
        return cell;
    }


    private enum InfoPanelBorderStyles {
        topLeftCorner,
        topRightCorner,
        bottomLeftCorner,
        bottomRightCorner,
        bottom,
        top,
        left,
        right,
        none
    }
}

