package com.example.demo.shiro;

import java.util.LinkedHashMap;
import java.util.Map;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
	@Autowired
	private MyShiroRealm myShiroRealm;
	
    @Bean
    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登陆URL 
        // 如果不设置值，默认会寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/user/login.html");
        // 设置无权限时跳转的URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/login.html");

        
        // 设置拦截器--进行权限认证
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //游客，开发权限,//静态资源
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/**.css", "anon");
        filterChainDefinitionMap.put("/**.ico", "anon");
        filterChainDefinitionMap.put("/**.js", "anon");
        filterChainDefinitionMap.put("/**.jpg", "anon");
        filterChainDefinitionMap.put("/**.png", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/touxiang/**", "anon");
        //开放接口
        filterChainDefinitionMap.put("/login/**", "anon");
        //静态页面
        filterChainDefinitionMap.put("/user/**", "anon");
        //用户，需要角色权限 “user”
        //filterChainDefinitionMap.put("/xiaobo/**", "roles[{1,2,3}]");
        //管理员，需要角色权限 “admin”
        //filterChainDefinitionMap.put("/admin/**", "roles[{1,2,3}]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login/login", "anon");
        
        //其余接口一律拦截        
        //该这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入securityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }
    
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME); // 散列算法
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS); // 散列次数
        return hashedCredentialsMatcher;
    }
    @Bean
    public PasswordHelper passwordHelper() {
        return new PasswordHelper();
    }
    
  //注解生效1
  	@Bean
  	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
  	AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =new AuthorizationAttributeSourceAdvisor();
  	    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
  	    return authorizationAttributeSourceAdvisor;
  	}
  //注解生效2
  	@Bean
  	@ConditionalOnMissingBean
  	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
  	DefaultAdvisorAutoProxyCreator app=new DefaultAdvisorAutoProxyCreator();
  	    app.setProxyTargetClass(true);
  	    return app;
  	}
    /**
     * 自定义身份认证 realm;
     * 加上 @Bean 注解，目的是注入Realm
     */
//    @Bean
//    public MyShiroRealm myRealm() {
//        return new MyShiroRealm();
//    }
}