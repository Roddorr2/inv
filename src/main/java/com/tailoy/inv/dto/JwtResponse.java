package com.tailoy.inv.dto;

public class JwtResponse {
	private String token;
	private String correo;
	private String cargo;
	
	public JwtResponse(String token, String correo, String cargo) {
		this.token = token;
		this.correo = correo;
		this.cargo = cargo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
