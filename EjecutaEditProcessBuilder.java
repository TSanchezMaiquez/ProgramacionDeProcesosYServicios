import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjecutaEditProcessBuilder {

	public static void main(String[] args) {
							//cmd lo ejecuta un proceso, el segundo proceso ejecuta /c dir
		String [] comando ={"cmd", "/c", "dir"};//{"notepad.exe","app_text.txt"};
		
		
		//En este punto no se abre el procesador de texto, hay q darle orden.
		//Recoge como atributo distintas acciones que llevaran a cabo distintos procesos
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
			//Llamando a la variable pb arrancamos el procesador de texto.
			//Process permite crear tantos procesos como sean necesarios
			//crea 2 procesos: uno para cmd, y otro para /c dir
			Process p =pb.start();
			
			//Recibe la información del proceso en curso
			InputStream is = p.getInputStream();
			//Formatea los datos
			InputStreamReader isr = new InputStreamReader (is);
			//mostrar por consola
			BufferedReader buffer = new BufferedReader(isr);
			String linea;
			//Lee cada linea hasta el retorno de carro
			linea=buffer.readLine();
			
			while (linea !=null) {
				System.out.println(linea);
				linea = buffer.readLine();
			}
			buffer.close();
			//Espera a que termine el proceso. En este caso esta esperando a que cierre el 
			//procesador de texto y entonces continua el programa
			int estado =p.waitFor();
			System.out.println("proceso finalizado con "+ estado);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		System.out.println("Terminado principal ProcessBuilder");
	}

}
