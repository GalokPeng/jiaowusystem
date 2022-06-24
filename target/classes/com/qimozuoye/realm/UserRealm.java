package com.qimozuoye.realm;

import com.qimozuoye.pojo.GuestUser;
import com.qimozuoye.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
//    @Autowired
//    UserMapper userMapper;

    @Resource
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权执行");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        GuestUser guser = (GuestUser) subject.getPrincipal();
        System.out.println("用户信息：" + subject.getPrincipal());
        GuestUser dbguser = userService.findById(guser.getId());
        info.addStringPermission(dbguser.getPerms());
        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证执行");

        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取登录的用户
        GuestUser user = userService.findByName(token.getUsername());

        if (user == null) {
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");


    }
}
