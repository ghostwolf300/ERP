package org.erp.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.erp.exception.InitialPasswordException;

@Component
public class ERPAuthFailureHandler implements AuthenticationFailureHandler {
	
	
	public static final int BAD_CREDENTIALS=1;
	public static final int ACCOUNT_DISABLED=2;
	public static final int ACCOUNT_EXPIRED=3;
	public static final int ACCOUNT_LOCKED=4;
	public static final int INITIAL_PASSWORD=5;
	public static final int OTHER_ERROR=99;
	
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		handle(request,response,exception);

	}
	
	protected void handle(HttpServletRequest request,HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException{
		
		String failureMessage=exception.getMessage();
		String username;
		String targetUrl;
		
		if(exception instanceof BadCredentialsException) {
			username=exception.getMessage();
			targetUrl="/login?error=true&type="+BAD_CREDENTIALS;
		}
		else if(exception.getCause()!=null && exception.getCause() instanceof DisabledException) {
			username=exception.getCause().getMessage();
			targetUrl="/login?error=true&type="+ACCOUNT_DISABLED;
		}
		else if(exception.getCause()!=null && exception.getCause() instanceof AccountExpiredException) {
			username=exception.getCause().getMessage();
			targetUrl="/login?error=true&type="+ACCOUNT_EXPIRED;
		}
		else if(exception.getCause()!=null && exception.getCause() instanceof LockedException) {
			username=exception.getCause().getMessage();
			targetUrl="/login?error=true&type="+ACCOUNT_LOCKED;
		}
		else if(exception.getCause()!=null && exception.getCause() instanceof InitialPasswordException) {
			System.out.println("Failure Handler: Initial Password");
			username=exception.getCause().getMessage();
			targetUrl="/changePassword";
		}
		else {
			targetUrl="/login?error=true&type="+OTHER_ERROR;
		}
		
		//response.sendRedirect("/login?error=true");
		request.getRequestDispatcher(targetUrl).forward(request, response);
		//redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	

}
