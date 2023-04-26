package com.alura.hotel.latam.bo;

import com.alura.hotel.latam.dao.UsuarioDAO;
import com.alura.hotel.latam.modelo.Usuario;
import com.alura.hotel.latam.vo.UsuarioVO;

public class UsuarioBO {

	private UsuarioDAO usuarioDAO;

	public UsuarioBO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public boolean autenticar(UsuarioVO usuarioVO) {
		Usuario usuario = this.usuarioDAO.buscarPorNombreUsuario(usuarioVO.getNombreUsuario());

		if (usuario != null && usuario.getContrasena().equals(usuarioVO.getContrasena())) {
			return true;
		}
		return false;
	}

}
