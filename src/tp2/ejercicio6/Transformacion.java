package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

public class Transformacion {
	
	private BinaryTree<Integer> a = new BinaryTree<Integer>();
	
	public Transformacion(BinaryTree<Integer> arbol) {
		this.a = arbol;
	}
	
	public BinaryTree<Integer> suma() {
		sumaRecursivo(a);
		return a;
	}
	
	private int sumaRecursivo(BinaryTree<Integer> a) {
		int suma = 0;
		if (a.isLeaf()) {
			int aux = a.getData();
			a.setData(0);
			return aux;
		}
		if (a.hasLeftChild()) {
			suma += sumaRecursivo(a.getLeftChild());
		}
		if (a.hasRightChild()) {
			suma += sumaRecursivo(a.getRightChild());
		}
		int actual = a.getData();
		a.setData(suma);
		return suma + actual;
	}

	public static void main(String[] args) {
		BinaryTree<Integer> a = new BinaryTree<Integer>(24);
		BinaryTree<Integer> izq = new BinaryTree<Integer>(10);
		izq.addLeftChild(new BinaryTree<Integer>(5));
		izq.addRightChild(new BinaryTree<Integer>(12));
		BinaryTree<Integer> der = new BinaryTree<Integer>(32);
		der.addRightChild(new BinaryTree<Integer>(50));
		a.addRightChild(der);
		a.addLeftChild(izq);
		
		Transformacion t = new Transformacion(a);
		
		t.suma().imprimirArbol();
	}

}
