package com.alura.hotel.latam.dto;

import java.util.Date;

import com.alura.hotel.latam.vo.ReservaVO;

public class HuespedDTO {

	private Long id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private ReservaVO reservaVO;

	public HuespedDTO(Long id, ReservaVO reservaVO) {
		this.id = id;
		this.reservaVO = reservaVO;
	}

	public HuespedDTO(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			ReservaVO reservaVO) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaVO = reservaVO;
	}

	public HuespedDTO(Long id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, ReservaVO reservaVO) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaVO = reservaVO;
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

}
