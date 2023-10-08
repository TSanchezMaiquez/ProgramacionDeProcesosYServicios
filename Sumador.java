
public class Sumador {
	
	/*Los atributos a args se le pasan por consola y dentro de src*/
	//Sumador (n1, n2)
	public static void main(String[] args) {
		
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		
		int suma = sumar (n1, n2);
		System.out.println(suma);
		//vaciar memoria cache
		System.out.flush();
		
		
	}
	private static int sumar(int num1, int num2) {
		int suma=0;
		if(num1 >num2) {
			int aux=num1;
			num1=num2;
			num2=aux;
			
			}

		
		for (int i = num1; i <=num2 ; i++) {
			suma+= i;
		}
		return suma;
	}

}
