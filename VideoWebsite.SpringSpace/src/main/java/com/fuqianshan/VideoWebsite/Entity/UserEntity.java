package com.fuqianshan.VideoWebsite.Entity;

public class UserEntity {
    private String username;
    private String password;
    private int enabled;

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getPassword(){
        return password;
    }
    public void setPawssword(String password){
        this.password=password;
    }
    
    public int getEnabled(){
        return enabled;
    }
    public void setEnabled(int enabled){
        this.enabled=enabled;
    }
}
