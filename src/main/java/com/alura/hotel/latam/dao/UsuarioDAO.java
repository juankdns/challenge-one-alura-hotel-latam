package com.alura.hotel.latam.dao;

import com.alura.hotel.latam.modelo.Usuario;

public interface UsuarioDAO {

	Usuario buscarPorNombreUsuario(String nombreUsuario);
}
