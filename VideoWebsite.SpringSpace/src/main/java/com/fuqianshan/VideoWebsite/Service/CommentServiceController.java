package com.fuqianshan.VideoWebsite.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fuqianshan.VideoWebsite.Entity.CommentEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentServiceController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;

    @RequestMapping("/Xapi/pushComment")
    public @ResponseBody JSONPObject IOpushComment(@RequestParam(value = "value") String value,
            @RequestParam(value = "vid") String vid, HttpServletResponse httpServletResponse) {

        commentService.pushComment(vid, value, userService);
        return (IOToolKits.myResponseGenerator(httpServletResponse, "success", "_IOpushComment"));
    }

    @RequestMapping("/Xapi/editComment")
    public @ResponseBody JSONPObject IOeditComment(@RequestParam(value = "value") String value,
            @RequestParam(value = "cid") String cid, HttpServletResponse httpServletResponse) {

        if (commentService.accessabilityCheck(cid, userService)) {
            commentService.editComment(cid, value);
            return (IOToolKits.myResponseGenerator(httpServletResponse, "success", "_IOeditComment"));

        }
        return (IOToolKits.myResponseGenerator(httpServletResponse, "denied", "_IOeditComment"));

    }

    @RequestMapping("/Xapi/deleteComment")
    public @ResponseBody JSONPObject IOdeleteComment(@RequestParam(value = "cid") String cid,
            HttpServletResponse httpServletResponse) {

        if (commentService.accessabilityCheck(cid, userService)) {
            commentService.deleteComment(cid);
            return (IOToolKits.myResponseGenerator(httpServletResponse, "success", "_IOdeleteComment"));
        }
        return (IOToolKits.myResponseGenerator(httpServletResponse, "denied", "_IOdeleteComment"));
    }

    @RequestMapping("/Xapi/listCommentByVID")
    public @ResponseBody JSONPObject IOlistCommentByVID(HttpServletResponse httpServletResponse,@RequestParam(value = "vid")String vid){
        if(!(videoService.accessabilityCheck(vid, userService, "read")))return(null);
        List<CommentEntity> response=commentService.listCommentByVID(vid,userService
        );

        return(IOToolKits.myResponseGenerator(httpServletResponse, response, "_IOlistCommentByVID"));
    }
}
