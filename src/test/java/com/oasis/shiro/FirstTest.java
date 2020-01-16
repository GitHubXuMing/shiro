package com.oasis.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;

public class FirstTest {
    @Test
    public void test() {
        // 1.获取SecurityManager工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2.通过Factory对象获取SecurityManager对象
        SecurityManager securityManager = factory.getInstance();
        // 3.将SecurityManager对象添加到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 4.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken successToken = new UsernamePasswordToken("admin", "123456");
        // 登录操作
        subject.login(successToken);//success:true
        // 获取登录的状态
        System.out.println("success:" + subject.isAuthenticated());
        AuthenticationToken errorToken = new UsernamePasswordToken("admin", "123");
        subject.login(errorToken);//抛异常
    }
}