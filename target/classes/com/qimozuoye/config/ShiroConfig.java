package com.qimozuoye.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.qimozuoye.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean 3
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        //班级
        filterMap.put("/clas","authc");
        filterMap.put("/cla","perms[admin]");
        filterMap.put("/cla/*", "perms[admin]");
        filterMap.put("/delecla/*","perms[admin]");
        //员工
        filterMap.put("/emps","authc");
        filterMap.put("/emp","perms[admin]");
        filterMap.put("/emp/*", "perms[admin]");
        filterMap.put("/delemp/*","perms[admin]");
        //学生
        filterMap.put("/stus","authc");
        filterMap.put("/stu","perms[admin]");
        filterMap.put("/stu/*", "perms[admin]");
        filterMap.put("/delstu/*","perms[admin]");
        //教师
        filterMap.put("/teacs","authc");
        filterMap.put("/teac","perms[admin]");
        filterMap.put("/teac/*", "perms[admin]");
        filterMap.put("/delteac/*","perms[admin]");
        //查询
        filterMap.put("/selestu/*","authc");
        filterMap.put("selectStu/*","authc");
        filterMap.put("/seleteac/*","authc");
        filterMap.put("selecla/*","authc");
        //用户
        filterMap.put("/users","authc");
        filterMap.put("/users/*","perms[admin]");
//        filterMap.put("/users/*","perms[teac]");
//        filterMap.put("/users/*","perms[stu]");
        filterMap.put("/user/logout","logout");

        //网页
        filterMap.put("/commons","perms[admin]");
        filterMap.put("/commons/*", "perms[admin]");
        //课程
        filterMap.put("/cours","authc");
        filterMap.put("/cour","perms[admin]");
        filterMap.put("/cour/*", "perms[admin]");
        filterMap.put("/delecour/*","perms[admin]");
//        filterMap.put("dashboard.html","perms[admin]");



        //设置未授权提示界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager 2
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    /**
     * 创建Realm 1
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
