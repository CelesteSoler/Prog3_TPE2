package Prog3_TPE2;
//package tp2Esp;

	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.List;


	public class DFS_Ciclo {
		private Grafo <?> grafo;
		private HashMap<Integer,String>colores;//la idea es no meterle el color al vertice, guardo los colores de cada de vertice por fuera,
		//hacemos un hasmap la clave: idVertice valor:color
		private int tiempo;
		private HashMap<Integer,Integer> descubrimiento;//necesito guardar la info independiente, la Clave: vertideId, Valor: el tiempo
		private HashMap<Integer,Integer> finalizacion;
		
		public DFS (Grafo <?> grafo) {
			this.grafo = grafo;
			this.colores = new HashMap<>();
			this.tiempo = 0;
			this.descubrimiento = new HashMap<>();
			this.finalizacion = new HashMap<>();
		}
		
		public void dfs() {
				
				Iterator<Integer> it = this.grafo.obtenerVertices();//un iterador qu enos permite recorrer todos los vertices de mi grafo
				while (it.hasNext()) {
					int verticeId = it.next();//por cada vertice
					colores.put(verticeId, "blanco");//lo pinto de blanco
				}
				this.tiempo = 0;//los pinto todos de blanco y el tiempo es cero
				it = this.grafo.obtenerVertices();//vuelva a armar el iterador para recorrerlos y ver cual sigue ne blanco y no fue pintado
				while(it.hasNext()) {
					int verticeId = it.next();
					if (colores.get(verticeId).equals("blanco")) {//si el vertice sigue siendo  blanco
						dfs_visit(verticeId);//llamo a dfs_visit desde ese vertice
					}
				}	
			}
		
		public boolean hayCiclo() {//ejercicio 3
			
			Iterator<Integer> it = this.grafo.obtenerVertices();
			while (it.hasNext()) {
				int verticeId = it.next();
				colores.put(verticeId, "blanco");
			}
			this.tiempo = 0;
			it = this.grafo.obtenerVertices();
			
			boolean encontre= false;
			while(it.hasNext()&& !encontre) {
				int verticeId = it.next();
				if (colores.get(verticeId).equals("blanco")) 
					encontre = dfs_visit(verticeId);
			}
			return encontre;	
		}
		//si tengo un grupito de vertices aislados, disparo y  //recordar que cuando utilizo un metodo recursivo, 
		//tengo que hacer algo, guardarlo en una variable
		public boolean dfs_visit(int vertice) {
			//RECORDAR:cuando tengo un metodo que me devuelve algo, en este caso un boolean, si ese metodo es recursivo
			//ese llamdo recursivo me va a estar devolviendo algo, lo mas probable, que con ese resultado yo voy a tener que hacer algo
			//encontre = dfs_visit(adyacente)
		
			colores.put(vertice, "amarillo");//primero al visitar un vertice, le piso el color blanco y lo pintamos de amarillo
			tiempo+=1;//le marco el tiempo en que visite el vertice
			descubrimiento.put(vertice, tiempo);//tenemos que ir guardando este tiempo de descubrimiento del vertice
			
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);//tengo que recorrer tods los adyacentes
			boolean encontre = false;
			
			while(it.hasNext()&& !encontre) {//mientras tenga adyacente
				int adyacente = it.next();//obtengo el id del vertice adyacente
				
				if(colores.get(adyacente).equals("blanco")) { //si ese adyacente es blanco
					//si no voy chequeando que ese vertice haya sido visitado, se queda ciclando, 
					encontre = dfs_visit(adyacente);//disparo el dfs_visit en el adyacente recursivo
				}
				else if (colores.get(adyacente).equals("amarillo")) {//si lo visite y esta en amariillo, quiere decir que ya fue visitado, encontes lo pinto en negro
					encontre = true;//si hay un ciclo donde lo encontre corte, lo retorno
				}
			}
			colores.put(vertice, "negro");
			tiempo += 1;
			finalizacion.put(vertice, tiempo);
			
			return encontre;
			
		}
		
		public List<Integer>dfsList() {
			
			Iterator<Integer> it = this.grafo.obtenerVertices();
			while (it.hasNext()) {
				int verticeId = it.next();
				colores.put(verticeId, "blanco");
			}
			this.tiempo = 0;
			it = this.grafo.obtenerVertices();
			while(it.hasNext()) {
				int verticeId = it.next();
				if (colores.get(verticeId).equals("blanco")) {
					dfs_visit(verticeId);
				}
			}
			return null;	
		}
		
		
		public List<Integer>dfs_visitList(int vertice) {
			colores.put(vertice, "amarillo");
			tiempo+=1;
			descubrimiento.put(vertice, tiempo);
			
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
		
			while(it.hasNext()) {
				int adyacente = it.next();
				if(colores.get(adyacente).equals("blanco")) { 
					dfs_visitList(adyacente);
				}
				
			colores.put(vertice, "negro");
			tiempo += 1;
			finalizacion.put(vertice, tiempo);
			
			}
			return null;
			
			
		}
		
		

		



	}
}
