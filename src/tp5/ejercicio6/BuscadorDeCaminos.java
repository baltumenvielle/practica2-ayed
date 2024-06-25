package tp5.ejercicio6;

import tp5.ejercicio1.*;
import java.util.List;
import java.util.LinkedList;
import tp5.ejercicio1.adjList.*;

public class BuscadorDeCaminos {
	
	Graph<String> bosque;
	
	public BuscadorDeCaminos(Graph<String> bosque) {
        this.setBosque(bosque);
    }

    public Graph<String> getBosque() {
        return bosque;
    }

    public void setBosque(Graph<String> bosque) {
        this.bosque = bosque;
    }
	
	public List<List<String>> recorridosMasSeguros() {
		List<List<String>> recorridos = new LinkedList<List<String>>();
		List<String> caminoActual = new LinkedList<String>();
		boolean[] visitados = new boolean[bosque.getSize()];
		
		if (!bosque.isEmpty()) {
			Vertex<String> origen = bosque.search("Casa Caperucita");
			Vertex<String> destino = bosque.search("Casa Abuelita");
			if (origen != null && destino != null) {
				recorridoHelper(visitados, origen, destino, caminoActual, recorridos);
				return recorridos;
			}
		}
		return null;
	}
	
	private void recorridoHelper(boolean[] visitados, Vertex<String> vertice, Vertex<String> destino, List<String> caminoActual, List<List<String>> recorridos) {
		visitados[vertice.getPosition()] = true;
		caminoActual.add(vertice.getData());
		
		if (vertice == destino) {
			recorridos.add(new LinkedList<String>(caminoActual));
		}
		
		List<Edge<String>> aristas = bosque.getEdges(vertice);
		for (Edge<String> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			
			if (!visitados[pos] && arista.getWeight() < 5) {
				recorridoHelper(visitados, arista.getTarget(), destino, caminoActual, recorridos);
			}
		}
		visitados[vertice.getPosition()] = false;
		caminoActual.remove(caminoActual.size()-1);
	}

	public static void main(String[] args) {
		Graph<String> bosque = new AdjListGraph<String>();
        Vertex<String> v1 = bosque.createVertex("Casa Caperucita");
        Vertex<String> v2 = bosque.createVertex("Claro 3");
        Vertex<String> v3 = bosque.createVertex("Claro 1");
        Vertex<String> v4 = bosque.createVertex("Claro 2");
        Vertex<String> v5 = bosque.createVertex("Claro 5");
        Vertex<String> v6 = bosque.createVertex("Claro 4");
        Vertex<String> v7 = bosque.createVertex("Casa Abuelita");
        bosque.connect(v1, v2, 4);
        bosque.connect(v2, v1, 4);
        bosque.connect(v1, v3, 3);
        bosque.connect(v3, v1, 3);
        bosque.connect(v1, v4, 4);
        bosque.connect(v4, v1, 4);
        bosque.connect(v2, v5, 15);
        bosque.connect(v5, v2, 15);
        bosque.connect(v3, v5, 3);
        bosque.connect(v5, v3, 3);
        bosque.connect(v4, v3, 4);
        bosque.connect(v3, v4, 4);
        bosque.connect(v4, v5, 11);
        bosque.connect(v5, v4, 11);
        bosque.connect(v4, v6, 10);
        bosque.connect(v6, v4, 10);
        bosque.connect(v4, v3, 4);
        bosque.connect(v3, v4, 4);
        bosque.connect(v5, v7, 4);
        bosque.connect(v7, v5, 4);
        bosque.connect(v6, v7, 9);
        bosque.connect(v7, v6, 9);
        BuscadorDeCaminos bos = new BuscadorDeCaminos(bosque);
        List<List<String>> lis = bos.recorridosMasSeguros();
        for(List<String> l: lis) {
            System.out.println(l);
        }
	}
}
