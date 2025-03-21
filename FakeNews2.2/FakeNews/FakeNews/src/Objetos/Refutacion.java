package Objetos;

import java.time.LocalDate;

public class Refutacion {
	
	private FakeNew fakeNew;
	private Refutador refutador;
	
	private LocalDate fechaRefutada;
	private String fuenteEvidencia;
	private boolean provOrgOficial;
	
	public Refutacion(LocalDate fechaRefutada, String fuenteEvidencia, boolean provOrgOficial, Refutador refutador, FakeNew fakeNew) {
		this.fechaRefutada = fechaRefutada;
		this.fuenteEvidencia = fuenteEvidencia;
		this.provOrgOficial = provOrgOficial;
		this.refutador = refutador;
		this.fakeNew = fakeNew;
	}
	
	public Refutacion(FakeNew fakeNew) {
		this.fakeNew = fakeNew;
	}
	
	public FakeNew getFakenew() {
		return fakeNew;
	}
	public void setFakenew(FakeNew fakenew) {
		this.fakeNew = fakenew;
	}
	public Refutador getRefutador() {
		return refutador;
	}
	public void setRefutador(Refutador refutador) {
		this.refutador = refutador;
	}
	public LocalDate getFechaRefutada() {
		return fechaRefutada;
	}
	public void setFechaRefutada(LocalDate fechaRefutada) {
		this.fechaRefutada = fechaRefutada;
	}
	public String getFuenteEvidencia() {
		return fuenteEvidencia;
	}
	public void setFuenteEvidencia(String fuenteEvidencia) {
		this.fuenteEvidencia = fuenteEvidencia;
	}
	public boolean isProvOrgOficial() {
		return provOrgOficial;
	}
	public void setProvOrgOficial(boolean provOrgOficial) {
		this.provOrgOficial = provOrgOficial;
	}
	
}
