package com.example.emptyspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by elio on 15/02/2023
 */
@RestController
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            Thread.sleep(2000);
            System.out.println("ok" + is.available());
            if (true) {
                throw new IOException("wocao");
            }
            return ResponseEntity.ok("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传失败：" + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
