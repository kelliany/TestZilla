package com.trunkshell.testzilla.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.trunkshell.testzilla.model.User;
import com.trunkshell.testzilla.service.UserService;

/**
 * 视图的切面类.
 * 在加载页面前加载已登录用户的个人信息. 
 * @author Xie Haozhe
 */
@Aspect
public class ViewAspect {
	/**
	 * 加载已登录用户的个人信息及答题情况.
	 * @param proceedingJoinPoint - ProceedingJoinPoint对象
	 * @param session - HttpSession对象
	 * @return 一个包含预期视图的ModelAndView对象
	 * @throws Throwable - ResourceNotFound异常
	 */
	@Around(value = "execution(* com.trunkshell.testzilla.controller.*.*View(..)) && args(.., request)")
	public ModelAndView getUserProfile(ProceedingJoinPoint proceedingJoinPoint, HttpServletRequest request) throws Throwable {
		ModelAndView view = null;
		view = (ModelAndView) proceedingJoinPoint.proceed();
		
		HttpSession session = request.getSession();
		boolean isLoggedIn = isLoggedIn(session);
		if ( isLoggedIn ) {
			long uid = (Long)session.getAttribute("uid");
	    	User user = userService.getUserUsingUid(uid);
            
	    	view.addObject("isLogin", isLoggedIn)
            	.addObject("user", user);
        }
		view.addObject("language", session.getAttribute("language"));
		return view;
	}
	
	/**
     * 检查用户是否已经登录.
     * @param session - HttpSession 对象
     * @return 用户是否已经登录
     */
    private boolean isLoggedIn(HttpSession session) {
        Boolean isLoggedIn = (Boolean)session.getAttribute("isLoggedIn");
        if ( isLoggedIn == null || !isLoggedIn.booleanValue() ) {
            return false;
        }
        return true;
    }
    
    /**
     * 自动注入的UserService对象.
     */
    @Autowired
    UserService userService;
}
