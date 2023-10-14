package com.aos.infrustructure.config;

import com.aos.infrustructure.security.AuthoritiesService;
import com.aos.repo.entity.User;
import com.aos.repo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class AuthConfig {

  @Autowired private UserRepo userRepo;

  @Value("excludePaths")
  private String[] excludePaths;

  @Autowired private AuthoritiesService authoritiesService;

  @Autowired private AuthExceptionHandler authExceptionHandler;

  @Autowired private JWTFilter jwtFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // 请求授权
    http.cors()
        .and()
        .csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(authExceptionHandler)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/login", "/register", "/loginWechat/**", "/loginGoogle/**", "/test*","/version")
        .permitAll()
        .anyRequest()
        .authenticated();
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      User user =
          userRepo
              .findOneByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("auth.failed.not.exist"));

      UserPO userDetail =
          new UserPO(
              user.getId(),
              user.getUsername(),
              user.getPassword(),
              user.getEnable(),
              true,
              true,
              true,
              authoritiesService.authorities());
      return userDetail;
    };
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    authenticationProvider.setHideUserNotFoundExceptions(false);
    return authenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(authenticationProvider());
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
