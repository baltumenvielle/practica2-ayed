package tp5.ejercicio5;

import tp5.ejercicio1.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import tp5.ejercicio1.adjList.*;

public class BancoItau {
	
	public List<Jubilado> jubiladosCercanos(Graph<Persona> grafo, String empleado, int distancia) {
		List<Jubilado> jubilados = new LinkedList<Jubilado>();
		boolean[] visitados = new boolean[grafo.getSize()];
		
		if (!grafo.isEmpty()) {
			Vertex<Persona> empleadoNodo = this.buscarEmpleado(grafo, empleado);
			
			if (empleadoNodo != null) {
				Queue<Vertex<Persona>> cola = new LinkedList<Vertex<Persona>>();
				
				cola.add(empleadoNodo);
				cola.add(null);
				visitados[empleadoNodo.getPosition()] = true;
				
				while (!cola.isEmpty() && distancia > 0 && jubilados.size() < 40) {
					Vertex<Persona> w = cola.remove();
					if (w != null) {
						List<Edge<Persona>> aristas = grafo.getEdges(w);
						for (Edge<Persona> arista: aristas) {
							int pos = arista.getTarget().getPosition();
							if (!visitados[pos]) {
								visitados[pos] = true;
								cola.add(arista.getTarget());
								if (arista.getTarget().getData().cumple()) {
									jubilados.add(new Jubilado(arista.getTarget().getData().getNombre(), arista.getTarget().getData().getDomicilio()));
								}
							}
						}
					}
					else if (!cola.isEmpty()) {
						distancia--;
						cola.add(null);
					}
				}
			}
		}
		return jubilados;
	}
	
	private Vertex<Persona> buscarEmpleado(Graph<Persona> grafo, String empleado) {
		List<Vertex<Persona>> vertices = grafo.getVertices();
		for (Vertex<Persona> vertice: vertices) {
			if (vertice.getData().getNombre().equals(empleado)) {
				return vertice;
			}
		}
		return null;
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
        
        //System.out.println(banco.carteraJubilados(grafo, "Matias", 1));
        System.out.println(banco.jubiladosCercanos(grafo, "Matias", 2));
        //System.out.println(banco.carteraJubilados(grafo, "Matias", 3));
        
        //System.out.println(banco.carteraJubilados(grafo, "Rosana", 2));
        
        //System.out.println(banco.carteraJubilados(grafo, "Matias", 2));
	}

}
