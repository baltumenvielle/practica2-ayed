package tp3.ejercicio10;

import tp3.ejercicio1.GeneralTree;
import java.util.List;
import java.util.LinkedList;

public class ParcialArboles {
	
	public static List<Integer> resolver(GeneralTree<Integer> arbol) {
		List<Integer> caminoActual = new LinkedList<Integer>(), caminoMax = new LinkedList<Integer>();
		resolverHelper(arbol, caminoMax, caminoActual, 0, 0, 0);
		return caminoMax;
	}
	
	private static int resolverHelper(GeneralTree<Integer> arbol, List<Integer> caminoMax, List<Integer> caminoActual, int nivel, int sumaActual, int sumaMax) {
		if (arbol.getData() == 1) {
			caminoActual.add(arbol.getData());
			sumaActual += nivel;
		}
		
		List<GeneralTree<Integer>> children = arbol.getChildren();
		for (GeneralTree<Integer> child: children) {
			sumaMax = resolverHelper(child, caminoMax, caminoActual, nivel+1, sumaActual, sumaMax);
		}
		
		if (sumaActual > sumaMax) {
			sumaMax = sumaActual;
			caminoMax.removeAll(caminoMax);
			caminoMax.addAll(caminoActual);
		}
		if (arbol.getData() == 1) {
			sumaActual -= nivel;
			caminoActual.remove(caminoActual.size()-1);
		}
		
		return sumaMax;
	}

	public static void main(String[] args) {
        List<GeneralTree<Integer>> subChildren1 = new LinkedList<GeneralTree<Integer>>();
        subChildren1.add(new GeneralTree<Integer>(1));
        subChildren1.add(new GeneralTree<Integer>(1));
        subChildren1.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> subA = new GeneralTree<Integer>(1, subChildren1);
        List<GeneralTree<Integer>> subChildren2 = new LinkedList<GeneralTree<Integer>>();
        subChildren2.add(subA);
        subChildren2.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(0, subChildren2);
        
        List<GeneralTree<Integer>> subChildren3 = new LinkedList<GeneralTree<Integer>>();
        subChildren3.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> subB = new GeneralTree<Integer>(0, subChildren3);
        List<GeneralTree<Integer>> subChildren4 = new LinkedList<GeneralTree<Integer>>();
        subChildren4.add(subB);
        GeneralTree<Integer> subC = new GeneralTree<Integer>(0, subChildren4);
        List<GeneralTree<Integer>> subChildren5 = new LinkedList<GeneralTree<Integer>>();
        subChildren5.add(new GeneralTree<Integer>(1));
        subChildren5.add(subC);
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(1, subChildren5);
        
        List<GeneralTree<Integer>> subChildren6 = new LinkedList<GeneralTree<Integer>>();
        subChildren6.add(new GeneralTree<Integer>(0));
        subChildren6.add(new GeneralTree<Integer>(0));
        GeneralTree<Integer> subD = new GeneralTree<Integer>(0, subChildren6);
        List<GeneralTree<Integer>> subChildren7 = new LinkedList<GeneralTree<Integer>>();
        subChildren7.add(subD);
        GeneralTree<Integer> subE = new GeneralTree<Integer>(0, subChildren7);
        List<GeneralTree<Integer>> subChildren8 = new LinkedList<GeneralTree<Integer>>();
        subChildren8.add(subE);
        GeneralTree<Integer> a3 = new GeneralTree<Integer>(1, subChildren8);
        
        List<GeneralTree<Integer>> arbol = new LinkedList<GeneralTree<Integer>>();
        arbol.add(a1);
        arbol.add(a2);
        arbol.add(a3);
        GeneralTree<Integer> a = new GeneralTree<Integer>(1, arbol);
        
        System.out.println(resolver(a));
	}

}
