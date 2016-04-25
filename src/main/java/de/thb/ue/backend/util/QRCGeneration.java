/*
 * Copyright 2016 Max Gregor
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCGeneration {

    public static final int SIZE_SMALL = 120;
    public static final int SIZE_MEDIUM = 230;
    public static final int SIZE_LARGE = 350;

    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";
    public static final String ENCODING_SHIFT_JIS = "Shift_JIS";


    public static BufferedImage generateQRC(String text, int size, ErrorCorrectionLevel ecLevel, String encoding) throws WriterException, IOException {

        Map<EncodeHintType, Object> encodingHints = new HashMap<>();
        encodingHints.put(EncodeHintType.CHARACTER_SET, encoding);
        encodingHints.put(EncodeHintType.ERROR_CORRECTION, ecLevel);
        BitMatrix matrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size, encodingHints);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
}
