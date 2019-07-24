package org.erp.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.erp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class ERPAuthSuccessHandler implements AuthenticationSuccessHandler {
	
	
	@Autowired
	private UserService userService;
	
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request,response,authentication);
		clearAuthenticationAttributes(request);

	}
	
	protected void handle(HttpServletRequest request,HttpServletResponse response,Authentication auth) throws IOException, ServletException{
		String targetUrl=null;
		//if user has flag "initial password"
		if(isInitialPassword(auth)) {
			//remove all authorities/privileges
			//redirect to password change page
			targetUrl="/changePassword";
		}
		//else
		else {
			//redirect to "home"
			targetUrl="/home";
		}
		
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	protected String determineTargetUrl(Authentication auth){
		return "/home";
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
	
	protected boolean isInitialPassword(Authentication auth) {
		String username=auth.getName();
		return userService.isInitialPassword(username);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
