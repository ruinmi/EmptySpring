package com.example.emptyspring;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.apache.ibatis.io.Resources;

import java.io.IOException;

/**
 * Created by elio on 12/01/2023
 */
public enum CmFont {
    BF_MSYH("pdf/fonts/msyh/msyh.ttc"),
    BF_MSYH_BD("pdf/fonts/msyh/msyhbd.ttc"),
    BF_TIMES("pdf/fonts/times/times.ttf"),
    BF_TIMES_BD("pdf/fonts/times/timesbd.ttf");

    private final BaseFont bf;

    CmFont(String relativePath) {
        try {
            String path = Resources.getResourceAsFile(relativePath).getPath();
            if (path.lastIndexOf(".ttc") > 0) {
                this.bf = BaseFont.createFont(path + ",0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } else {
                this.bf = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            }
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Missing Font: " + this.name());
        }
    }

    public BaseFont getFont() {
        return bf;
    }
}
