package org.erp.config;



import org.erp.component.ERPAuthFailureHandler;
import org.erp.component.ERPAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * @Autowired private DataSource dataSource;
	 */
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private ERPAuthSuccessHandler successHandler;
	
	@Autowired
	private ERPAuthFailureHandler failureHandler;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(
                		"/",
                		"/home",
                		"/css/**",
                		"/js/**",
                		"/img/**").permitAll()
                .antMatchers("/users").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            	.formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
            .and()
            	.logout()
                .permitAll();
    }
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}
	
	/*
	 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
	 * auth) throws Exception{
	 * 
	 * auth.jdbcAuthentication().dataSource(dataSource)
	 * .usersByUsernameQuery("select username,password,enabled from user where username=?"
	 * )
	 * .authoritiesByUsernameQuery("select username,role_name from user join user_role on user.id=user_role.user_id join role on user_role.role_id=role.id where username=?"
	 * ); }
	 */
	
	
}
