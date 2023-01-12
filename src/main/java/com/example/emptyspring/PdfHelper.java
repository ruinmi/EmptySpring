package com.example.emptyspring;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.ibatis.io.Resources;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created by elio on 10/01/2023
 */
public class PdfHelper {
    public static final BaseFont BF_MSYH;
    public static final BaseFont BF_TIMES;

    static {
        try {
            String f1 = Resources.getResourceAsFile("pdf/fonts/msyh/msyh.ttc").getPath();
            String f2 = Resources.getResourceAsFile("pdf/fonts/times/times.ttf").getPath();
            BF_MSYH = BaseFont.createFont(f1 + ",0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BF_TIMES = BaseFont.createFont(f2, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Font FONT_MSYH = new Font(BF_MSYH, 11, Font.NORMAL);
    public static final Font FONT_TIMES = new Font(BF_TIMES, 10, Font.NORMAL);

    public static void addNumberCell(PdfPTable table, String content) {
        addCell(table, content, FONT_TIMES, Element.ALIGN_RIGHT);
    }

    public static void addCenterNumberCell(PdfPTable table, String content) {
        addCell(table, content, FONT_TIMES, Element.ALIGN_CENTER);
    }

    public static void addTextCell(PdfPTable table, String content) {
        addCell(table, content, FONT_MSYH, Element.ALIGN_LEFT);
    }

    public static void addTextCell(PdfPTable table, String content, int span) {
        addCell(table, content, FONT_MSYH, Element.ALIGN_LEFT, span);
    }

    public static void addCenterTextCell(PdfPTable table, String content) {
        addCell(table, content, FONT_MSYH, Element.ALIGN_CENTER);
    }

    public static void addCell(PdfPTable table, String content, Font font, int alignment) {
        addCell(table, content, font, alignment, null);
    }

    public static void addCell(PdfPTable table, String content, Font font, int alignment, Integer span) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setUseAscender(true);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(alignment);
        if (span != null) {
            cell.setColspan(span);
        }
        table.addCell(cell);
    }




    public static float cmToItextPixel(float cm) {
        float ratio = 842 / 29.7f;
        return ratio * cm;
    }

}
