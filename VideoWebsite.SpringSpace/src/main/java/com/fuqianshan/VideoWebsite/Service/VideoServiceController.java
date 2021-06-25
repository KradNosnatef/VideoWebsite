package com.fuqianshan.VideoWebsite.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fuqianshan.VideoWebsite.Entity.VideoEntity;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VideoServiceController {
    @Autowired
    VideoService videoService;

    @RequestMapping("/Xapi/Video/listAllEnabled")//获取所有短视频基础属性
    public @ResponseBody JSONPObject IOlistAllEnabled(HttpServletResponse httpServletResponse){
        List<VideoEntity>response=videoService.listAllEnabled();
        //System.out.println(response.get(0));
        return(IOToolKits.myResponseGenerator(httpServletResponse, response, "_IOlistAllEnabled"));
    }

    @RequestMapping("/Xapi/Video/listAllMyVideo")//获取本session对应用户的所有短视频（含未审核）基本信息
    public @ResponseBody JSONPObject IOlistAllMyVideo(HttpServletResponse httpServletResponse){
        List<VideoEntity>response=videoService.listAllMyVideo();
        //System.out.println(response.get(0));
        return(IOToolKits.myResponseGenerator(httpServletResponse, response, "_IOlistAllMyVideo"));
    }

    @RequestMapping("/Xapi/admin/listAllUnabledVideo")//获取所有未审核短视频基本信息
    public @ResponseBody JSONPObject IOlistAllUnabledVideo(HttpServletResponse httpServletResponse){
        List<VideoEntity>response=videoService.listAllUnabledVideo();
        //System.out.println(response.get(0));
        return(IOToolKits.myResponseGenerator(httpServletResponse, response, "_IOlistAllUnabledVideo"));
    }

    @PostMapping("/Xapi/Video/upload")
    @ResponseBody
    public String uploadWork(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        request.setCharacterEncoding("UTF-8");

        return(videoService.upload(file));
    }

    @RequestMapping(value = "/Xapi/media", method = RequestMethod.GET)  
    public ResponseEntity<InputStreamResource> downloadFile()  
            throws IOException {  
        String filePath = "E:/test/1/6.mp4";  
        FileSystemResource file = new FileSystemResource(filePath);  
        HttpHeaders headers = new HttpHeaders();  
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));  
        headers.add("Pragma", "no-cache");  
        headers.add("Expires", "0");  
  
        return ResponseEntity  
                .ok()  
                .headers(headers)  
                .contentLength(file.contentLength())  
                .contentType(MediaType.parseMediaType("application/octet-stream"))  
                .body(new InputStreamResource(file.getInputStream()));  
    }  
}
