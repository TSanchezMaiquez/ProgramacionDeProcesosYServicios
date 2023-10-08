package sincronizacionDehilos;

public class LanzadorHilos1 {

	public static void main(String[] args) {
		int maxHilos = 100;
		HiloSincro01 [] misHilos = new HiloSincro01[maxHilos];
		
		for(int i=0; i < maxHilos; i++) {
			misHilos[i] = new HiloSincro01(i);
		}
	}
}
