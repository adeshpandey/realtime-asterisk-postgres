package me.adesh.asterisk.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // ...
    log.info("Hi....");
    http
        .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
        .oauth2ResourceServer().jwt();
    http.cors();
  }
}
