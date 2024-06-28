package parcialG2;

import tp5.ejercicio1.*;
import java.util.List;
import java.util.LinkedList;

public class Parcial {
	
	private Graph<Color> grafo;
	
	public List<String> encontrarCamino(String origen, String destino) {
		List<String> camino = new LinkedList<String>();
		boolean[] visitados = new boolean[grafo.getSize()];
		
		if (!grafo.isEmpty()) {
			Vertex<Color> origenNodo = buscarNodo(origen);
			Vertex<Color> destinoNodo = buscarNodo(destino);
			
			if (origenNodo != null && destinoNodo != null) {
				caminoHelper(origenNodo, visitados, destinoNodo, camino);
			}
		}
		
		return camino;
	}
	
	private Vertex<Color> buscarNodo(String vertice) {
		Vertex<Color> nodo = null;
		
		List<Vertex<Color>> vertices = grafo.getVertices();
		for (Vertex<Color> v: vertices) {
			if (v.getData().getNombre().equals(vertice)) return v;
		}
		
		return nodo;
	}
	
	private boolean caminoHelper(Vertex<Color> vertice, boolean[] visitados, Vertex<Color> destino, List<String> camino) {
		visitados[vertice.getPosition()] = true;
		camino.add(vertice.getData().getNombre());
		
		if (vertice == destino) return true;
		
		boolean encontre = false;
		List<Edge<Color>> aristas = grafo.getEdges(vertice);
		for (Edge<Color> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			String color = arista.getTarget().getData().getColor();
			
			if (!visitados[pos] && !encontre && !color.equals(vertice.getData().getColor())) {
				encontre = caminoHelper(arista.getTarget(), visitados, destino, camino);
			}
		}
		if (!encontre) visitados[vertice.getPosition()] = false;
		camino.remove(camino.size()-1);
		return encontre;
	}

	public static void main(String[] args) {

	}

}
