package com.service.point.config;

import com.service.point.filter.HeaderFilter;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final URI clientPath = URI.create("/api/client");
    private final URI clientAddressPath = URI.create("/api/client/address");
    private final URI clientPhonePath = URI.create("/api/client/phone");
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Bean
    @SuppressWarnings("java:S4502") // Be sure to disable csrf
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(req -> req.anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new HeaderFilter(List.of(
//
//                                new HeaderFilter.RouteConfig(URI.create("/api/point/order"), HttpMethod.POST.name(), Collections.emptyList()),
//                                new HeaderFilter.RouteConfig(URI.create("/api/point/refund"), HttpMethod.POST.name(), Collections.emptyList()),
//                                new HeaderFilter.RouteConfig(URI.create("/api/point/policy/register"), HttpMethod.POST.name(), List.of(ADMIN_ROLE)),
//                                new HeaderFilter.RouteConfig(URI.create("/api/point/policy"), HttpMethod.GET.name(), List.of(ADMIN_ROLE)),
//                                new HeaderFilter.RouteConfig(URI.create("/api/point/policy/{pointPolicyId}"), HttpMethod.GET.name(), List.of(ADMIN_ROLE)),
//                    new HeaderFilter.RouteConfig(URI.create("/api/point/policy/modify"), HttpMethod.PUT.name(), List.of(ADMIN_ROLE)),
//                    new HeaderFilter.RouteConfig(URI.create("/api/point/policy/active"), HttpMethod.PUT.name(), List.of(ADMIN_ROLE)),
//                    new HeaderFilter.RouteConfig(URI.create("/api/point/use/payment"), HttpMethod.POST.name(), Collections.emptyList()),
//                    new HeaderFilter.RouteConfig(URI.create("/api/point/use/refund"), HttpMethod.POST.name(), Collections.emptyList()),
//                    new HeaderFilter.RouteConfig(URI.create("/api/point/myPage/use"), HttpMethod.GET.name(), Collections.emptyList()),
//                    new HeaderFilter.RouteConfig(URI.create("/api/point/adminPage/use"), HttpMethod.GET.name(), List.of(ADMIN_ROLE)),
//                    new HeaderFilter.RouteConfig(URI.create("/api/point"), HttpMethod.GET.name(), Collections.emptyList())
                        )
                ), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
