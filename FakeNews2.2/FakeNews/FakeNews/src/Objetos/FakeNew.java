package Objetos;

import java.time.LocalDate;

public class FakeNew {
	
	private String titulo;
	private String descripcion;
	private String creador;
	private LocalDate fechaAparicion;
	private Categoria categoria;
	private MedioOrigen medioOrigen;
	
	
	public FakeNew(String t,String d, String c, LocalDate f, Categoria cat, MedioOrigen m) {
		titulo = t;
		descripcion = d;
		creador = c;
		fechaAparicion = f;
		categoria = cat;
		medioOrigen = m;
	}
	
	public FakeNew(String t) {
		titulo = t;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCreador() {
		return creador;
	}
	public void setCreador(String creador) {
		this.creador = creador;
	}
	public LocalDate getFechaAparicion() {
		return fechaAparicion;
	}
	public void setFechaAparicion(LocalDate fechaAparicion) {
		this.fechaAparicion = fechaAparicion;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public MedioOrigen getMedioOrigen() {
		return medioOrigen;
	}
	public void setMedioOrigen(MedioOrigen medioOrigen) {
		this.medioOrigen = medioOrigen;
	}
	
}

