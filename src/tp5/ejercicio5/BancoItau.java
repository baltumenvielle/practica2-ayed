package tp5.ejercicio5;

import tp5.ejercicio1.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import tp5.ejercicio1.adjList.*;

public class BancoItau {
	
	public List<Jubilado> encontrarJubilados(Graph<Persona> personas, String empleado, int grado) {
		List<Jubilado> jubilados = new LinkedList<Jubilado>();
		
		if (!personas.isEmpty()) {
			Vertex<Persona> empleadoNodo = buscarEmpleado(personas, empleado); // busqueda del empleado
			
			if (empleadoNodo != null) {
				Queue<Vertex<Persona>> cola = new LinkedList<Vertex<Persona>>();
				boolean[] visitados = new boolean [personas.getSize()];
				
				visitados[empleadoNodo.getPosition()] = true;
				cola.add(empleadoNodo);
				cola.add(null);
				
				while (!cola.isEmpty() && grado > 0 && jubilados.size() < 40) {
					Vertex<Persona> w = cola.remove();
					
					if (w != null) {
						List<Edge<Persona>> aristas = personas.getEdges(w);
						for (Edge<Persona> arista: aristas) {
							Vertex<Persona> verticeDestino = arista.getTarget();
							
							if (!visitados[verticeDestino.getPosition()]) {
								visitados[verticeDestino.getPosition()] = true;
								cola.add(verticeDestino);
								if (verticeDestino.getData().cumple()) {
									jubilados.add(new Jubilado(verticeDestino.getData().getNombre(), verticeDestino.getData().getDomicilio()));
								}
							}
						}
					}
					else if (!cola.isEmpty()) {
						grado--;
						cola.add(null);
					}
				}
		}
		
		}
		
		return jubilados;
	}
	
	private static Vertex<Persona> buscarEmpleado(Graph<Persona> grafo, String empleado) {
		List<Vertex<Persona>> vertices = grafo.getVertices();
		Vertex<Persona> persona = null;
		
		for (Vertex<Persona> vertice: vertices) {
			if (vertice.getData().getNombre().equals(empleado)) return vertice;
		}
		return persona;
	}

	public static void main(String[] args) {
		Graph<Persona> grafo = new AdjListGraph<>();
        Vertex<Persona> v1 = grafo.createVertex(new Persona("Empleado", "Matias", "AAA", true));
        Vertex<Persona> v2 = grafo.createVertex(new Persona("Jubilado", "Julian", "BBB", false));
        Vertex<Persona> v3 = grafo.createVertex(new Persona("Jubilado", "Francisco", "CCC", false));
        Vertex<Persona> v4 = grafo.createVertex(new Persona("Empleado", "Valentin", "DDD", true));
        Vertex<Persona> v5 = grafo.createVertex(new Persona("Jubilado", "Omar", "EEE", true));
        Vertex<Persona> v6 = grafo.createVertex(new Persona("Empleado", "Rosana", "FFF", true));
        Vertex<Persona> v7 = grafo.createVertex(new Persona("Jubilado", "Maria", "GGG", false));
        Vertex<Persona> v8 = grafo.createVertex(new Persona("Jubilado", "Sandra", "HHH", true));
        Vertex<Persona> v9 = grafo.createVertex(new Persona("Jubilado", "Matheo", "III", false));
        
        grafo.connect(v1, v2);
        grafo.connect(v2, v1);
        grafo.connect(v2, v3);
        grafo.connect(v3, v2);
        
        grafo.connect(v1, v9);
        grafo.connect(v9, v1);
        grafo.connect(v9, v8);
        grafo.connect(v8, v9);
        
        grafo.connect(v1, v4);
        grafo.connect(v4, v1);
        grafo.connect(v1, v6);
        grafo.connect(v6, v1);
        grafo.connect(v4, v5);
        grafo.connect(v5, v4);
        grafo.connect(v5, v7);
        grafo.connect(v7, v5);
        
        
        BancoItau banco = new BancoItau();
        
        //System.out.println(banco.encontrarJubilados(grafo, "Matias", 1));
        //System.out.println(banco.encontrarJubilados(grafo, "Matias", 2));
        System.out.println(banco.encontrarJubilados(grafo, "Matias", 3));
        
        //System.out.println(banco.encontrarJubilados(grafo, "Rosana", 2));
        
        //System.out.println(banco.encontrarJubilados(grafo, "Matias", 2));
	}

}
