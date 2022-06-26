package Prog3_TPE2;
//package tp2Esp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;


public class GrafoDirigido<String> implements Grafo<String> {

	private HashMap<String,ArrayList<Arco<String>>> vertices;
	private int cantArcos;



	public GrafoDirigido( int cantArcos) {

		this.vertices = new HashMap<String, ArrayList<Arco<String>>>() ;
		this.cantArcos = 0;
	}

	@Override
	public void agregarVertice(String verticeId) {
		if(!contieneVertice(verticeId)){
			vertices.put(verticeId, new ArrayList<Arco<String>>());
		}
	}

	@Override
	public boolean contieneVertice(String verticeId) {
		return vertices.containsKey(verticeId);
	}


	@Override
	public void agregarArco(String verticeId1, String verticeId2, int etiqueta) {
		//if(contieneVertice(verticeId1)&&contieneVertice(verticeId2)){
		if(!existeArco(verticeId1,verticeId2)) {
			Arco<String> newArco1a2 = new Arco<>(verticeId1,verticeId2, etiqueta);
			vertices.get(verticeId1).add(newArco1a2);
			cantArcos++;
		}
	}


	@Override
	public void borrarArco(String verticeId1, String verticeId2) {
		if(existeArco(verticeId1,verticeId2)) {
			ArrayList< Arco<String> >arcos= vertices.get(verticeId1);//me traigo todos los arcos que tiene el vertice origen en verticeId1
			if(arcos!=null) {//si es distinto de null, 
				for(int i=0; i<arcos.size();i++) {//recorro el array de vertices origen
					if(arcos.get(i).getVerticeDestino()==verticeId2) {//por cada arco con origen en VerticeId1 le pido el valor del verticeDestino si es igual a verticeId2
						arcos.remove(i);//remuevo ese arco
						cantArcos--;//decremento la cantidad de arcos
					}
				}
			}	
		}
	}


	@Override
	public boolean existeArco(String verticeId1, String verticeId2) {

		if(contieneVertice(verticeId1)) {//si el vertice destino existe
			Iterator<Arco<String>> arcos = this.obtenerArcos(verticeId1);//obtengo un iterator de todos los arcos adyacentes de vertice origen
			while(arcos.hasNext()) {//mientras arcos tenga un siguiente
				if(arcos.next().getVerticeDestino().equals(verticeId2)) { //a cada arco le pido el vertice destino, metodo que está en la clase Arco
					return true;//si encuentro un vertice destino iguak a verticeId2, retorno true
				}	
			}

			return false;
		}
		return false;
	}

	@Override
	public Arco<String> obtenerArco(String verticeId1, String verticeId2){ //CORREGIR
		if(contieneVertice(verticeId1)&&contieneVertice(verticeId2)) {//si existen los vertices
			if(existeArco(verticeId1,verticeId2)) {//si existe arco entre ellos
				Iterator<Arco<String>> arcos = this.obtenerArcos(verticeId1);
				while(arcos.hasNext()) {//mientras arcos tenga un siguiente
					Arco<String> arco = arcos.next();
					if(arco.getVerticeDestino().equals(verticeId2)) {//a cada arco le pido el vertice destino, metodo que está en la clase Arco

						return arco;//si encuentro un ve
					}
				}
				return null;
			}
			return null;				
		}
		return null;
	}




	@Override
	public int cantidadVertices() {
		return vertices.size();
	}

	@Override
	public int cantidadArcos() {
		return this.cantArcos;
	}

	@Override
	public Iterator<String> obtenerVertices() {
		return vertices.keySet().iterator();//está en el vídeo

	}

	@Override
	public Iterator<String> obtenerAdyacentes(String verticeId) {
		Iterator <Arco<String>> itInterno = vertices.get(verticeId).iterator();
		return new IteradorAdyacentes<String>(itInterno);
	}

	@Override
	public Iterator<Arco<String>> obtenerArcos() {
		Iterator<String> verticesIt = obtenerVertices();//crea un iterador de integer de todos los vertices del grafo 	 
		ArrayList<Arco<String>> arcosList = new ArrayList<Arco<String>>();//crea un array donde va a agregar todos los arcos de cada vertice

		while (verticesIt.hasNext()) {		//O(v*a), v = tamanio del hashmap vertices y a = promedio de los tamanios de las listas de adyacentes a cada vertice
			String current = verticesIt.next();//guarda el valor de cada vertice en una variable int
			Iterator<Arco<String>> adyacentes = obtenerArcos(current); //O(1)//obtiene todos los arcos de ese vertice

			adyacentes.forEachRemaining(arcosList::add);//a esos arcos adyacentes del vertice, los agrega al array de arcosList	
		}

		return arcosList.iterator(); //O(1)
	}

	@Override
	public Iterator<Arco<String>> obtenerArcos(String verticeId) {
		ArrayList<Arco<String>> arcosList = new ArrayList<Arco<String>>();

		if (contieneVertice(verticeId)) { //O(1)
			arcosList = vertices.get(verticeId);	//O(1)
			return arcosList.iterator();	//O(1) 
		}

		return arcosList.iterator();
	}

	@Override
	public Iterator<Arco<String>> String(String verticeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarVertice(String verticeId) {
		// TODO Auto-generated method stub

	}


	public ArrayList<String> generoMasBuscadoA(String generoA, int Ngeneros){
		ArrayList<String> generos = new ArrayList<>();

		ArrayList<Arco<String>> arcos = new ArrayList<>();
		Iterator<Arco<String>> arcosAdya = this.obtenerArcos(generoA);
		arcosAdya.forEachRemaining(arcos::add);// los agrega a la lista arcos 
		arcos.sort(Comparator.comparing(Arco<String>::getEtiqueta).reversed());

		for(int i= 0; i< Ngeneros; i++) {
			generos.add(arcos.get(i).getVerticeDestino());
		}
		return generos;
	}
	
	public ArrayList<String> secuenciaMayorValor(String generoA){
		ArrayList<String> secuenciaMayor = new ArrayList <>();
		String currentGen = generoA;

		while(contieneVertice(currentGen)) {   //si existe algun arco 
			Iterator<Arco<String>> arcosAdya = this.obtenerArcos(currentGen);
			ArrayList<String> genero = generoMasBuscadoA(currentGen, 1); //selecciona el genero mas buscado desde currentGen
			secuenciaMayor.add(genero.get(0));  //agrego a secuenciaMayor el genero
		    vertices.remove(currentGen);	//borro el genero que ya
			currentGen = genero.get(0);

		}

		if(!secuenciaMayor.isEmpty()) {
			return secuenciaMayor;
		}
		return null;
	}
}


