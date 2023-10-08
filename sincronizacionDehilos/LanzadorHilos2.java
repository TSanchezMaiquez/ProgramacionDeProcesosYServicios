package sincronizacionDehilos;

public class LanzadorHilos2 {

	public static void main(String[] args) {
		
		Object candado = new Object();
		
		int maxHilos = 100;
		HiloSincro2 [] misHilos = new HiloSincro2[maxHilos];
		
		for(int i=0; i < maxHilos; i++) {
		//Al pasar el objeto candado se vincula un hilo a ese objeto lo que permite que
		//al llamar al metodo cambiarContador los hilos no puedan entrar al metodo si hay un hilo vinculado
			misHilos[i] = new HiloSincro2(i, candado);
		}
	}
}
