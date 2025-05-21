package com.tailoy.inv.config;

import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.UsuarioRepository;
import com.tailoy.inv.security.JwtAuthFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
        	Usuario usuario = usuarioRepository.findByCorreo(username)
        	        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            if (usuario == null || usuario.isEstado() == false) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }

            return User.builder()
                    .username(usuario.getCorreo())
                    .password(usuario.getContrasena())
                    .roles(usuario.getCargo().getNombre().toUpperCase().replace(" ", "_"))
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        return http
        		.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/almacen/**").hasRole("ALMACENERO")
                        .requestMatchers("/recibo/**").hasRole("ASISTENTE_DE_RECIBO")
                        .requestMatchers("/compras/**").hasRole("GERENTE_DE_COMPRAS")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
