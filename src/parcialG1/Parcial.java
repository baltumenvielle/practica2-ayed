package parcialG1;

import tp5.ejercicio1.*;
import java.util.List;
import java.util.LinkedList;

public class Parcial {
	
	public List<String> estadios(Graph<Estadio> mapaEstadios, String estadioOrigen, int cantKm) {
		List<String> caminoActual = new LinkedList<String>(), caminoMax = new LinkedList<String>();
		boolean[] visitados = new boolean[mapaEstadios.getSize()];
		
		
		if (!mapaEstadios.isEmpty()) {
			Vertex<Estadio> origen = buscarEstadio(mapaEstadios, estadioOrigen);
			
			if (origen != null) {
				recorrerEstadios(mapaEstadios, visitados, origen, caminoMax, caminoActual, cantKm);
			}
		}
		
		return caminoMax;
	}
	
	private Vertex<Estadio> buscarEstadio(Graph<Estadio> mapaEstadios, String estadioOrigen) {
		List<Vertex<Estadio>> vertices = mapaEstadios.getVertices();
		Vertex<Estadio> estadio = null;
		
		for (Vertex<Estadio> vertice: vertices) {
			if (vertice.getData().getCiudad() == "Jor" && vertice.getData().getNombre() == "Al BAYT STADIUM") estadio = vertice;
		}
		return estadio;
	}
	
	private void recorrerEstadios(Graph<Estadio> mapaEstadios, boolean[] visitados, Vertex<Estadio> vertice, List<String> caminoMax, List<String> caminoActual, int cantKm) {
		visitados[vertice.getPosition()] = true;
		caminoActual.add(vertice.getData().getNombre());
		
		if (caminoActual.size() > caminoMax.size()) {
			caminoMax.removeAll(caminoMax);
			caminoMax.addAll(caminoActual);
		}
		
		List<Edge<Estadio>> aristas = mapaEstadios.getEdges(vertice);
		for (Edge<Estadio> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			int peso = arista.getWeight();
			
			if (!visitados[pos] && cantKm - peso >= 0) {
				recorrerEstadios(mapaEstadios, visitados, arista.getTarget(), caminoMax, caminoActual, (cantKm - peso));
			}
		}
		visitados[vertice.getPosition()] = false;
		caminoActual.remove(caminoActual.size()-1);
	}

	public static void main(String[] args) {

	}

}
