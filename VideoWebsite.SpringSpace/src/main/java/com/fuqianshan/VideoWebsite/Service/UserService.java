package com.fuqianshan.VideoWebsite.Service;

import java.util.List;
import java.util.Map;

import com.fuqianshan.VideoWebsite.DAO.AuthKeyDAO;
import com.fuqianshan.VideoWebsite.DAO.AuthoritiesDAO;
import com.fuqianshan.VideoWebsite.DAO.UserDAO;
import com.fuqianshan.VideoWebsite.Entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    AuthKeyDAO authKeyDAO;
    @Autowired
    AuthoritiesDAO authoritiesDAO;

    public void setUserEntity(UserEntity userEntity) {
    }

    public boolean adminCheck(){
        List<Map<String,Object>> list=authoritiesDAO.queryByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        for(int i=0;i<=list.size()-1;i++){
            String authority=(String)list.get(i).get("authority");
            if(authority.compareTo("ROLE_ADMIN")==0)return(true);
        }
        return(false);
    }

    public int register(String username, String password, String authKey) {// 成功返回0，因登录名重复失败返回1，授权码不正确返回2，其它问题返回-1
        

        // 诸检查
        List<Map<String, Object>> result = userDAO.queryByUsername(username);
        if (result.size() != 0)
            return (1);

        result = authKeyDAO.queryByValue(authKey);
        if (result.size() == 0){
            if(authKey!="")return(2);
            String userPassword = "{noop}" + password;
            userDAO.userRegister(username, userPassword, 0);
        }
        else{
            String userPassword = "{noop}" + password;
            userDAO.userRegister(username, userPassword, 1);

        }

        return (0);
    }

}
