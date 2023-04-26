package com.alura.hotel.latam.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alura.hotel.latam.dto.ReservaDTO;
import com.alura.hotel.latam.modelo.Reserva;

public class ReservaVO {

	private Long id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private BigDecimal valor;
	private String metodoPago;

	public ReservaVO(Long id) {
		this.id = id;
	}

	public ReservaVO(ReservaDTO reservaDTO) {
		this.id = reservaDTO.getId();
		this.fechaEntrada = reservaDTO.getFechaEntrada();
		this.fechaSalida = reservaDTO.getFechaSalida();
		this.valor = reservaDTO.getValor();
		this.metodoPago = reservaDTO.getMetodoPago();
	}

	public ReservaVO(Reserva reserva) {
		this.id = reserva.getId();
		this.fechaEntrada = reserva.getFechaEntrada();
		this.fechaSalida = reserva.getFechaSalida();
		this.valor = reserva.getValor();
		this.metodoPago = reserva.getMetodoPago();
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

	public Reserva reservaModelo() {
		Reserva reserva = new Reserva(this.getFechaEntrada(), this.getFechaSalida(), this.getValor(),
				this.getMetodoPago());
		return reserva;
	}

	public Reserva reservaModeloConId() {
		Reserva reserva = new Reserva(this.getId());
		return reserva;
	}

	public Reserva reservaModeloActualizar() {
		Reserva reserva = new Reserva(this.getId(), this.getFechaEntrada(), this.getFechaSalida(), this.getValor(),
				this.getMetodoPago());
		return reserva;
	}

	public Object[] generarFila() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Object[] fila = { this.getId(), formatter.format(this.getFechaEntrada()),
				formatter.format(this.getFechaSalida()), this.getValor(), this.getMetodoPago() };
		return fila;
	}

}
