package parcial4;

import tp2.ejercicio1.BinaryTree;

public class Parcial {
	BinaryTree<Integer> a = new BinaryTree<Integer>();
	
	public Parcial(BinaryTree<Integer> a) {
		this.a = a;
	}
	
	public boolean resolver(int k) {
		return resolverHelper(a, k, 0);
	}
	
	private boolean resolverHelper(BinaryTree<Integer> a, int k, int suma) {
		if (a.isLeaf()) {
			if (suma + a.getData() == k) return true;
			return false;
		}
		boolean ok = true;
		if (a.hasLeftChild()) {
			ok = resolverHelper(a.getLeftChild(), k, suma + a.getData());
		}
		if (a.hasRightChild() && ok) {
			ok = resolverHelper(a.getRightChild(), k, suma + a.getData());
		}
		return ok;
	}
	
	public static void main(String[] args) {
        System.out.println("Test Ejercicio 7");

        BinaryTree<Integer> ab = new BinaryTree<>(2);
        ab.addLeftChild(new BinaryTree<>(1));
        ab.addRightChild(new BinaryTree<>(2));

        ab.getLeftChild().addLeftChild(new BinaryTree<>(5));
        ab.getLeftChild().addRightChild(new BinaryTree<>(4));
        ab.getLeftChild().getRightChild().addLeftChild(new BinaryTree<>(1));
        ab.getLeftChild().getRightChild().addRightChild(new BinaryTree<>(1));

        ab.getRightChild().addLeftChild(new BinaryTree<>(1));
        ab.getRightChild().addRightChild(new BinaryTree<>(2));
        ab.getRightChild().getRightChild().addRightChild(new BinaryTree<>(2));
        ab.getRightChild().getLeftChild().addLeftChild(new BinaryTree<>(3));
        
        Parcial aux = new Parcial(ab);
        System.out.print(aux.resolver(8));
}
}
