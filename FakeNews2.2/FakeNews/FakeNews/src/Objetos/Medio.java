package Objetos;

import java.time.LocalDate;

public abstract class Medio{
	//nombreMedio en la base de datos quedó como clave única para poder consultarla a futuro
	private String nombreMedio;
	//tipoMedio es un enum en la base de datos (tipo un arrayList, ¿Te acordás?, para verificar qué tipo de medio es y así también usar la clase FactoryMedios
	private String tipoMedio;
	private String usuarioPeriodista;
	private LocalDate fechaDivulgacion;
	private boolean seRetracto;
	
	public Medio (String nom, String up, LocalDate fd, boolean sr) {
		nombreMedio = nom;
		usuarioPeriodista = up;
		fechaDivulgacion = fd;
		seRetracto = sr;
	}
	
	//Usé este constructor para llamar solamente el nombre del medio y el tipo de medio que es (para la consulta de la fakenew)
	public Medio (String nombreMedio, String tipoMedio) {
		this.nombreMedio = nombreMedio;
		this.tipoMedio = tipoMedio;
	}
	
	
	public String getNombreMedio() {
		return nombreMedio;
	}

	public void setNombreMedio(String nombreMedio) {
		this.nombreMedio = nombreMedio;
	}
	public String getUsuarioPeriodista() {
		return usuarioPeriodista;
	}
	public void setUsuarioPeriodista(String usuarioPeriodista) {
		this.usuarioPeriodista = usuarioPeriodista;
	}
	public LocalDate getFechaDivulgacion() {
		return fechaDivulgacion;
	}
	public void setFechaDivulgacion(LocalDate fechaDivulgacion) {
		this.fechaDivulgacion = fechaDivulgacion;
	}
	public boolean isSeRetracto() {
		return seRetracto;
	}
	public void setSeRetracto(boolean seRetracto) {
		this.seRetracto = seRetracto;
	}
	public String getTipoMedio() {
		return tipoMedio;
	}
	public void setTipoMedio(String tipoMedio) {
		this.tipoMedio = tipoMedio;
	}
	

}