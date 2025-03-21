package Objetos;

import java.time.LocalDate;

public class Escrito extends Medio{
	
	private String nombreDiario;
	private String nombreNota;
	private int visualizaciones;

	
	//Usé este constructor para llamar solamente el nombre del medio y el tipo de medio que es (para la consulta de la fakenew)
	public Escrito(String nombreMedio, String tipoMedio) {
		super(nombreMedio, tipoMedio);
		// TODO Auto-generated constructor stub
	}
	
	public Escrito(String nom ,String up, LocalDate fd, boolean sr, String nd, String nn, int v) {
		super(nom, up, fd, sr);
		nombreDiario = nd;
		nombreNota = nn;
		visualizaciones = v;
	}


	public String getNombreDiario() {
		return nombreDiario;
	}
	public void setNombreDiario(String nombreDiario) {
		this.nombreDiario = nombreDiario;
	}
	public String getNombreNota() {
		return nombreNota;
	}
	public void setNombreNota(String nombreNota) {
		this.nombreNota = nombreNota;
	}
	public int getVisualizaciones() {
		return visualizaciones;
	}
	public void setVisualizaciones(int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

}