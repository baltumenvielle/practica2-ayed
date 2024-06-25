package parcial6;

import tp3.ejercicio1.GeneralTree;

import java.util.LinkedList;
import java.util.List;

public class Parcial {
	
	GeneralTree<Integer> a = new GeneralTree<Integer>();
	
	public List<Integer> resolver() {
		List<Integer> lista = new LinkedList<Integer>();
		resolverHelper(lista, a, 0);
		return lista;
	}
	
	private void resolverHelper(List<Integer> lista, GeneralTree<Integer> nodo, int suma) {
		if (nodo.hasChildren()) {
			List<GeneralTree<Integer>> children = nodo.getChildren();
			suma = 0;
			for (GeneralTree<Integer> child: children) {
				resolverHelper(lista, child, suma);
				suma += child.getData();
			}
			if (children.size() % 2 != 0) {
				lista.add(suma);
			}
		}
	}

	public static void main(String[] args) {

	}

}
