import java.io.IOException;

public class EjecutaEdit {

	public static void main(String[] args) {

		String comando ="notepad.exe";  //xed
		Runtime runTimeActual = Runtime.getRuntime();
		
		Process p;
		try {
			//Abre el block de notas directamente, sin necesidad de más codigo
			p=runTimeActual.exec(comando);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("proceso terminado");
	}

}
