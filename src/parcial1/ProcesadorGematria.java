package parcial1;

import tp3.ejercicio1.GeneralTree;
import java.util.List;

public class ProcesadorGematria {
	
	public int contar(GeneralTree<Integer> a, int valor) {
		return contarHelper(a, valor, 0);
	}
	
	private int contarHelper(GeneralTree<Integer> a, int valor, int suma) {
		if (a.isLeaf()) {
			suma += a.getData();
			if (suma == valor) {
				suma = 0;
				return 1;
			}
			return 0;
		}
		else if (suma + a.getData() < valor) {
			int cant = 0;
			
			List<GeneralTree<Integer>> children = a.getChildren();
			for (GeneralTree<Integer> child: children) {
				cant += contarHelper(child, valor, suma);
			}
			return cant;
		}
		else return 0;
	}
	
}
