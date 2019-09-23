package com.capgemini;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfAWriter;

import java.io.FileOutputStream;

public class Generator {

    //    public File generate(BasePdfDocument document, String filename)
    //            throws IOException, DocumentException, XMPException {
    //
    //
    //        return file;
    //    }

    private PdfAWriter getWriter(FileOutputStream fos, Document document) throws DocumentException {
        PdfAWriter writer = PdfAWriter.getInstance(document, fos, PdfAConformanceLevel.PDF_A_1A);
        writer.setViewerPreferences(PdfAWriter.PageModeUseOutlines);
        writer.setViewerPreferences(PdfAWriter.DisplayDocTitle);
        writer.setRunDirection(PdfAWriter.RUN_DIRECTION_LTR);
        writer.setTagged(PdfAWriter.markAll);
        writer.createXmpMetadata();
        return writer;
    }

}
