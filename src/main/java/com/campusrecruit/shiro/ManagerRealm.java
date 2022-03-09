package com.campusrecruit.shiro;


import com.campusrecruit.pojo.DO.Manager;
import com.campusrecruit.service.ManagerService;
import com.campusrecruit.utils.ApplicationContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

public class ManagerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("manager");
        return  simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String managerId = (String) authenticationToken.getPrincipal();
        ManagerService managerService = (ManagerService) ApplicationContextUtil.getBean("managerServicelmpl");
        Manager manager = managerService.selectById(Integer.valueOf(managerId));
        System.out.println(manager);
        if(!ObjectUtils.isEmpty(manager)){
            return  new SimpleAuthenticationInfo(managerId,manager.getPassword(), ByteSource.Util.bytes(manager.getSalt()),this.getName());
        }
        return null;
    }
}
