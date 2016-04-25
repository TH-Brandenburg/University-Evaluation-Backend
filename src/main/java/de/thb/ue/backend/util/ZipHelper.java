package de.thb.ue.backend.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipHelper {

    public static void fileToZipFile(File toZip, File output) {
        byte[] buffer = new byte[1024];

        try {
            FileOutputStream fos = new FileOutputStream(output);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(toZip.getName());
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(toZip);

            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            in.close();
            zos.closeEntry();

            //remember close it
            zos.close();

            System.out.println("Done");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void folderToZipFile(File folderToZip, File output) throws IOException {
        byte[] buffer = new byte[1024];
        assert folderToZip != null && folderToZip.exists() && folderToZip.isDirectory();

        FileOutputStream fos = new FileOutputStream(output);
        ZipOutputStream zos = new ZipOutputStream(fos);

        for (String file : folderToZip.list()) {
            File actualFile = new File(file);
            if (!actualFile.getName().equals(output.getName())) {
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);

                FileInputStream in =
                        new FileInputStream(new File(folderToZip, file));

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                in.close();
            }
        }

        zos.closeEntry();
        //remember close it
        zos.close();

    }

}
