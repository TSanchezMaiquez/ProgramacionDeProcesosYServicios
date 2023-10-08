import java.io.File;
import java.io.IOException;

public class EjecutaRedirect {

	public static void main(String[] args) {

		String [] comando ={"cmd", "/c", "dir"};//{"notepad.exe","app_text.txt"};
		
		
		ProcessBuilder pb = new ProcessBuilder(comando);

		File fout = new File ("Salida txt");
		File ferr = new File ("error.txt");
		
		
		pb.redirectOutput(fout);
		pb.redirectError(ferr);
		
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finalizado");
	}

}
