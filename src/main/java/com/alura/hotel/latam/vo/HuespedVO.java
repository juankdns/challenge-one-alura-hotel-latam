package com.alura.hotel.latam.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alura.hotel.latam.dto.HuespedDTO;
import com.alura.hotel.latam.modelo.Huesped;

public class HuespedVO {

	private Long id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private ReservaVO reservaVO;
	private Long reservaId;

	public HuespedVO(HuespedDTO huespedDTO) {
		this.id = huespedDTO.getId();
		this.nombre = huespedDTO.getNombre();
		this.apellido = huespedDTO.getApellido();
		this.fechaNacimiento = huespedDTO.getFechaNacimiento();
		this.nacionalidad = huespedDTO.getNacionalidad();
		this.telefono = huespedDTO.getTelefono();
		this.reservaVO = huespedDTO.getReservaVO();
	}

	public HuespedVO(Huesped huesped) {
		this.id = huesped.getId();
		this.nombre = huesped.getNombre();
		this.apellido = huesped.getApellido();
		this.fechaNacimiento = huesped.getFechaNacimiento();
		this.nacionalidad = huesped.getNacionalidad();
		this.telefono = huesped.getTelefono();
		this.reservaId = huesped.getReserva().getId();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public ReservaVO getReservaVO() {
		return reservaVO;
	}

	public Long getReservaId() {
		return reservaId;
	}

	public Huesped huespedModelo() {
		Huesped huesped = new Huesped(this.getNombre(), this.getApellido(), this.getFechaNacimiento(),
				this.getNacionalidad(), this.getTelefono(), this.getReservaVO().reservaModeloConId());
		return huesped;
	}

	public Huesped huespedModeloActualizar() {
		Huesped huesped = new Huesped(this.getId(), this.getNombre(), this.getApellido(), this.getFechaNacimiento(),
				this.getNacionalidad(), this.getTelefono(), this.getReservaVO().reservaModeloConId());
		return huesped;
	}

	public Huesped huespedModeloEliminar() {
		Huesped huesped = new Huesped(this.getId(), this.getReservaVO().reservaModeloConId());
		return huesped;
	}

	public Object[] generarFila() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Object[] fila = { this.getId(), this.getNombre(), this.getApellido(),
				formatter.format(this.getFechaNacimiento()), this.getNacionalidad(), this.getTelefono(),
				this.getReservaId() };
		return fila;
	}

}
