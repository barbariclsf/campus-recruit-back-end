package com.campusrecruit.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /**
     * 创建ShiroFilterFantoryBean
     */
    @Bean
    public ShiroFilterFactoryBean ShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDeafDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm, @Qualifier("managerRealm") ManagerRealm managerRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());
        securityManager.setSessionManager(sessionManager());
        //添加多个realm
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(managerRealm);

        UserModularRealmAuthorizer userModularRealmAuthorizer = new UserModularRealmAuthorizer();
        userModularRealmAuthorizer.setRealms(realms);
        securityManager.setAuthorizer(userModularRealmAuthorizer);
        securityManager.setRealms(realms);
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setGlobalSessionTimeout(86400000);
        SimpleCookie cookie = new SimpleCookie("userCookie");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        mySessionManager.setSessionIdCookie(cookie);
        return mySessionManager;
    }


    /**
     * userRealm
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    /**
     * managerReaml
     *
     * @return
     */
    @Bean(name = "managerRealm")
    public ManagerRealm getManagerRealm() {
        ManagerRealm managerRealm = new ManagerRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置算法为MD5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        managerRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return managerRealm;
    }


    /**
     * 系统自带的Realm管理，主要针对多realm
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        //自己重写的ModularRealmAuthenticator
        MyModularRealmAuthenticator modularRealmAuthenticator = new MyModularRealmAuthenticator();
        //多realm认证策略，至少一个成功
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



}
