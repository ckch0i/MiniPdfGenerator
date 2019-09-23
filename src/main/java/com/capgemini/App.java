package com.capgemini;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.xmp.XMPException;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {

    private static final String PATH_TO_GET_FONTS_FROM = "fonts";
    private static final String OUTPUT_FILE = "PrintAllFonts.pdf";

    public static void main(String[] args) throws DocumentException, XMPException, IOException, URISyntaxException {
        generateFontsDocument();
        //        loadFiles();
    }

    public static void loadFiles() throws URISyntaxException, IOException {
        Files.newDirectoryStream(Paths.get(App.class.getClassLoader().getResource(PATH_TO_GET_FONTS_FROM).toURI()),
                "*.{ttf}").forEach(f -> System.out.println(f.getFileName())).;
    }

    private static void generateFontsDocument() throws IOException {
        BasePdfDocument basePdfDocument = new BasePdfDocument();

        try {
            PdfWriter writer = PdfWriter.getInstance(basePdfDocument, new FileOutputStream(OUTPUT_FILE));
            basePdfDocument.open();
            ArrayList<String> fonts = getFonts(PATH_TO_GET_FONTS_FROM);
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
        fonts.forEach(font -> tryCatcher(basePdfDocument, font));
    }

    @SneakyThrows
    private static void tryCatcher(BasePdfDocument basePdfDocument, String font) {
        basePdfDocument.add(new Paragraph("Text written in font: " + font,
                FontFactory.getFont(String.valueOf(font), BaseFont.WINANSI, BaseFont.EMBEDDED, 16)));
    }

        private static ArrayList<String> getFonts(String pathToGetfilesFrom) throws IOException {
            ArrayList<String> files = new ArrayList<>();
            Files.list(Paths.get(pathToGetfilesFrom)).filter(i -> i.toString().endsWith(".ttf"))
                    .forEach(i -> files.add(i.toString()));
            return files;
        }

}
