package com.oasis.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
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
        //4.生成Token令牌（由用户名，密码构成）
        AuthenticationToken token = new UsernamePasswordToken("admin", "123456");
        //5.1 登录成功操作
        try {
            subject.login(token);//将token令牌与MyRealm取出的认证信息进行验证
        }catch(UnknownAccountException uae){
            System.out.println("账户错误");
        }catch(IncorrectCredentialsException ice){
            System.out.println("密码错误");}
        System.out.println("success:" + subject.isAuthenticated());
    }

    }
