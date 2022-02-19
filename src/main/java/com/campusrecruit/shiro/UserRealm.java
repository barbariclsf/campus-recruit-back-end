package com.campusrecruit.shiro;



import com.campusrecruit.pojo.DO.User;
import com.campusrecruit.service.UserService;
import com.campusrecruit.utils.ApplicationContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;


public class UserRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        System.out.println("user执行授权" );
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("user");
        return  simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userId = (String) authenticationToken.getPrincipal();

        UserService userService = (UserService) ApplicationContextUtil.getBean("userServicelmpl");
        User user = userService.selectByUserId(Integer.valueOf(userId));
        System.out.println(user);
        if(!ObjectUtils.isEmpty(user)){
            return  new SimpleAuthenticationInfo(userId,user.getOpenId(),this.getName());
        }
        return null;
    }
}
