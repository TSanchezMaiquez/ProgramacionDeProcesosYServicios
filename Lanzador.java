import java.io.File;
import java.io.IOException;

public class Lanzador {

	public static void main(String[] args) {

		LanzadorFicheros lf = new LanzadorFicheros();
		lf.lanzarSumador(1,10, "r1.txt");
		lf.lanzarSumador(11,10, "r2.txt");
		System.out.println("Sumadores lanzados");
		
	
	}

}
class LanzadorFicheros {

	public void lanzarSumador(Integer num1, Integer num2, String fileName) {
		
		String [] comando = {"java", "src/Sumador.java", num1.toString(), num2.toString()};
		ProcessBuilder pb = new ProcessBuilder(comando);

		File fout = new File (fileName);
		File ferr = new File ("Err"+ fileName);
		
		
		pb.redirectOutput(fout);
		pb.redirectError(ferr);
		
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}