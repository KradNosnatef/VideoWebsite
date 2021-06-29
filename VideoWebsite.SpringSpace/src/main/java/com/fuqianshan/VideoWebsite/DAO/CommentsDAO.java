package com.fuqianshan.VideoWebsite.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //简单查询区
    public List<Map<String,Object>> queryByCID(String cid){
        String sql = "select comments.*,users.username from comments,users where comments.cid=? and comments.uid=users.uid";
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList(sql, cid);
        return (list);
    }
    public List<Map<String,Object>> queryByVID(String vid){
        String sql = "select comments.*,users.username from comments,users where comments.vid=? and comments.uid=users.uid";
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList(sql, vid);
        return (list);
    }
    //自定义功能区
    public void pushComment(String uid,String vid,String value){
        String sql="insert into comments (uid,vid,value) values (?,?,?)";
        jdbcTemplate.update(sql, uid,vid,value);
    }

    public void editComment(String cid,String value){
        String sql="update comments set value=? where comments.cid=?";
        jdbcTemplate.update(sql, value,cid);
    }

    public void deleteComment(String cid){
        String sql="delete from comments where comments.cid=?";
        jdbcTemplate.update(sql, cid);
    }
}
