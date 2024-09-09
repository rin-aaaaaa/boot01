package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // 메모리상 인증정보 등록 => 테스트 전용
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		UserDetails user = User.builder()
					.username("user1")
					.password(passwordEncoder().encode("1234"))
					.roles("USER") // ROLE_USER
				 // .authoritise("ROLE_USER")
					.build();
		
		UserDetails admin = User.builder()
				.username("admin1")
				.password(passwordEncoder().encode("1234"))
				//.roles("ADMIN") // ROLE_ADMIN
			    .authorities("ROLE_ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	// 인증 및 인가
	@Bean
	SecurityFilterChain filterchain(HttpSecurity http) throws Exception{
		// 적용될 Security 설정
		// => URI 에 적용될 권한
		http.authorizeHttpRequests(authrize -> authrize.requestMatchers("/", "/all").permitAll()
													   //.requestMatchers("/user/**").hasRole("USER")
													   .requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
													   .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
													   .anyRequest().authenticated() 
													   )
		.formLogin(formLogin -> formLogin.defaultSuccessUrl("/all"))
		.logout(logout -> logout.logoutSuccessUrl("/all"));
		
		return http.build();
	}

}
