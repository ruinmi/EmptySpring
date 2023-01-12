package com.example.emptyspring;

import com.itextpdf.text.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by elio on 09/01/2023
 */
public class LocalTest {
    @Test
    public void you() {
        try (FileOutputStream fos = new FileOutputStream("nihao.pdf")) {
            CmDocument doc = new CmDocument(PageSize.A4, 2F, 1.75F, 2.54F, 2.54F, fos);
            doc.open();
            doc.addText("报 价 书", Element.ALIGN_CENTER, CmFont.BF_MSYH_BD.getFont(), 18, 0);
            doc.close();
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }

    }
}
