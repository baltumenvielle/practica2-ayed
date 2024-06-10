package tp2.ejercicio9;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {
	
	public BinaryTree<SumDif> sumAndDif(BinaryTree<Integer> arbol) {
        BinaryTree<SumDif> a = new BinaryTree<>();
        sumAndDifHelper(arbol,a,0,0);
        return a;
    }
	
	private static void sumAndDifHelper(BinaryTree<Integer> tree, BinaryTree<SumDif> sad,int sum,int parent) {
        SumDif sumAndDif = new SumDif(sum+tree.getData(), tree.getData()-parent);
        sad.setData(sumAndDif);
        if (tree.hasLeftChild()) {
            sad.addLeftChild(new BinaryTree<SumDif>());
            sumAndDifHelper(tree.getLeftChild(),sad.getLeftChild(),sum+tree.getData(),tree.getData());
        }
        if (tree.hasRightChild()) {
            sad.addRightChild(new BinaryTree<SumDif>());
            sumAndDifHelper(tree.getRightChild(),sad.getRightChild(),sum+tree.getData(),tree.getData());
        }
    }

	public static void main(String[] args) {
		ParcialArboles parcialArb = new ParcialArboles();

        BinaryTree<Integer> ab = new BinaryTree<Integer>(20);
        ab.addLeftChild(new BinaryTree<Integer>(5));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(-5));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(10));
        ab.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.addRightChild(new BinaryTree<Integer>(30));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(50));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(-9));
        ab.getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(4));
        ab.getRightChild().getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(6));

        ab.entreNiveles(0, 4);
        System.out.println();
        System.out.println("Nuevo arbol:");
        parcialArb.sumAndDif(ab).entreNiveles(0, 4);
	}

}
