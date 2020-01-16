package com.oasis.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {
    //权限授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //获取用户名、密码
        String username = (String) authenticationToken.getPrincipal();//principal[ˈprɪnsəpl] 主角
        //TODO: 正常逻辑此处应该根据账号去数据库中查询，相当于Dao层调取数据
        // 此处设定账号为 admin 密码123456
        if(!"admin".equals(username)){
            return null;//shrio没有获得有效验证信息，则抛出UnknownAccountException异常
        }
        String password="123456";//测试需要，直接为password赋值
        //MyRealm中只完成了账号的认证。密码认证还是在AuthenticatingRealm中完成的，
        // 只是我们在自定义Realm中完成了密码的设置
        //重新封装认证信息，相当于封装从Dao层取得的数据，realName参数相当于key值
        AuthenticationInfo info = new SimpleAuthenticationInfo(username,password,"myrealm");
        return info;
    }
}
