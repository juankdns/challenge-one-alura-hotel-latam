package com.alura.hotel.latam.vo;

import com.alura.hotel.latam.dto.UsuarioDTO;

public class UsuarioVO {

	private Long id;
	private String nombreUsuario;
	private String contrasena;

	public UsuarioVO(UsuarioDTO usuarioDTO) {
		this.nombreUsuario = usuarioDTO.getNombreUsuario();
		this.contrasena = usuarioDTO.getContrasena();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
