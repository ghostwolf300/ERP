package org.erp.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class ERPAuthFailureHandler implements AuthenticationFailureHandler {
	
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		handle(request,response,exception);

	}
	
	protected void handle(HttpServletRequest request,HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException{
		
		System.out.println("Failure handler");
		
		
		String failureMessage=exception.getMessage();
		String username=request.getParameter("username");
		String targetUrl;
			
		if(failureMessage.equals("initial_pw")) {
			//Redirect to change password
			//targetUrl="/login/changePassword?username="+username;
			targetUrl="/login?error=true";
			System.out.println("Initial pw redirecting...");
		}
		else if(failureMessage.equals("locked")) {
			//display error message on login screen
			targetUrl="/login?error=true";
		}
		else {
			//other error. display error message.
			targetUrl="/login?error=true";
		}
		
		//response.sendRedirect("/login?error=true");
		request.getRequestDispatcher(targetUrl).forward(request, response);
		//redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	

}
