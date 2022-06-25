package Prog3_TPE2;
//package tp2Esp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GenerosMasBuscados {
	
	private Grafo<String> grafo;

	public ArrayList<String> generoMasBuscadoA(String generoA, int Ngeneros){
		 ArrayList<String> generos = new ArrayList<>();
		 
		 ArrayList<Arco<String>> arcos = new ArrayList<>();
		 Iterator<Arco<String>> arcosAdya = this.grafo.obtenerArcos(generoA);		 
		
//		Iterator<String> it = this.grafo.obtenerAdyacentes(generoA);
		//public Iterator<Arco<String>> obtenerArcos(String verticeId) {		
		
		while(arcosAdya.hasNext()) {
			Arco<String> arco = grafo.obtenerArco(generoA, arcosAdya.next());
			arcos.add(arco);
		}
		Collections.sort(arcos);
	
		
		int i =0;
		while(generos.size() < Ngeneros) {
			
			String gen = arcos[i].getVerticeDestino();
			generos.add(gen);
			i++;
		}
		
		
//		while(it.hasNext()) {
//			Arco<String> arco = grafo.obtenerArco(generoA, it.next());
//			Collections.sort();
//			int valorEtiqueta = arco.getEtiqueta();
//			//como podemos agregando ordenados los vertices según valor de etiqueta
//			generos.add(it.next());
//			
//		}
		return arcos;
	}
	
	
	
}
