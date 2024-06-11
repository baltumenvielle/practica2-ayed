package tp3.ejercicio2;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import tp3.ejercicio1.GeneralTree;

public class RecorridosAG {
	
	public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		if (!a.isEmpty()) {
			helperPreOrden(a, lista, n);
		}
		return lista;
	}
	
	private void helperPreOrden(GeneralTree<Integer> a, List<Integer> lista, Integer n) {
		List<GeneralTree<Integer>> children = a.getChildren();
		int num = a.getData();
		
		if (num> n && num % 2 != 0)  lista.add(num);
		
		if (a.hasChildren()) {
			for (GeneralTree<Integer> child: children) {
				helperPreOrden(child, lista, n);
			}
		}
	}
	
	public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		if (!a.isEmpty()) {
			helperInOrden(a, lista, n);
		}
		return lista;
	}
	
	private void helperInOrden(GeneralTree<Integer> a, List<Integer> lista, Integer n) {
		List<GeneralTree<Integer>> children = a.getChildren();
		if (a.hasChildren()) {
			helperInOrden(children.get(0), lista, n);
		}
		
		int num = a.getData();
		if (num > n && num % 2 != 0) lista.add(num);
		
		for (int i=1;i<children.size();i++) {
			helperInOrden(children.get(i), lista, n);
		}
	}
	
	public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		if (!a.isEmpty()) {
			helperPostOrden(a, lista, n);
		}
		return lista;
	}
	
	private void helperPostOrden(GeneralTree<Integer> a, List<Integer> lista, Integer n) {
		List<GeneralTree<Integer>> children = a.getChildren();
		if (a.hasChildren()) {
			for (GeneralTree<Integer> child: children) {
				helperPostOrden(child, lista, n);
			}
		}
		int num = a.getData();
		if (num> n && num % 2 != 0)  lista.add(num);
	}
	
	public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		Queue<GeneralTree<Integer>> cola = new LinkedList<GeneralTree<Integer>>();
		
		if (!a.isEmpty()) {
			cola.add(a);
			cola.add(null);
			
			while (!cola.isEmpty()) {
				GeneralTree<Integer> nodo = cola.remove();
				if (nodo != null) {
					int num = nodo.getData();
					if (num % 2 != 0 && num > n) lista.add(num);
					
					List<GeneralTree<Integer>> children = nodo.getChildren();
					for (GeneralTree<Integer> child: children) {
						cola.add(child);
					}
				}
				else if (!cola.isEmpty()) {
					cola.add(null);
				}
			}
			
		}
		return lista;
	}

	public static void main(String[] args) {
		GeneralTree<Integer> a1 = new GeneralTree<Integer>(1);
        List<GeneralTree<Integer>> children2 = new LinkedList<GeneralTree<Integer>>();
        children2.add(new GeneralTree<Integer>(21));
        children2.add(new GeneralTree<Integer>(22));
        children2.add(new GeneralTree<Integer>(23));
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(2, children2);
        List<GeneralTree<Integer>> children3 = new LinkedList<GeneralTree<Integer>>();
        children3.add(new GeneralTree<Integer>(31));
        children3.add(new GeneralTree<Integer>(32));
        GeneralTree<Integer> a3 = new GeneralTree<Integer>(3, children3);
        List<GeneralTree<Integer>> children = new LinkedList<GeneralTree<Integer>>();
        children.add(a1);
        children.add(a2);
        children.add(a3);
        GeneralTree<Integer> a = new GeneralTree<Integer>(11, children);
        
        System.out.println("POR NIVELES: " + a.numerosImparesMayoresQuePorNiveles(0));
	}

}
