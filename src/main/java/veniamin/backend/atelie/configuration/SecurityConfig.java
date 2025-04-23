package veniamin.backend.atelie.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import veniamin.backend.atelie.filter.ExceptionHandlerFilter;
//import veniamin.backend.atelie.filter.RefreshTokenFilter;
//import veniamin.backend.atelie.filter.jwt.JwtTokenFilter;

import java.util.Arrays;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
//@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final JwtTokenFilter jwtTokenFilter;
//
//    private final RefreshTokenFilter refreshTokenFilter;
//
//    private final ExceptionHandlerFilter exceptionHandlerFilter;


//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PATCH", "DELETE", "PUT"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        configuration.applyPermitDefaultValues();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return
//                httpSecurity.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                        .addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                        .addFilterAfter(refreshTokenFilter, JwtTokenFilter.class)
//                        .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
//                        .exceptionHandling(Customizer.withDefaults())
//                        .authorizeHttpRequests(c ->
//                                c.requestMatchers("/admin/metrics/**").hasAuthority("ADMIN")
//                                        .requestMatchers(antMatcher(HttpMethod.GET, "/tasks/**")).authenticated()
//                                        .requestMatchers("/tasks/**").hasAuthority("ADMIN"))
//
//
//                        .cors(Customizer.withDefaults())
//                        .csrf(csrf -> csrf.disable())
//                        .logout(c -> c.invalidateHttpSession(true)
//                                .clearAuthentication(true))
//                        .sessionManagement(c -> c.maximumSessions(1))
//                        .authorizeHttpRequests((requests) -> requests //*
//                                .anyRequest().permitAll()) //*
//                        .build();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/authorize/**").permitAll()
                        .requestMatchers("/dashboard").hasAnyRole("CLIENT", "ADMIN", "CUTTER", "MANAGER")

                        .anyRequest().authenticated()
                )
//                .formLogin(form -> form
//                        .loginPage("/login").permitAll()
//                        .defaultSuccessUrl("/cutters", true)
//                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout").permitAll()
                );
        return http.build();
    }
//
//    @Bean
//    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
//        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
//    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//            .password(encoder.encode("adminpass"))
//            .roles("ADMIN")
//            .build());
//        manager.createUser(User.withUsername("manager")
//            .password(encoder.encode("managerpass"))
//            .roles("MANAGER")
//            .build());
//        manager.createUser(User.withUsername("tailor")
//            .password(encoder.encode("cutterpass"))
//            .roles("CUTTER")
//            .build());
//        return manager;
//    }

}