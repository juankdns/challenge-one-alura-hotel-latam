package com.alura.hotel.latam.dao;

import java.util.List;

import com.alura.hotel.latam.modelo.Huesped;
import com.alura.hotel.latam.vo.HuespedVO;

public interface HuespedDAO {

	List<HuespedVO> buscarTodo();

	void crearHuesped(HuespedVO huespedVO);

	void actualizar(Huesped huesped);

	void eliminar(Huesped huesped);
}
