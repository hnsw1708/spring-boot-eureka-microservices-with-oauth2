package com.iot.demo.api.smarthomesresourceserver.security;

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
        // set up desired converter
        converter.setJwtGrantedAuthoritiesConverter(new IdentityServerRoleConverter());

        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/smart-homes/**")
                        .hasAuthority("ROLE_iot_developer")
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(server -> server
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(converter))); // applying JWT Authentication converter

        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // WARNING! You can not have CORS details on both sides, API Gateway and Resource Server!
        // code below to the end of the class, is an option if we are going around API Gateway,
        // and directly requesting resource with JavaScript SPA from resource server

//        http
//                .cors().and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/smart-homes/**")
//                .hasAuthority("ROLE_iot_developer")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(converter); // applying JWT Authentication converter

    }

    // this bean will be automatically injected at runtime to Spring Security configuration
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource()
//    {
//        final CorsConfiguration corsConfiguration = new CorsConfiguration();
//        // set allowed options
//        corsConfiguration.setAllowedOrigins(List.of("*"));
//        corsConfiguration.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.POST.name()));
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        // register CORS configuration object with URL path - UrlBasedCorsConfigurationSource
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }
}

