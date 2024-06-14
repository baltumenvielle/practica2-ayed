package parcial3;

import tp3.ejercicio1.GeneralTree;

import java.util.LinkedList;
import java.util.List;

public class PadreMasPeque {
	
	public List<GeneralTree<Integer>> arbolesMasPeque(GeneralTree<Integer> a) {
		List<GeneralTree<Integer>> lista = new LinkedList<GeneralTree<Integer>>();
		pequeHelper(a, lista);
		return lista;
	}
	
	private void pequeHelper(GeneralTree<Integer> a, List<GeneralTree<Integer>> lista) {
		if (a.isLeaf()) {
			lista.add(a);
			return;
		}
		int suma = 0;
		List<GeneralTree<Integer>> children = a.getChildren();
		for (GeneralTree<Integer> child: children) {
			pequeHelper(child, lista);
			suma += child.getData();
		}
		if (a.getData() < suma) lista.add(a);
		return;
	}

	public static void main(String[] args) {

	}

}
