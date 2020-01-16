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
        // 1.通过SecurityManager工厂对象获取SecurityManager对象
        SecurityManager securityManager =
                new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
        // 2.将SecurityManager对象添加到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 3.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //4.获取Token令牌（由用户名，密码构成）
        AuthenticationToken successToken = new UsernamePasswordToken("admin", "123456");
        //5.1 登录成功操作
        subject.login(successToken);//success:true
        System.out.println("success:" + subject.isAuthenticated());
        //5.2 用户名错误
        AuthenticationToken userErrorToken = new UsernamePasswordToken("admin123", "123456");
        subject.login(userErrorToken);//抛org.apache.shiro.authc.UnknownAccountException异常
        //5.3 密码错误
        AuthenticationToken pwdErrorToken = new UsernamePasswordToken("admin", "123");
        subject.login(pwdErrorToken);//抛org.apache.shiro.authc.IncorrectCredentialsException异常
        }

    }
