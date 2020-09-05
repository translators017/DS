package co.dataswitch.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	
	  @Override protected void configure(HttpSecurity http) throws Exception {
		  http
		  .authorizeRequests()
		  .antMatchers("/resources/**").permitAll()
          .antMatchers("/home/**").hasRole("ADMIN")
          .and()
          	.formLogin()
          	.loginProcessingUrl("/login")
          	.defaultSuccessUrl("/home", true)
          	.failureUrl("/login?error")
          	.loginPage("/")
          	.permitAll()
          .and()
          	.logout()
          	.logoutSuccessUrl("/login?logout")
          	.invalidateHttpSession(true)
          	.permitAll();
		  
		  //http.headers()
          //.contentSecurityPolicy("script-src 'self'");
	  }
	 


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
        		.withUser("user").password("user").roles("USER");
    }
}
