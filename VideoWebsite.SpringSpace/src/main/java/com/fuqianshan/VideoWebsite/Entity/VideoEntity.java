package com.fuqianshan.VideoWebsite.Entity;

import java.util.Map;

public class VideoEntity {
    public String vid,uid,enabled,filename;
    public String username;

    public VideoEntity(Map<String,Object> map){
        vid=map.get("vid").toString();
        uid=map.get("uid").toString();
        enabled=map.get("enabled").toString();
        filename=map.get("filename").toString();
        username=map.get("username").toString();
    }
}
