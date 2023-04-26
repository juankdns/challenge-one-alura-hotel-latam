package com.alura.hotel.latam.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReservaDTO {

	private Long id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private BigDecimal valor;
	private String metodoPago;

	public ReservaDTO(Long id) {
		this.id = id;
	}

	public ReservaDTO(Date fechaEntrada, Date fechaSalida, BigDecimal valor, String metodoPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.metodoPago = metodoPago;
	}

	public ReservaDTO(Long id, Date fechaEntrada, Date fechaSalida, BigDecimal valor, String metodoPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.metodoPago = metodoPago;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

}
