package tp3.ejercicio4;

import tp3.ejercicio1.GeneralTree;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class AnalizadorArbol {
	
	public double devolverMaximoPromedio (GeneralTree<AreaEmpresa> arbol) {
		Queue<GeneralTree<AreaEmpresa>> cola = new LinkedList<GeneralTree<AreaEmpresa>>();
		double maxPromedio = 0;
		
		if (!arbol.isEmpty()) {
			cola.add(arbol);
			cola.add(null);
			
			int suma = 0, nodos = 0;
			
			while (!cola.isEmpty()) {
				GeneralTree<AreaEmpresa> nodo = cola.remove();
				
				if (nodo != null) {
					List<GeneralTree<AreaEmpresa>> children = nodo.getChildren();
					suma += nodo.getData().getTardanza();
					nodos++;
					for (GeneralTree<AreaEmpresa> child: children) {
						cola.add(child);
					}
				}
				else if (!cola.isEmpty()) {
					cola.add(null);
					double promedio = suma / nodos;
					maxPromedio = Math.max(maxPromedio, promedio);
					suma = 0;
					nodos = 0;
				}
			}
		}
		return maxPromedio;
	}
}
