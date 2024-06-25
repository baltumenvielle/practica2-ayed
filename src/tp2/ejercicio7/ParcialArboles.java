package tp2.ejercicio7;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {
	
	private BinaryTree<Integer> a = new BinaryTree<Integer>();
	
	public ParcialArboles(BinaryTree<Integer> arbol) {
		this.a = arbol;
	}
	
	public boolean isLeftTree(int num) {
		BinaryTree<Integer> nodo = buscar(a, num);
		
		if (nodo == null) return false;
		
		int hi, hd;
		
		if (nodo.hasLeftChild()) hi = contar(nodo.getLeftChild());
		else hi = -1;
		if (nodo.hasRightChild()) hd = contar(nodo.getRightChild());
		else hd = -1;
		
		return hi > hd;
	}
	
	private BinaryTree<Integer> buscar(BinaryTree<Integer> a, int num) {
		if (a.getData() == num) return a;
		
		BinaryTree<Integer> aux = new BinaryTree<Integer>();
		aux = null;
		
		if (a.hasLeftChild()) {
			aux = buscar(a.getLeftChild(), num);
		}
		if (a.hasRightChild() && aux == null) {
			aux = buscar(a.getRightChild(), num);
		}
		
		if (aux != null) return aux;
		return null;
	}
	
	private int contar(BinaryTree<Integer> a) {
		boolean izq = a.hasLeftChild(), der = a.hasRightChild();
		int suma = 0;
		
		if (izq && !der || !izq && der) {
			suma++;
		}
		if (izq) {
			suma += contar(a.getLeftChild());
		}
		if (der) {
			suma += contar(a.getRightChild());
		}
		
		return suma;
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> a = new BinaryTree<Integer>(2);
		BinaryTree<Integer> izq = new BinaryTree<Integer>(7);
		izq.addLeftChild(new BinaryTree<Integer>(23));
		izq.addRightChild(new BinaryTree<Integer>(6));
		a.addLeftChild(izq);
		BinaryTree<Integer> nodo = new BinaryTree<Integer>(-3);
		izq.getLeftChild().addLeftChild(nodo);
		nodo = new BinaryTree<Integer>(55);
		izq.getRightChild().addLeftChild(nodo);
		nodo = new BinaryTree<Integer>(11);
		izq.getRightChild().addRightChild(nodo);
		
		BinaryTree<Integer> der = new BinaryTree<Integer>(-5);
		der.addLeftChild(new BinaryTree<Integer>(19));
		a.addRightChild(der);
		nodo = new BinaryTree<Integer>(4);
		der.getLeftChild().addRightChild(nodo);
		BinaryTree<Integer> aux = der.getLeftChild().getRightChild();
		nodo = new BinaryTree<Integer>(18);
		aux.addLeftChild(nodo);
		
		a.imprimirArbol();
		
		ParcialArboles arb = new ParcialArboles(a);
		
		System.out.println(arb.isLeftTree(7)); // true
		System.out.println(arb.isLeftTree(2)); // false
		System.out.println(arb.isLeftTree(-5)); // true
		System.out.println(arb.isLeftTree(19)); // false
		System.out.println(arb.isLeftTree(-3)); // false
	}

}
