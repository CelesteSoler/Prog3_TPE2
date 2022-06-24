package tp2Esp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "C://Users//celes//OneDrive//Documents//TUDAI 2022//PROGRAMACION 3//dataset2.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile),"UTF-8"))) {
        	
        	GrafoDirigido<String> grafo = new GrafoDirigido<>(0);
        	 br.readLine();
        	 
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                           
                for(int i=0; i< (items.length-1) ; i++ ) {
                	
                	grafo.agregarVertice(items[i]);
                	grafo.agregarVertice(items[i+1]);
                	if(grafo.existeArco(items[i], items[i+1])){
                			Arco<String> arco = grafo.obtenerArco(items[i],  items[i+1]);
                			arco.setEtiqueta(arco.getEtiqueta()+1);
                			
                	} 
                	else 
                		grafo.agregarArco(items[i],items[i+1], 1);
                	
                	
                }
                //juegos drama 4
                //
                
                //System.out.println("----------------");
                //grafo.agregarVertice("cine");
                //grafo.agregarVertice("drama");
                //grafo.agregarArco("cine", "drama", 1);
               
                
            //System.out.println(Arrays.toString(items));
            }
          //  System.out.println(biblio.toStringG());
           System.out.println(grafo.cantidadArcos());
        
           Boolean existe = grafo.existeArco("juegos", "drama");
           System.out.println(existe);
           
           Arco<String> arco =grafo.obtenerArco("periodismo","marketing");
           System.out.println(arco);
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}