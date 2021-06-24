package com.fuqianshan.VideoWebsite.Test;

import java.util.List;
import java.util.Map;

import com.fuqianshan.VideoWebsite.DAO.AuthoritiesDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TestService")
public class TestService {

    @Autowired
    AuthoritiesDAO authoritiesDAO;

    public void simpleTest(){
        System.out.println("--------Starting Simple Test--------");

        List<Map<String,Object>>list;
        list=authoritiesDAO.queryByUID("3");
        System.out.println(list);
        list=authoritiesDAO.queryByUsername("admin");
        System.out.println(list);
		System.out.println("--------Simple Test finished--------");
    }
}
