package com.example.emptyspring;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.ibatis.io.Resources;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created by elio on 11/01/2023
 */
public class CmDocument extends Document {
    private int pageNum;
    private final PdfWriter writer;

    public CmDocument(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom, FileOutputStream fos) throws DocumentException {
        super(pageSize, cmToItextPixel(marginLeft), cmToItextPixel(marginRight), cmToItextPixel(marginTop), cmToItextPixel(marginBottom));
        this.writer = PdfWriter.getInstance(this, fos);
        this.pageNum = 1;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    @Override
    public void open() {
        super.open();
        addHeader();
        addFooter();
    }

    @Override
    public void close() {
        super.close();
        this.writer.close();
    }

    @Override
    public boolean newPage() {
        this.pageNum++;
        addHeader();
        addFooter();
        return super.newPage();
    }

    public void addH1(String text, int alignment) throws DocumentException {
        Paragraph p = new Paragraph(text, new Font(CmFont.BF_MSYH_BD.getFont(), 10.56F, Font.NORMAL));
    }

    public void addText(String text, int alignment, BaseFont font, float fontSize) throws DocumentException {
        addText(text, alignment, font, fontSize, 10);
    }
    public void addText(String text, int alignment, BaseFont font, float fontSize, float lineSpacing) throws DocumentException {
        Paragraph p = new Paragraph(text, new Font(font, fontSize, Font.NORMAL));
        p.setAlignment(alignment);
        p.setLeading(lineSpacing);
        this.add(p);
    }
    public void addHeader() {
        Image image;
        try {
            image = Image.getInstance(ClassLoader.getSystemResource("pdf/img/header.jpg").getPath());
            image.scalePercent((this.right() - this.left()) * 100 / 2060);
            image.setAbsolutePosition(this.left(), PageSize.A4.getHeight() - cmToItextPixel(1) - image.getScaledHeight());
            this.add(image);
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }

    }

    public void addFooter() {
        Image image;
        try {
            image = Image.getInstance(ClassLoader.getSystemResource("pdf/img/footer.png").getPath());
            image.scalePercent((this.right() - this.left()) * 100 / 647);
            image.setAbsolutePosition(this.left(), cmToItextPixel(1.14F));
            this.add(image);
            PdfContentByte canvas = writer.getDirectContentUnder();
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase(this.getPageNum().toString()), this.getPageSize().getWidth() / 2, image.getAbsoluteY() + image.getHeight(), 0);
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static float cmToItextPixel(float cm) {
        float ratio = 842 / 29.7f;
        return ratio * cm;
    }
}
