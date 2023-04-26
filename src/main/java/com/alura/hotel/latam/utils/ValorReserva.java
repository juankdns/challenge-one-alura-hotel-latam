package com.alura.hotel.latam.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValorReserva {

	public static BigDecimal valorPorDia(Long dias) {
		BigDecimal valor = new BigDecimal(dias * 76.54);
		return valor.setScale(2, RoundingMode.HALF_UP);
	}
}
