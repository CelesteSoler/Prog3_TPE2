package tp2Esp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GenerosMasBuscados {
	
	private Grafo<String> grafo;

	public ArrayList<String> generoMasBuscadoA(String generoA){
		 ArrayList<String> generos = new ArrayList<>();
		
		Iterator<String> it = this.grafo.obtenerAdyacentes(generoA);
		//public Iterator<Arco<String>> obtenerArcos(String verticeId) {
		Iterator<Arco<String>> arcosAdya = grafo.
		while(it.hasNext()) {
			Arco<String> arco = grafo.obtenerArco(generoA, it.next());
			Collections.
			int valorEtiqueta = arco.getEtiqueta();
			//como podemos agregando ordenados los vertices según valor de etiqueta
			generos.add(it.next());
			
		}
		return generos;
		
	}
	
	
	
}
