package tp2.ejercicio5;

import tp2.ejercicio1.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class ProfundidadDeArbolBinario {
	
	BinaryTree<Integer> a = new BinaryTree<Integer>();
	
	public ProfundidadDeArbolBinario(BinaryTree<Integer> arbol) {
		this.a = arbol;
	}
	
	public int sumaElementosProfundidad(int p) {
		int suma = 0;
		if (!a.isEmpty()) {
			Queue<BinaryTree<Integer>>cola = new LinkedList<BinaryTree<Integer>>();
			BinaryTree<Integer> nodo;
			
			cola.add(a);
			cola.add(null);
			
			int nivel = 0;
			
			while (!cola.isEmpty()) {
				nodo = cola.remove();
				if (nodo != null) {
					if (nivel == p) {
						suma += nodo.getData();
					}
					else if (nivel > p) {
						break;
					}
					if (nodo.hasLeftChild()) {
						cola.add(nodo.getLeftChild());
					}
					if (nodo.hasRightChild()) {
						cola.add(nodo.getRightChild());
					}
				}
				else if (!cola.isEmpty()) {
					nivel++;
					cola.add(null);
				}
			}
		}
		return suma;
	}

	public static void main(String[] args) {
		BinaryTree<Integer> ab = new BinaryTree<Integer>(4);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        
        ProfundidadDeArbolBinario prof = new ProfundidadDeArbolBinario(ab);
        System.out.println(prof.sumaElementosProfundidad(2));
	}

}
