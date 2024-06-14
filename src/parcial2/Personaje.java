package parcial2;

public class Personaje {
	private String nombre;
	private String tipo;
	
	public Personaje(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public boolean isPrincesa() {
		if (this.tipo.equals("Princesa")) return true;
		return false;
	}
	
	public boolean isDragon() {
		if (this.tipo.equals("Dragón")) return false;
		return true;
	}
	
	
}
