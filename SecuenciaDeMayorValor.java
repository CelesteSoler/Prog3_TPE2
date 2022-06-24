package tp2Esp;

import java.util.ArrayList;
import java.util.Iterator;

public class SecuenciaDeMayorValor {
 
	Grafo<String> grafo;
	
	
	//preguntamos si generoA tiene adyacentes
	//este método pide los adyacentes
	//los ordena según etiqueta(con un método que nos falta realizar?)
	//primero agrega al generoA al array generosSecuencia
	//ese primero se vuelve generoA
	//
	public ArrayList<String> secuencia(String generoA){
		ArrayList<String> generosSecuencia = new ArrayList<>();
			
		Iterator<String> it = this.grafo.obtenerAdyacentes(generoA);
		
		while(it.hasNext()) {
			int etiqueta =
		}
	}
}
