package com.lengqi.controller;

import com.lengqi.common.constant.Constant;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author lengqi
 * @DATE: 2021/5/28
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return Constant.SA_TOKEN;
    }
    public static boolean doPrepare(String path) {
        boolean flag = false;
        ClassPathResource classPathResource = new ClassPathResource("/Users/lengqi/Downloads/IEDriverServer.exe");
        String filename = classPathResource.getFilename();

        try {
            InputStream inputStream = classPathResource.getInputStream();
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            String fileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
            String filePath = path + File.separator + fileName;
            File file = new File(filePath);
            if (!file.exists()){
                FileOutputStream output = new FileOutputStream(file);
                output.write(bytes);
            }
            flag = true;
        } catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }



}
