package tp5.ejercicio2;

import tp5.ejercicio1.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import tp5.ejercicio1.adjList.*;

public class Recorridos<T> {
	
	public List<T> dfs(Graph<T> grafo) {
		List<T> lista = new LinkedList<T>();
		boolean[] visitados = new boolean[grafo.getSize()];
		
		if (!grafo.isEmpty()) {
			List<Vertex<T>> vertices = grafo.getVertices();
			for (Vertex<T> vertice: vertices) {
				int pos = vertice.getPosition();
				
				if (!visitados[pos]) {
					dfsHelper(grafo, visitados, vertice, lista);
				}
			}
		}
		return lista;
	}
	
	private void dfsHelper(Graph<T> grafo, boolean[] visitados, Vertex<T> vertice, List<T> lista) {
		visitados[vertice.getPosition()] = true;
		lista.add(vertice.getData());
		
		List<Edge<T>> aristas = grafo.getEdges(vertice);
		for (Edge<T> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			
			if (!visitados[pos]) {
				dfsHelper(grafo, visitados, arista.getTarget(), lista);
			}
		}
	}
	
	public List<T> bfs(Graph<T> grafo) {
		List<T> lista = new LinkedList<T>();
		boolean[] visitados = new boolean[grafo.getSize()];
		
		if (!grafo.isEmpty()) {
			List<Vertex<T>> vertices = grafo.getVertices();
			for (Vertex<T> vertice: vertices) {
				int pos = vertice.getPosition();
				
				if (!visitados[pos]) {
					bfsHelper(grafo, visitados, vertice, lista);
				}
			}
		}
		return lista;
	}
	
	private void bfsHelper(Graph<T> grafo, boolean[] visitados, Vertex<T> vertice, List<T> lista) {
		Queue<Vertex<T>> cola = new LinkedList<Vertex<T>>();
		
		cola.add(vertice);
		cola.add(null);
		
		while (!cola.isEmpty()) {
			Vertex<T> w = cola.remove();
			lista.add(w.getData());
			
			List<Edge<T>> aristas = grafo.getEdges(w);
			for (Edge<T> arista: aristas) {
				int pos = arista.getTarget().getPosition();
				
				if (!visitados[pos]) {
					visitados[pos] = true;
					cola.add(arista.getTarget());
				}
			}
		}
	}

	public static void main(String[] args) {
		Graph<String> ciudades = new AdjListGraph<String>();
        Recorridos<String> rec = new Recorridos<String>();
        
        Vertex<String> v1 = ciudades.createVertex("Buenos Aires");
        Vertex<String> v2 = ciudades.createVertex("Santiago");
        Vertex<String> v3 = ciudades.createVertex("Asunción");
        Vertex<String> v4 = ciudades.createVertex("Tokio");
        Vertex<String> v5 = ciudades.createVertex("Roma");
        Vertex<String> v6 = ciudades.createVertex("Paris");
        Vertex<String> v7 = ciudades.createVertex("Madrid");
        Vertex<String> v8 = ciudades.createVertex("Caracas");
        
        ciudades.connect(v1, v2);
        ciudades.connect(v1, v3);
        ciudades.connect(v2, v5);
        ciudades.connect(v3, v7);
        ciudades.connect(v3, v8);
        ciudades.connect(v8, v7);
        ciudades.connect(v8, v4);
        ciudades.connect(v5, v4);
        ciudades.connect(v7, v4);
        ciudades.connect(v6, v5);
        ciudades.connect(v6, v7);
        ciudades.connect(v6, v4);
        ciudades.connect(v4, v1);
        
        List<String> lisDFS = rec.dfs(ciudades);
        List<String> lisBFS = rec.bfs(ciudades);
        
        System.out.print("Lista DFS: ");
        for (String e: lisDFS){
            System.out.print(e + " ~ ");
        }
        
        System.out.println();
        
        System.out.print("Lista BFS: ");
        
        for (String e: lisBFS){
            System.out.print(e + " ~ ");
        }
	}

}
