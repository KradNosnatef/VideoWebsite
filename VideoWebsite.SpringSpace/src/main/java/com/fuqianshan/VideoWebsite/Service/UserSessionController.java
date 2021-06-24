package com.fuqianshan.VideoWebsite.Service;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fuqianshan.VideoWebsite.Test.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSessionController {

    @Autowired
    UserService userService;
    @Autowired
    TestService testService;

    @RequestMapping(value = "/Xapi/logout", method = RequestMethod.GET)
    public @ResponseBody JSONPObject IOlogout(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("X-Content-Type-Options", "");
        String response;

        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext();

        response = "蛋疼，SSec5的注销接口和3不一样，一直没搞懂这个工况下怎么注销";

        JSONPObject jsonpObject = new JSONPObject("callback_IOlogout", response);
        return (jsonpObject);
    }

    @RequestMapping(value = "/Xapi/getUsername") // 获取登录状态
    @PreAuthorize("permitAll()")
    public @ResponseBody JSONPObject IOgetUsername(HttpServletResponse httpServletResponse) {
        String responseBody;
        responseBody = SecurityContextHolder.getContext().getAuthentication().getName();

        return (IOToolKits.myResponseGenerator(httpServletResponse, responseBody, "_IOgetUsername"));
    }

    @RequestMapping(value = "/Xapi/login") // 拉起登录窗口
    public @ResponseBody JSONPObject IOlogin(HttpServletResponse httpServletResponse) {
        String responseBody;
        responseBody = SecurityContextHolder.getContext().getAuthentication().getName();

        return (IOToolKits.myResponseGenerator(httpServletResponse, responseBody, "_IOlogin"));
    }

    @RequestMapping(value = "/Xapi/regist") // 注册API
    @PreAuthorize("permitAll()")
    public @ResponseBody JSONPObject IOregist(HttpServletResponse httpServletResponse,
            @RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("authKey") String authKey) {

        int response = userService.register(username, password, authKey);
        return (IOToolKits.myResponseGenerator(httpServletResponse, response, "_IOregist"));
    }

    @RequestMapping("/Xapi/adminCheck") // 看看劳资是不是管理员
    public @ResponseBody JSONPObject IOadminCheck(HttpServletResponse httpServletResponse) {
        boolean response = userService.adminCheck();
        return (IOToolKits.myResponseGenerator(httpServletResponse, response, "_IOadminCheck"));
    }

    @RequestMapping("/Xapi/SimpleTest") // 测试用的入口
    public void runSimpleTest() {
        testService.simpleTest();
    }

}