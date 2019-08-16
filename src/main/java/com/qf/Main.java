package com.qf;

import com.qf.pojo.UserInfo;
import com.qf.service.UserInfoService;
import com.qf.service.impl.UserInfoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by DELL on 2019/7/29.
 */
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-service.xml");
        UserInfoService bean = context.getBean(UserInfoService.class);
//        List<UserInfo> userInfos = bean.listAll();
//        System.out.println(userInfos);
        boolean b = bean.deleteUserById(2);
        System.out.println(b);
    }
}
