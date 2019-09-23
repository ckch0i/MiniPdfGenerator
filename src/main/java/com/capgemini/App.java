package com.capgemini;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.xmp.XMPException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {

    private static final String pathToGetFontsFrom = "./src/main/resources/fonts";
    private static final String outputFile = "c:/temp/PrintAllFonts.pdf";

    public static void main(String[] args) throws DocumentException, XMPException, IOException {
        generateFontsDocument();
    }

    private static void generateFontsDocument() throws IOException {
        BasePdfDocument basePdfDocument = new BasePdfDocument();

        try {
            PdfWriter writer = PdfWriter.getInstance(basePdfDocument, new FileOutputStream(outputFile));
            basePdfDocument.open();

            ArrayList<String> fonts = getFonts(pathToGetFontsFrom);
            writeFonts(basePdfDocument, fonts);
            basePdfDocument.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeFonts(BasePdfDocument basePdfDocument, ArrayList<String> fonts) {
        fonts.forEach(font -> {
            try {
                basePdfDocument.add(new Paragraph("Text written in font: " + font,
                        FontFactory.getFont(String.valueOf(font), BaseFont.WINANSI, BaseFont.EMBEDDED, 16)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static ArrayList<String> getFonts(String pathToGetfilesFrom) throws IOException {
        ArrayList<String> files = new ArrayList<>();
        Files.list(Paths.get(pathToGetfilesFrom)).filter(i -> i.toString().endsWith(".ttf"))
                .forEach(i -> files.add(i.toString()));
        return files;
    }

}
