package com.example.emptyspring;

import com.itextpdf.text.*;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elio on 09/01/2023
 */
public class LocalTest {
    @Test
    public void generateZhEnStarter() {
        generateZhEn(populateMap());
    }

    public void generateZhEn(HashMap<String, String> map) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("zh.js"))));
            BufferedWriter enWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("D:\\Project\\WAP-QMS-2022\\branches\\WAP-QMS-VUE\\src\\i18n\\lang\\en.js"))));
            writer.write("export const m = {\n");
            enWriter.write("export const m = {\n");
            String[] keys = map.keySet().toArray(new String[]{});
            Arrays.sort(keys);
            for (String key : keys) {
                // zh:
                writer.write("    " + key + ": '" + map.get(key) + "',\n");
                // en：首字母大写，单词分开
                StringBuilder sb = new StringBuilder();
                int count = 0;
                for (char c : key.toCharArray()) {
                    count++;
                    if (c >= 'a') {
                        if (count == 1) {
                            c = (char) (c - 32);
                        }
                        sb.append(c);
                    } else {
                        sb.append(" ").append(c);
                    }
                }
                enWriter.write("    " + key + ": '" + sb + "', //" + map.get(key) + "\n");
            }
            writer.write("}");
            enWriter.write("}");
            writer.flush();
            enWriter.flush();
            writer.close();
            enWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    // 1.封装map,去重复的键
    public HashMap<String, String> populateMap() {
        FileInputStream zh;


        try {
            zh = new FileInputStream("D:\\Project\\WAP-QMS-2022\\branches\\WAP-QMS-VUE\\src\\i18n\\lang\\zh.js");
            BufferedReader reader = new BufferedReader(new InputStreamReader(zh));
            String pattern = "'(.*?)'";
            String pattern1 = "\"(.*?)\"";
            Pattern r = Pattern.compile(pattern);
            Pattern r1 = Pattern.compile(pattern1);
            HashMap<String, String> map = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(":")) {
                    int i = line.indexOf(":");
                    String eng = line.substring(0, i).trim();
                    Matcher m = r.matcher(line);
                    boolean flag;
                    if (!(flag = m.find())) {
                        m = r1.matcher(line);
                        flag = m.find();
                    }
                    if (flag) {
                        if (!map.containsKey(eng)) {
                            map.put(eng, m.group(1));
                        }
                    }
                }
            }
            reader.close();
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
