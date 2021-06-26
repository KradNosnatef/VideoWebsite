package com.fuqianshan.VideoWebsite.DAO;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class VideoRecordsDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //简单查询区
    public List<Map<String,Object>> listAllEnabled(){
        String sql = "select video_records.*,users.username from video_records,users where video_records.uid=users.uid and video_records.enabled=1";
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList(sql);
        return (list);
    }

    public List<Map<String,Object>> queryEnabledByUsername(String username){
        String sql = "select video_records.*,users.username from video_records,users where video_records.uid=users.uid and users.username=? and video_records.enabled=1";
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList(sql,username);
        return (list);
    }

    public List<Map<String,Object>> queryByUsername(String username){//特权操作：要求本人或管理员才能执行
        String sql = "select video_records.*,users.username from video_records,users where video_records.uid=users.uid and users.username=?";
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList(sql,username);
        return (list);
    }

    public List<Map<String,Object>> queryByEnabled(String enabled){//特权操作：要求管理员才能执行
        String sql = "select video_records.*,users.username from video_records,users where video_records.uid=users.uid and video_records.enabled=?";
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList(sql,enabled);
        return (list);
    }

    public List<Map<String,Object>> queryByVid(String vid) {//这是一个准特权操作，这个vid传参入口不允许直接暴露给前端，必须由中间服务生成
        String sql = "select video_records.*,users.username from video_records,users where video_records.uid=users.uid and video_records.vid=?";
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList(sql,vid);
        return (list);
    }

    //自定义功能区
    public String insertByUID(String uid,String filename){//返回值是vid
        KeyHolder keyHolder=new GeneratedKeyHolder();
        String sql="insert into video_records (uid,enabled,filename) values (?,1,?)";
        PreparedStatementCreator preparedStatementCreator=con ->{
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, uid);
            ps.setString(2, filename);
            return(ps);
        };
        jdbcTemplate.update(preparedStatementCreator,keyHolder);
        Integer vid=keyHolder.getKey().intValue();
        return(vid.toString());
    }
}
