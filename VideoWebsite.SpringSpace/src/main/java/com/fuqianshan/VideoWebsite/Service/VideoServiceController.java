package com.fuqianshan.VideoWebsite.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fuqianshan.VideoWebsite.Entity.VideoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
