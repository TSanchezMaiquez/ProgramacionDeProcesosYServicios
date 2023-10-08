package domain;

public class Buffer1 {
	
	private char contenido;
	private boolean disponible;
	
	public Buffer1 () {
		this.contenido = ' ';
	}
	
	public char recoger () {
		if(disponible) {
			disponible = false;
			return contenido;
		}
		return ('#');
	}

	public void poner (char c) {
		if(!disponible) {
			contenido=c;
			disponible = true;
		}
	}
}
