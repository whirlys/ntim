package com.whirly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.whirly.model.User;

/**
 * 登录拦截器
 * 
 * @author TDW
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();

		if (requestURI.indexOf("login") >= 0) {
			// 如果是公开 地址则放行
			return true;
		}

		// 判断是否已登录
		User loginUser = (User) request.getSession().getAttribute("user");
		if (loginUser == null) {
			// 未登录
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}
		/*logger.info("user {} visit uri : {}.", loginUser.getUserId(), requestURI);*/
		return true;
		// return super.preHandle(request, response, handler);
	}
}
