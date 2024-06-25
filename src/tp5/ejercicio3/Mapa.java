package tp5.ejercicio3;

import java.util.List;
import java.util.LinkedList;
import tp5.ejercicio1.*;
import tp5.ejercicio1.adjList.*;

public class Mapa {
	
	Graph<String> mapaCiudades;
	
	public void setMapaCiudades(Graph<String> mapaCiudades) {
        this.mapaCiudades = mapaCiudades;
    }
	
	public Mapa(Graph<String> mapa) {
        this.setMapaCiudades(mapa);
    }
	
	public List<String> devolverCamino(String ciudad1, String ciudad2) {
		List<String> camino = new LinkedList<String>();
		boolean[] visitados = new boolean[mapaCiudades.getSize()];
		
		if (!mapaCiudades.isEmpty()) {
			Vertex<String> origen = mapaCiudades.search(ciudad1);
			Vertex<String> destino = mapaCiudades.search(ciudad2);
			
			if (origen != null && destino != null) {
				caminoHelper(origen, visitados, destino, camino);
				return camino;
			}
		}
		return null;
	}
	
	private boolean caminoHelper(Vertex<String> vertice, boolean[] visitados, Vertex<String> destino, List<String> camino) {
		visitados[vertice.getPosition()] = true;
		camino.add(vertice.getData());
		
		if (vertice == destino) return true;
		
		boolean encontre = false;
		List<Edge<String>> aristas = mapaCiudades.getEdges(vertice);
		for (Edge<String> arista: aristas) {
			if (!visitados[arista.getTarget().getPosition()] && !encontre) {
				encontre = caminoHelper(arista.getTarget(), visitados, destino, camino);
			}
		}
		if (!encontre) camino.remove(camino.size()-1);
		
		return encontre;
	}
	
	public List<String> devolverCaminoExceptuando(String ciudad1, String ciudad2, List<String> ciudades) {
		List<String> camino = new LinkedList<String>();
		boolean[] visitados = new boolean[mapaCiudades.getSize()];
		
		if (!mapaCiudades.isEmpty()) {
			Vertex<String> origen = mapaCiudades.search(ciudad1);
			Vertex<String> destino = mapaCiudades.search(ciudad2);
			
			if (origen != null && destino != null) {
				caminoExceptuandoHelper(origen, visitados, destino, camino, ciudades);
				return camino;
			}
			
		}
		return null;
	}
	
	private boolean caminoExceptuandoHelper(Vertex<String> vertice, boolean[] visitados, Vertex<String> destino, List<String> camino, List<String> ciudades) {
		visitados[vertice.getPosition()] = true;
		camino.add(vertice.getData());
		
		if (vertice == destino) return true;
		
		boolean encontre = false;
		List<Edge<String>> aristas = mapaCiudades.getEdges(vertice);
		for (Edge<String> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			String ciudad = arista.getTarget().getData();
			
			if (ciudades.contains(ciudad)) visitados[pos] = true;
			
			if (!visitados[pos] && !encontre) {
				encontre = caminoExceptuandoHelper(arista.getTarget(), visitados, destino, camino, ciudades);
			}
		}
		if (!encontre) {
			camino.remove(camino.size()-1);
			visitados[vertice.getPosition()] = false;
		}
		return encontre;
	}
	
	public List<String> caminoMasCorto(String ciudad1, String ciudad2) {
		List<String> caminoActual = new LinkedList<String>(), caminoMin = new LinkedList<String>();
		boolean[] visitados = new boolean[mapaCiudades.getSize()];
		
		if (!mapaCiudades.isEmpty()) {
			Vertex<String> origen = mapaCiudades.search(ciudad1);
			Vertex<String> destino = mapaCiudades.search(ciudad2);
			
			if (origen != null && destino != null) {
				caminoCortoHelper(origen, visitados, destino, caminoMin, caminoActual, 0, Integer.MAX_VALUE);
				return caminoMin;
			}
		}
		return null;
	}
	
