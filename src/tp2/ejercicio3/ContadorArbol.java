package tp2.ejercicio3;

import java.util.LinkedList;

import tp2.ejercicio1.BinaryTree;

public class ContadorArbol {
	
	BinaryTree<Integer> a = new BinaryTree<Integer>();
	
	public ContadorArbol(BinaryTree<Integer> arbol) {
		this.a = arbol;
	}
	
	public LinkedList<Integer> numerosPares() {
		LinkedList<Integer> lista = new LinkedList<Integer>();
		if (!a.isEmpty()) {
			preOrden(lista, a);
			inOrden(lista, a);
			postOrden(lista, a);
		}
		return lista;
	}
	
	private void preOrden(LinkedList<Integer> lista, BinaryTree<Integer> a) {
		if (a.getData() % 2 == 0) {
			lista.add(a.getData());
		}
		if (a.hasLeftChild()) {
			inOrden(lista, a.getLeftChild());
		}
		if (a.hasRightChild()) {
			inOrden(lista, a.getRightChild());
		}
	}
	
	private void inOrden(LinkedList<Integer> lista, BinaryTree<Integer> a) {
		if (a.hasLeftChild()) {
			inOrden(lista, a.getLeftChild());
		}
		if (a.getData() % 2 == 0) {
			lista.add(a.getData());
		}
		if (a.hasRightChild()) {
			inOrden(lista, a.getRightChild());
		}
	}
	
	private void postOrden(LinkedList<Integer> lista, BinaryTree<Integer> a) {
		if (a.hasLeftChild()) {
			inOrden(lista, a.getLeftChild());
		}
		if (a.hasRightChild()) {
			inOrden(lista, a.getRightChild());
		}
		if (a.getData() % 2 == 0) {
			lista.add(a.getData());
		}
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> ab = new BinaryTree<Integer>(4);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        
        ContadorArbol c = new ContadorArbol(ab);
        
        System.out.println("");
        LinkedList<Integer> lis = c.numerosPares();
        System.out.println("Los nodos pares del arbol en INORDER son: ");
        System.out.println(lis);
	}

}
