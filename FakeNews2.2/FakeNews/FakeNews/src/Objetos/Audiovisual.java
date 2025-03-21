package Objetos;

import java.time.LocalDate;

public class Audiovisual extends Medio {

	private String nombrePrograma;
	private int minutosDedicados;
	private float rating;
	
	//Usé este constructor para llamar solamente el nombre del medio y el tipo de medio que es (para la consulta de la fakenew)
	public Audiovisual(String nombreMedio, String tipoMedio) {
		super(nombreMedio, tipoMedio);
		// TODO Auto-generated constructor stub
	}
	
	public Audiovisual(String nom ,String up, LocalDate fd, boolean sr, String np, int md, float r) {
		super(nom, up, fd, sr);
		nombrePrograma = np;
		minutosDedicados = md;
		rating = r;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	public int getMinutosDedicados() {
		return minutosDedicados;
	}
	public void setMinutosDedicados(int minutosDedicados) {
		this.minutosDedicados = minutosDedicados;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

	
	
}