package com.alura.hotel.latam.dao;

import java.util.List;

import com.alura.hotel.latam.modelo.Reserva;
import com.alura.hotel.latam.vo.ReservaVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ReservaImpl implements ReservaDAO {

	private EntityManager em;

	public ReservaImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<ReservaVO> buscarTodo() {
		try {
			TypedQuery<ReservaVO> query = this.em.createNamedQuery("Reserva.buscarTodo", ReservaVO.class);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public ReservaVO crearReserva(ReservaVO reservaVO) {
		EntityTransaction transaction = this.em.getTransaction();
		Reserva reserva = reservaVO.reservaModelo();

		transaction.begin();

		this.em.persist(reserva);

		transaction.commit();

		reservaVO.setId(reserva.getId());

		return reservaVO;
	}

	@Override
	public void actualizar(Reserva reserva) {
		EntityTransaction transaction = this.em.getTransaction();
		transaction.begin();
		this.em.merge(reserva);
		transaction.commit();
	}

	@Override
	public void eliminar(Reserva reserva) {
		EntityTransaction transaction = this.em.getTransaction();
		transaction.begin();
		this.em.remove(this.em.contains(reserva) ? reserva : this.em.merge(reserva));
		transaction.commit();
	}

}
