package com.alura.hotel.latam.dao;

import com.alura.hotel.latam.modelo.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UsuarioImpl implements UsuarioDAO {

	private EntityManager em;

	public UsuarioImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Usuario buscarPorNombreUsuario(String nombreUsuario) {
		try {
			TypedQuery<Usuario> query = this.em.createNamedQuery("Usuario.buscarPorNombreUsuario", Usuario.class);
			query.setParameter("nombreUsuario", nombreUsuario);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
