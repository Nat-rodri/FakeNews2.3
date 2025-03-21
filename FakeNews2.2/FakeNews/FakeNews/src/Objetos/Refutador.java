package Objetos;

public class Refutador {
	
	private String nombre;
	private String apellido;
	private String medio;
	private String apodo;
	 ///agregar desde eque inicio este tipo de datos 
	public Refutador(String nombre, String apellido, String medio, String apodo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.medio = medio;
		this.apodo = apodo;
	}
	
	public Refutador(String nombre) {
		this.nombre = nombre;
	}
	
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
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

	public final String getApodo() {
		return apodo;
	}

	public final void setApodo(String apodo) {
		this.apodo = apodo;
	}
	

}
