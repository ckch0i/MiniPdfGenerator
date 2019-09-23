package com.capgemini;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;

public class BasePdfDocument extends Document {

    protected final Font testFont;

    public BasePdfDocument() {
        super(PageSize.A4, 35, 35, 35, 65);
        testFont = FontFactory.getFont("c:/ROserifwebbold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED, 12);
    }

}
