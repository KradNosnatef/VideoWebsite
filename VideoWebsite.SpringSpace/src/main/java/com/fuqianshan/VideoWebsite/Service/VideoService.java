package com.fuqianshan.VideoWebsite.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fuqianshan.VideoWebsite.DAO.UserDAO;
import com.fuqianshan.VideoWebsite.DAO.VideoRecordsDAO;
import com.fuqianshan.VideoWebsite.Entity.VideoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("VideoService")
public class VideoService {
    @Autowired
    VideoRecordsDAO videoRecordsDAO;
    @Autowired
    UserDAO userDAO;

    // 为啥这里要多一层，蛋疼
    // 什么叫过度设计啊（战术后仰）
    public List<VideoEntity> listAllEnabled() {
        List<VideoEntity> list = new ArrayList<VideoEntity>();
        VideoEntity videoEntity;
        List<Map<String, Object>> lMaps = videoRecordsDAO.listAllEnabled();
        for (int i = 0; i <= lMaps.size() - 1; i++) {
            videoEntity = new VideoEntity(lMaps.get(i));
            list.add(videoEntity);

        }
        return (list);
    }

    public List<VideoEntity> listAllMyVideo() {
        List<VideoEntity> list = new ArrayList<VideoEntity>();
        VideoEntity videoEntity;
        List<Map<String, Object>> lMaps = videoRecordsDAO
                .queryByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        for (int i = 0; i <= lMaps.size() - 1; i++) {
            videoEntity = new VideoEntity(lMaps.get(i));
            list.add(videoEntity);

        }
        return (list);
    }

    public void SetEnabledByVID(String vid,String enabled){//设置enabled
        videoRecordsDAO.AlterEnabledByVID(vid, enabled);
    }

    public List<VideoEntity> listAllUnabledVideo() {
        List<VideoEntity> list = new ArrayList<VideoEntity>();
        VideoEntity videoEntity;
        List<Map<String, Object>> lMaps = videoRecordsDAO.queryByEnabled("0");
        for (int i = 0; i <= lMaps.size() - 1; i++) {
            videoEntity = new VideoEntity(lMaps.get(i));
            list.add(videoEntity);

        }
        return (list);
    }

    public boolean accessabilityCheck(String vid, UserService userService, String mode) {// 校验当前session是否可以访问该vid对应的文件，mode为
                                                                                         // read 、write和enable
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userService.adminCheck())
            return (true);
        else if (mode.compareTo("write") == 0) {
            String queryUsername = videoRecordsDAO.queryByVID(vid).get(0).get("username").toString();
            if (queryUsername.compareTo(username) == 0)
                return (true);
        } else if (mode.compareTo("read") == 0) {
            String queryEnabled = videoRecordsDAO.queryByVID(vid).get(0).get("enabled").toString();
            if (queryEnabled.compareTo("1") == 0)
                return (true);
        } else if(mode.compareTo("enable")==0){
            return(false);
        }
        return (false);
    }

    public String queryUIDByVID(String vid){
        String uid=(String)(videoRecordsDAO.queryByVID(vid).get(0).get("uid"));
        return(uid);
    }

    public String upload(MultipartFile file) throws IllegalStateException, IOException {
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String path = null;
            String type = filename.indexOf(".") != -1
                    ? filename.substring(filename.lastIndexOf(".") + 1, filename.length())
                    : null;
            System.out.println(type);
            if (type != null) {
                if ("MP4".equals(type.toUpperCase())) {// 全部验证完毕，开始注入

                    String uid = userDAO
                            .queryByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get(0)
                            .get("uid").toString();
                    String vid = videoRecordsDAO.insertByUID(uid, filename);
                    System.out.println("uid is:" + uid + "\nvid is:" + vid);

                    path = "E:/test/" + uid + "/" + vid + ".mp4";
                    File dest = new File(path);
                    // 判断文件父目录是否存在
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdir();
                    }

                    file.transferTo(dest);

                    return ("success");
                } else {
                    return "typeWrongError";
                }
            } else {
                return "typeNullError";
            }
        } else {
            return "fileEmptyError";
        }
    }
}
