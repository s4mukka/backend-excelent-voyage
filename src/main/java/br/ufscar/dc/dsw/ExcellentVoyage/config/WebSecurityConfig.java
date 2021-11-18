package br.ufscar.dc.dsw.ExcellentVoyage.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.ExcellentVoyage.security.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
	}

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests()
				.antMatchers("/", "/error", "/login/**", "/index", "/styles/**", "/image/**", "/webjars/**").permitAll()
                .antMatchers("/cadastro/**", "/cliente/salvar", "/agencia/salvar", "/pacote/salvar").permitAll()
				.antMatchers("/cliente/home").hasRole("cliente")//precisa mudar
                .antMatchers("/agencia/home", "/pacote/salvar", "/pacote/adicionar").hasRole("agencia")//pecisa mudar
				.antMatchers("/admin/**", "/agencia/delete/**", "/cliente/delete/**").hasRole("admin")
				.antMatchers("/agencia/editar", "/cliente/editar").hasRole("admin")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll();
	}

}
