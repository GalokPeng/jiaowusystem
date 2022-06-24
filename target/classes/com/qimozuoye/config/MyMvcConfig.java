package com.qimozuoye.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *@Configuration用于定义配置类，可替换xml配置文件，
 * 被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
 * 并用于构建bean定义，初始化Spring容器。
 * 注意：@Configuration注解的配置类不可以是final类型、匿名类、嵌套的configuration必须是静态类
 *========================================================================================
 * 用@Configuration加载spring
 * 1.1、@Configuration配置spring并启动spring容器
 * 1.2、@Configuration启动容器+@Bean注册Bean
 * 1.3、@Configuration启动容器+@Component注册Bean
 * 1.4、使用 AnnotationConfigApplicationContext 注册 AppContext 类的两种方法
 * 1.5、配置Web应用程序(web.xml中配置AnnotationConfigApplicationContext)
 * ==========================================================================
 * @Configuation等价于<Beans></Beans>
 *  @Bean等价于<Bean></Bean>
 *  @ComponentScan等价于<context:component-scan base-package=”com.dxz.demo”/>
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    //视图跳转控制器addViewControllers()
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("sign");
        registry.addViewController("/sign.html").setViewName("sign");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/dashboard.html").setViewName("dashboard");
        registry.addViewController("/em.html").setViewName("em");
        registry.addViewController("/st.html").setViewName("st");
        registry.addViewController("/tea.html").setViewName("tea");
        registry.addViewController("/cl.html").setViewName("cl");
        registry.addViewController("/cou.html").setViewName("cou");

    }
    @Bean//用@Bean标注方法等价于XML中配置的bean。
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginHandlerInterceptor())
            .addPathPatterns("/**")//addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
            //excludePathPatterns：用于设置不需要拦截的过滤规则
            .excludePathPatterns("/","/sign.html","/user/login","/css/*","/js/*","/assets/**","/em.html","/st.html","/tea.html","/cl.html","/cou.html");
    }
}
