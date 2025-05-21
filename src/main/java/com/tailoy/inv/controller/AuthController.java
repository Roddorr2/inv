package com.tailoy.inv.controller;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tailoy.inv.model.Usuario;
import com.tailoy.inv.repository.UsuarioRepository;
import com.tailoy.inv.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;
	private final UsuarioRepository usuarioRepository;
	
	public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
		this.usuarioRepository = usuarioRepository;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String, String> loginData) {
		String correo = loginData.get("correo");
		String contrasena = loginData.get("contrasena");
		
		authManager.authenticate(new UsernamePasswordAuthenticationToken(correo, contrasena));
		
		String token = jwtUtil.generateToken(correo);
		Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();
		
		return Map.of(
				"token", token,
				"cargo", usuario.getCargo().getNombre(),
				"nombre", usuario.getNombre()
				);
	}
}
