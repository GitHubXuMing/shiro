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

import java.util.Arrays;

public class FirstTest {
    @Test
    public void test() {
        // 1.获取SecurityManager工厂对象,通过Factory对象获取SecurityManager对象
        SecurityManager securityManager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
        // 2.将SecurityManager对象添加到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 4.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("zhang", "123");
        // 登录操作
        System.out.println("****************************用户认证************************");
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账号出错...");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码出错...");
        }
        // 获取登录的状态
        System.out.println(subject.isAuthenticated());

        // 认证通过后进行角色验证
        System.out.println(subject.getPrincipal() + "是否具有role1角色:" + subject.hasRole("role1"));
        boolean[] types = subject.hasRoles(Arrays.asList("role1", "role2"));
        System.out.println(subject.getPrincipal() + "是否具有role1和role2角色:" + types[0] + "," + types[1]);
        // 验证权限
        System.out.println("****************************验证权限************************");
        System.out.println(subject.getPrincipal() + "是否具有user:create权限:" + subject.isPermitted("user:create"));
        System.out.println(subject.getPrincipal() + "是否具有user:delete角色:" + subject.isPermitted("user:delete"));
        boolean t = subject.isPermittedAll("user:create", "user:delete");
        System.out.println(subject.getPrincipal() + "是否具有user:create和user:delete的权限:" + t);
    }
}

