package com.alura.hotel.latam.dao;

import java.util.List;

import com.alura.hotel.latam.modelo.Huesped;
import com.alura.hotel.latam.vo.HuespedVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class HuespedImpl implements HuespedDAO {

	private EntityManager em;

	public HuespedImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<HuespedVO> buscarTodo() {
		try {
			TypedQuery<HuespedVO> query = this.em.createNamedQuery("Huesped.buscarTodo", HuespedVO.class);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void crearHuesped(HuespedVO huespedVO) {
		EntityTransaction transaction = this.em.getTransaction();
		Huesped huesped = huespedVO.huespedModelo();

		transaction.begin();

		this.em.persist(huesped);

		transaction.commit();
	}

	@Override
	public void actualizar(Huesped huesped) {
		EntityTransaction transaction = this.em.getTransaction();
		transaction.begin();
		this.em.merge(huesped);
		transaction.commit();
	}

	@Override
	public void eliminar(Huesped huesped) {
		EntityTransaction transaction = this.em.getTransaction();
		transaction.begin();
		this.em.remove(this.em.contains(huesped) ? huesped : this.em.merge(huesped));
		transaction.commit();
	}

}
