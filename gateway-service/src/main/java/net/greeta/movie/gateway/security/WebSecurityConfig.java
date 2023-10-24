package net.greeta.movie.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {

        return http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .pathMatchers(HttpMethod.GET,"/webjars/**").permitAll()
                        .pathMatchers(HttpMethod.GET,"/", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .pathMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
                        .pathMatchers(HttpMethod.GET,"/v3/api-docs/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "/erp", "/erp/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/movie/userextras/me").hasAnyRole(MOVIE_MANAGER, MOVIE_USER)
                        .pathMatchers(HttpMethod.GET,"/movie/v3/api-docs/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/movie", "/movie/**").permitAll()
                        .pathMatchers("/movie/*/comments").hasAnyRole(MOVIE_MANAGER, MOVIE_USER)
                        .pathMatchers("/movie", "/movie/**").hasRole(MOVIE_MANAGER)
                        .anyExchange().authenticated()
                        .and()
                         .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
                        .oauth2ResourceServer().jwt()
                        .jwtAuthenticationConverter(new ReactiveJwtAuthenticationConverterAdapter(jwtAuthConverter))
                )
                //.csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
                .build();
    }

    @Bean
    WebFilter csrfWebFilter() {
        // Required because of https://github.com/spring-projects/spring-security/issues/5766
        return (exchange, chain) -> {
            exchange.getResponse().beforeCommit(() -> Mono.defer(() -> {
                Mono<CsrfToken> csrfToken = exchange.getAttribute(CsrfToken.class.getName());
                return csrfToken != null ? csrfToken.then() : Mono.empty();
            }));
            return chain.filter(exchange);
        };
    }

    public static final String MOVIE_MANAGER = "MOVIE_MANAGER";
    public static final String MOVIE_USER = "MOVIE_USER";
}
