package com.oasis.shiro.realm;

import com.oasis.shiro.dao.pojo.SysPermission;
import com.oasis.shiro.dao.pojo.SysRole;
import com.oasis.shiro.dao.pojo.UserInfo;
import com.oasis.shiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserInfoService userInfoService;
    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        String username = (String)token.getPrincipal();//获取用户的输入的账号.
        String password = new String((char[])token.getCredentials());//获取密码
        System.out.println(username+"--->"+password);
        //通过username从数据库中查找 User对象
        //TODO:实际项目可以根据实际情况做缓存，Shiro也有时间间隔机制，2分钟内不会重复执行该方法，防止重复提交
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("userInfo="+userInfo);
        if(userInfo == null){return null; }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()),//salt盐值
                getName()  //自定义的RealmName
        );
        return authenticationInfo;
    }

    //角色权限设置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        for(SysRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }
}
