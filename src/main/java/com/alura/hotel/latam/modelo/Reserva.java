package com.alura.hotel.latam.modelo;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
@NamedQuery(name = "Reserva.buscarTodo", query = "SELECT new com.alura.hotel.latam.vo.ReservaVO(r) FROM Reserva r")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_entrada", nullable = false)
	private Date fechaEntrada;

	@Column(name = "fecha_salida", nullable = false)
	private Date fechaSalida;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(name = "metodo_pago", nullable = false)
	private String metodoPago;

	public Reserva() {

	}

	public Reserva(Long id) {
		this.id = id;
	}

	public Reserva(Long id, Date fechaEntrada, Date fechaSalida, BigDecimal valor, String metodoPago) {
		super();
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.metodoPago = metodoPago;
	}

	public Reserva(Date fechaEntrada, Date fechaSalida, BigDecimal valor, String metodoPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.metodoPago = metodoPago;
	}

	public Long getId() {
		return id;
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
