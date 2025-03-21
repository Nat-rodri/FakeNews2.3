package Objetos;

import java.time.LocalDate;

public class RedSocial extends Medio {
	
	private String plataforma;
	private String usuario;
	private int interacciones;
	
	public RedSocial(String nom, String up, LocalDate fd, boolean sr, String p, String u, int i) {
		super(nom, up, fd, sr);
		setPlataforma(p);
		setUsuario(u);
		setInteracciones(i);	
	}
	
	//Usé este constructor para llamar solamente el nombre del medio y el tipo de medio que es (para la consulta de la fakenew)
	public RedSocial(String nombreMedio, String tipoMedio) {
		super(nombreMedio, tipoMedio);
	}

	
	public int getInteracciones() {
		return interacciones;
	}
	public void setInteracciones(int interacciones) {
		this.interacciones = interacciones;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

}