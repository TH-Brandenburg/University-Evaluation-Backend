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

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFGeneration {

    public static void createQRCPDF(List<BufferedImage> tickets, String evaluationUID, String subject, SemesterType semesterType, int year, File workingDirectory) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
//        PDRectangle rectangle = new PDRectangle(page.findCropBox().getWidth(), 500);
//        page.setCropBox(rectangle);

        document.addPage(page);
        PDXObjectImage pdImage;

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            int x = 0;
            // start from top of page instead of bottom
            // somehow the first row gets swallowed -> start with second row instead
            int y = (int) page.findCropBox().getHeight() - (new PDJpeg(document, tickets.get(0))).getHeight();

            int imageHeight;
            int imageWidth;
            String qrCodePurpose = "Evaluation QR-Codes für das Fach " + subject + " im " + (semesterType==SemesterType.WINTER ? "Winter" : "Sommer") + "semester " + Integer.toString(year);
            PDFont font = PDType1Font.HELVETICA;
            int fontSize = 13;

            // center text horizontally
            float textWidth = font.getStringWidth(qrCodePurpose) / 1000 * fontSize;
            int textX  = (int) ((page.findCropBox().getWidth() / 2) - (textWidth / 2));

            // add QR-Codes
            for (int i = 0; i < tickets.size(); i++) {

                pdImage = new PDJpeg(document, tickets.get(i), 1.0f);
                imageHeight = pdImage.getHeight();
                imageWidth = pdImage.getWidth();

                if ((imageWidth + x) > page.findCropBox().getWidth()) {
                    x = 0;
                    y -= imageHeight;
                }

                if ( y <= 0) {
                    writeText(contentStream, page, qrCodePurpose, imageHeight, textX, (y + imageHeight) - (imageHeight / 4), fontSize, font);
                    page = new PDPage();
                    document.addPage(page);
                    contentStream.close();
                    contentStream = new PDPageContentStream(document, page);
                    y = (int) page.findCropBox().getHeight() - pdImage.getHeight();
                    x = 0;
                }

                contentStream.drawImage(pdImage, x, y);

                if(i + 1 >= tickets.size()){
                    writeText(contentStream, page, qrCodePurpose, imageHeight, textX, y-20, fontSize, font);
                }
                x += imageWidth;
            }

            /*PDDocumentCatalog catalog = document.getDocumentCatalog();
            catalog.getAcroForm().setXFA(null);*/

            contentStream.close();
            if (!workingDirectory.exists()) {
                workingDirectory.mkdir();
            }
            File outputFile = new File(workingDirectory, "qrcodes.pdf");
            document.save(outputFile);

        } catch (IOException | COSVisitorException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeText(PDPageContentStream stream, PDPage page, String text, int imageHeight, int x, int y, int fontSize, PDFont font){
        try {
            stream.beginText();
            stream.setFont(font, fontSize);
            stream.moveTextPositionByAmount(x, y);
            stream.drawString(text);
            stream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
