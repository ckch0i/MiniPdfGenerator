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
import java.util.function.Consumer;

public class App {

    public static void main(String[] args) throws DocumentException, XMPException, IOException {
        BasePdfDocument basePdfDocument = new BasePdfDocument();
        String pathToGetFilesFrom = "./src/main/resources/fonts";

        try {
            PdfWriter writer = PdfWriter
                    .getInstance(basePdfDocument, new FileOutputStream("c:/temp/PrintAllFonts.pdf"));
            basePdfDocument.open();

            ArrayList<String> fonts = getFonts(pathToGetFilesFrom);
            fonts.forEach(font -> {
                try {
                    basePdfDocument.add(new Paragraph("Testing of font " + font,
                            FontFactory.getFont(String.valueOf(font), BaseFont.WINANSI, BaseFont.EMBEDDED, 16)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            basePdfDocument.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getFonts(String pathToGetfilesFrom) throws IOException {
        ArrayList<String> files = new ArrayList<>();
        Files.list(Paths.get(pathToGetfilesFrom)).filter(i -> i.toString().endsWith(".ttf"))
                .forEach(i -> files.add(i.toString()));
        return files;
    }

    static Consumer<BasePdfDocument> lambdaWrapper(Consumer<BasePdfDocument> consumer) throws Exception {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
