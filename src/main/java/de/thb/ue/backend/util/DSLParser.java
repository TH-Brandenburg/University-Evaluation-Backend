package de.thb.ue.backend.util;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Created by Alexander on 13.07.2016.
 */
public class DSLParser {
    public static void main(String... aArgs) throws IOException {
        DSLParser parser = new DSLParser("D:\\test.txt");
        parser.processLineByLine();
        log("Done.");
    }

    /**
     Constructor.
     @param aFileName full name of an existing, readable file.
     */
    public DSLParser(String aFileName){
        fFilePath = Paths.get(aFileName);
    }


    /** Template method that calls {@link #processLine(String)}.  */
    public final void processLineByLine() throws IOException {
        try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
            while (scanner.hasNextLine()){
                processLine(scanner.nextLine());
            }
        }
    }

    protected void processLine(String aLine){
        //use a second Scanner to parse the content of each line
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter(":");

        if (scanner.hasNext()){
            String entry;
            String value;
            String q;
            String a;
            String[] answers;
            entry = scanner.next();
            value = scanner.next();
            String firstPart = quote(entry.trim());
            String secondPart = quote(value.trim());
            answers = secondPart.split(";");

            log(firstPart + " Text is: " + secondPart + Arrays.toString(answers));

        }

        else {
            log("Empty or invalid line. Unable to process.");
        }
        //scanner.close();
    }

    private final Path fFilePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static void log(Object aObject){
        System.out.println(String.valueOf(aObject));
    }

    private String quote(String aText){
        String QUOTE = "'";
        return QUOTE + aText + QUOTE;
    }
}
