package tp2.ejercicio4;

import tp2.ejercicio1.BinaryTree;

public class RedBinariaLlena {
	
	BinaryTree<Integer> a = new BinaryTree<Integer>();
	
	public RedBinariaLlena(BinaryTree<Integer> arbol) {
		this.a = arbol;
	}
	
	public int retardoReenvio() {
		if (!a.isEmpty()) {
			return retardoHelper(a);
		}
		return 0;
	}
	
	private int retardoHelper(BinaryTree<Integer> a) {
		int hi = 0, hd = 0;
		if (a.hasLeftChild()) {
			hi += retardoHelper(a.getLeftChild());
		}
		if (a.hasRightChild()) {
			hd += retardoHelper(a.getRightChild());
		}
		return Math.max(hi, hd) + a.getData();
	}

	public static void main(String[] args) {
		BinaryTree<Integer> ab = new BinaryTree<Integer>(4);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        
        RedBinariaLlena red = new RedBinariaLlena(ab);
        System.out.println("El mayor retardo posible es de: " + red.retardoReenvio() + " segundos");
	}

}
