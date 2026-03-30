package com.iot.demo.api.wearables.resource.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        final JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new IdentityServerRoleConverter());

        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/wearables/**")
//                .hasAuthority("SCOPE_profile")
                        .hasRole("iot_developer")
//                .hasAnyRole("iot_developer", "user")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(server -> server
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(converter)));

        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }
}

