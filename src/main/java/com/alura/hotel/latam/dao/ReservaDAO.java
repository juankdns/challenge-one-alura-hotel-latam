package com.alura.hotel.latam.dao;

import java.util.List;

import com.alura.hotel.latam.modelo.Reserva;
import com.alura.hotel.latam.vo.ReservaVO;

public interface ReservaDAO {

	List<ReservaVO> buscarTodo();

	ReservaVO crearReserva(ReservaVO reservaVO);

	void actualizar(Reserva reserva);

	void eliminar(Reserva reserva);
}
