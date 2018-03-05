package com.hcl.tfg.tools.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;


import reactor.core.publisher.Mono;


@EnableWebFluxSecurity
class SecurityConfig {

    @Value(("${spring.security.user.name}"))
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Value(("${spring.security.user.password}"))
    private String password;

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/config-values").permitAll()
                .pathMatchers(HttpMethod.GET, "/config-values/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/config-values").permitAll()
                .pathMatchers(HttpMethod.POST, "/config-values/**").permitAll()
                .pathMatchers(HttpMethod.DELETE, "/config-values/**").hasRole("ADMIN")
                //*").access(this::currentUserMatchesPath)
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                .build();
    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
        return authentication
                .map(a -> context.getVariables().get(username).equals(a.getName()))
                .map(granted -> new AuthorizationDecision(granted));
    }



    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password(password).roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder().username(username).password(password).roles("USER","ADMIN").build();
        return new MapReactiveUserDetailsService(user, admin);
    }


}
