package com.capgemini;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

public class BasePdfDocument extends Document {

    public BasePdfDocument() {
        super(PageSize.A4, 35, 35, 35, 65);
    }

}
