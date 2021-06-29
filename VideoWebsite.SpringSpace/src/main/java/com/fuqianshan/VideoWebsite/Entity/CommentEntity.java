package com.fuqianshan.VideoWebsite.Entity;

import java.util.Map;

public class CommentEntity {
    public String cid,uid,vid,value;
    public String username;
    public boolean accessability;

    public CommentEntity(Map<String,Object> map,boolean accessability){
        cid=map.get("cid").toString();
        vid=map.get("vid").toString();
        uid=map.get("uid").toString();
        value=map.get("value").toString();

        username=map.get("username").toString();

        this.accessability=accessability;
    }
}
