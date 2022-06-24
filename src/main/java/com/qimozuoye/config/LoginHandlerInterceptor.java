package com.qimozuoye.config;

import org.springframework.web.servlet.HandlerInterceptor;

//springMVC:HandlerInterceptor拦截器的使用
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
   /* @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取登录的名字
        Object loginUser = request.getSession().getAttribute("loginUser");
        //获取密码
        Object password = request.getSession().getAttribute("password");
        //判断是否为空，如果为空回传消息并跳转回登录页面，否则执行下一个拦截器或者处理器
        if (loginUser=="admin"&&password=="123456"){
            request.setAttribute("msg","请登录");
            //跳转回登录页面
            request.getRequestDispatcher("/sign.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }*/
}
