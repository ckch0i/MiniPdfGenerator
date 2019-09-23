package com.capgemini;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.xmp.XMPException;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {

    private static final String PATH_TO_GET_FONTS_FROM = "fonts";
    private static final String OUTPUT_FILE = "PrintAllFonts.pdf";

    public static void main(String[] args) throws DocumentException, XMPException, IOException, URISyntaxException {
        generateFontsDocument();
    }

    private static void generateFontsDocument() throws IOException {
        BasePdfDocument basePdfDocument = new BasePdfDocument();

        try {
            PdfWriter writer = PdfWriter.getInstance(basePdfDocument, new FileOutputStream(OUTPUT_FILE));
            basePdfDocument.open();
            writeFonts(basePdfDocument, getFonts());
            basePdfDocument.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void writeFonts(BasePdfDocument basePdfDocument, ArrayList<Path> fontsList) {
        fontsList.forEach(font -> createFontParagraph(basePdfDocument, font));
    }

    @SneakyThrows
    private static void createFontParagraph(BasePdfDocument basePdfDocument, Path font) {
        basePdfDocument.add(new Paragraph("Text written in font: " + font,
                FontFactory.getFont(App.class.getClassLoader().getResource(PATH_TO_GET_FONTS_FROM).toURI().toString()+ "/" + font, BaseFont.WINANSI, BaseFont.EMBEDDED, 16)));
    }

    private static ArrayList<Path> getFonts() throws IOException, URISyntaxException {
        ArrayList<Path> fontsList = new ArrayList<>();
        Files.newDirectoryStream(Paths.get(App.class.getClassLoader().getResource(PATH_TO_GET_FONTS_FROM).toURI()),
                "*.{ttf}").forEach(f -> fontsList.add(f.getFileName()));
        return fontsList;
    }

}
