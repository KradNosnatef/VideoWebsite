package com.fuqianshan.VideoWebsite.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fuqianshan.VideoWebsite.DAO.CommentsDAO;
import com.fuqianshan.VideoWebsite.Entity.CommentEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("CommentService")
public class CommentService {
    @Autowired
    CommentsDAO commentsDAO;

    public boolean accessabilityCheck(String cid, UserService userService) {// 返回本session是否有权限编辑cid对应的评论
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userService.adminCheck())
            return (true);

        String uid = userService.queryUIDByUsername(username);
        if (commentsDAO.queryByCID(cid).get(0).get("uid").toString().compareTo(uid) == 0)
            return (true);

        return (false);
    }

    public void pushComment(String vid, String value, UserService userService) {// 以本session对应的username发表评论
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String uid = userService.queryUIDByUsername(username);
        commentsDAO.pushComment(uid, vid, value);
    }

    public void editComment(String cid, String value){// 特权操作！修改对应的cid评论，调用前应作可达性检查

        commentsDAO.editComment(cid, value);
    }

    public void deleteComment(String cid){//特权操作！调用前应作可达性检查
        commentsDAO.deleteComment(cid);
    }

    public List<CommentEntity> listCommentByVID(String vid,UserService userService){//特权操作！调用前应当检查用户是否可以read该vid
        List<CommentEntity> list = new ArrayList<CommentEntity>();
        CommentEntity commentEntity;
        List<Map<String, Object>> lMaps = commentsDAO.queryByVID(vid);
        for (int i = 0; i <= lMaps.size() - 1; i++) {
            boolean accessability=accessabilityCheck(lMaps.get(i).get("cid").toString(), userService);
            commentEntity = new CommentEntity(lMaps.get(i),accessability);
            list.add(commentEntity);
        }
        return (list);
    }
}