	private int caminoCortoHelper(Vertex<String> vertice, boolean[] visitados, Vertex<String> destino, List<String> caminoMin, List<String> caminoActual, int actual, int min) {
		caminoActual.add(vertice.getData());
		visitados[vertice.getPosition()] = true;
		
		if (vertice == destino && actual < min) {
			caminoMin.removeAll(caminoMin);
			caminoMin.addAll(caminoActual);
			min = actual;
		}
		
		List<Edge<String>> aristas = mapaCiudades.getEdges(vertice);
		for (Edge<String> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			int peso = actual + arista.getWeight();
			
			if (!visitados[pos] && peso < min) {
				min = caminoCortoHelper(arista.getTarget(), visitados, destino, caminoMin, caminoActual, peso, min);
			}
		}
		caminoActual.remove(caminoActual.size()-1);
		visitados[vertice.getPosition()] = false;
		return min;
	}
	
	public List<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto) {
		List<String> camino = new LinkedList<String>();
		boolean[] visitados = new boolean[mapaCiudades.getSize()];
		
		if (!mapaCiudades.isEmpty()) {
			Vertex<String> origen = mapaCiudades.search(ciudad1);
			Vertex<String> destino = mapaCiudades.search(ciudad2);
			
			if (origen != null && destino != null) {
				caminoSinCargarHelper(origen, visitados, destino, tanqueAuto, camino);
				return camino;
			}
		}
		return null;
	}
	
	private boolean caminoSinCargarHelper(Vertex<String> vertice, boolean[] visitados, Vertex<String> destino, int tanqueAuto, List<String> camino) {
		camino.add(vertice.getData());
		visitados[vertice.getPosition()] = true;
		
		if (vertice == destino) return true;
		
		boolean encontre = false;
		List<Edge<String>> aristas = mapaCiudades.getEdges(vertice);
		for (Edge<String> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			int combustibleNecesario = arista.getWeight();
			
			if (!visitados[pos] && tanqueAuto-combustibleNecesario > 0) {
				encontre = caminoSinCargarHelper(arista.getTarget(), visitados, destino, tanqueAuto-combustibleNecesario, camino);
			}
		}
		if (!encontre) camino.remove(camino.size()-1);
		visitados[vertice.getPosition()] = false;
		
		return encontre;
	}
	
	public List<String> caminoConMenorCargaDeCombustible(String ciudad1, String ciudad2, int tanqueAuto) {
		List<String> caminoActual = new LinkedList<String>(), caminoMin = new LinkedList<String>();
		boolean[] visitados = new boolean[mapaCiudades.getSize()];
		
		if (!mapaCiudades.isEmpty()) {
			Vertex<String> origen = mapaCiudades.search(ciudad1);
			Vertex<String> destino = mapaCiudades.search(ciudad2);
			
			if (origen != null && destino != null) {
				caminoConMenorCargaHelper(origen, visitados, destino, caminoMin, caminoActual, tanqueAuto, tanqueAuto, 0, Integer.MAX_VALUE);
				return caminoMin;
			}
		}
		return null;
	}
	
	private int caminoConMenorCargaHelper(Vertex<String> vertice, boolean[] visitados, Vertex<String> destino, List<String> caminoMin, List<String> caminoActual, int tanqueActual, int tanque, int recargas, int minRecargas) {
		caminoActual.add(vertice.getData());
		visitados[vertice.getPosition()] = true;
		
		if (vertice == destino && recargas < minRecargas) {
			caminoMin.removeAll(caminoMin);
			caminoMin.addAll(caminoActual);
			minRecargas = recargas;
		}
		
		List<Edge<String>> aristas = mapaCiudades.getEdges(vertice);
		for (Edge<String> arista: aristas) {
			int pos = arista.getTarget().getPosition();
			int combustibleNecesario = arista.getWeight();
			
			if (!visitados[pos]) {
				if (tanqueActual >= combustibleNecesario) {
					minRecargas = caminoConMenorCargaHelper(arista.getTarget(), visitados, destino, caminoMin, caminoActual, (tanqueActual - combustibleNecesario), tanque, recargas, minRecargas);
				}
				if (tanque >= combustibleNecesario) {
					minRecargas = caminoConMenorCargaHelper(arista.getTarget(), visitados, destino, caminoMin, caminoActual, (tanque - combustibleNecesario), tanque, recargas+1, minRecargas);
				}
			}
		}
		caminoActual.remove(caminoActual.size()-1);
		visitados[vertice.getPosition()] = false;
		return minRecargas;
	}

	public static void main(String[] args) {
		Graph<String> mapaCiudades = new AdjListGraph<String>();
        Vertex<String> v1 = mapaCiudades.createVertex("La Plata"); //Casa Caperucita
        Vertex<String> v2 = mapaCiudades.createVertex("Ensenada"); //Claro 3
        Vertex<String> v3 = mapaCiudades.createVertex("Berisso"); //Claro 1
        Vertex<String> v4 = mapaCiudades.createVertex("Berazategui"); //Claro 2
        Vertex<String> v5 = mapaCiudades.createVertex("Florencio Varela"); //Claro 5
        Vertex<String> v6 = mapaCiudades.createVertex("Presidente Peron"); //Claro 4
        Vertex<String> v7 = mapaCiudades.createVertex("San Vicente"); //Casa Abuelita
        mapaCiudades.connect(v1, v2, 4);
        mapaCiudades.connect(v2, v1, 4);
        mapaCiudades.connect(v1, v3, 3);
        mapaCiudades.connect(v3, v1, 3);
        mapaCiudades.connect(v1, v4, 4);
        mapaCiudades.connect(v4, v1, 4);
        mapaCiudades.connect(v2, v5, 15);
        mapaCiudades.connect(v5, v2, 15);
        mapaCiudades.connect(v3, v5, 3);
        mapaCiudades.connect(v5, v3, 3);
        mapaCiudades.connect(v4, v3, 4);
        mapaCiudades.connect(v3, v4, 4);
        mapaCiudades.connect(v4, v5, 11);
        mapaCiudades.connect(v5, v4, 11);
        mapaCiudades.connect(v4, v6, 10);
        mapaCiudades.connect(v6, v4, 10);
        mapaCiudades.connect(v4, v3, 4);
        mapaCiudades.connect(v3, v4, 4);
        mapaCiudades.connect(v5, v7, 4);
        mapaCiudades.connect(v7, v5, 4);
        mapaCiudades.connect(v6, v7, 9);
        mapaCiudades.connect(v7, v6, 9);
        
        Mapa mapa = new Mapa(mapaCiudades);
        
        System.out.print("LISTA DEVOLVER CAMINO: ");
        System.out.print(mapa.devolverCamino("La Plata", "San Vicente"));
        
        System.out.println("");
        
        System.out.print("LISTA DEVOLVER CAMINO EXCEPTUANDO LUGARES:");
        List<String> restringidos = new LinkedList<String>();
        restringidos.add("Berisso");
        System.out.print(mapa.devolverCaminoExceptuando("La Plata", "San Vicente", restringidos));
        
        System.out.println("");
        
        System.out.print("LISTA CAMINO MAS CORTO (SEGUN DISTANCIA): ");
        System.out.print(mapa.caminoMasCorto("La Plata", "San Vicente"));
        
        System.out.println("");
        
        System.out.print("LISTA CAMINO SIN CARGAR COMBUSTIBLE: ");
        System.out.print(mapa.caminoSinCargarCombustible("La Plata", "San Vicente", 30));
        
        System.out.println("");
        
        System.out.print("LISTA CAMINO CON MENOR CARGAS DE COMBUSTIBLE: ");
        System.out.print(mapa.caminoConMenorCargaDeCombustible("La Plata", "San Vicente", 10));
        
        System.out.println("");
	}

}
