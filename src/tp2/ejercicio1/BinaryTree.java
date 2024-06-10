package tp2.ejercicio1;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree <T> {
	
	private T data;
	private BinaryTree<T> leftChild;   
	private BinaryTree<T> rightChild; 

	
	public BinaryTree() {
		super();
	}

	public BinaryTree(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Preguntar antes de invocar si hasLeftChild()
	 * @return
	 */
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}
	/**
	 * Preguntar antes de invocar si hasRightChild()
	 * @return
	 */
	public BinaryTree<T> getRightChild() {
		return this.rightChild;
	}

	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild = child;
	}

	public void addRightChild(BinaryTree<T> child) {
		this.rightChild = child;
	}

	public void removeLeftChild() {
		this.leftChild = null;
	}

	public void removeRightChild() {
		this.rightChild = null;
	}

	public boolean isEmpty(){
		return (this.isLeaf() && this.getData() == null);
	}

	public boolean isLeaf() {
		return (!this.hasLeftChild() && !this.hasRightChild());

	}
		
	public boolean hasLeftChild() {
		return this.leftChild!=null;
	}

	public boolean hasRightChild() {
		return this.rightChild!=null;
	}
	@Override
	public String toString() {
		return this.getData().toString();
	}

	public  int contarHojas() {
		if (!this.isEmpty()) {
			int cantHojas = this.contarHojasHelper(this);
			return cantHojas;
		}
		else return 0;
	}
		
	private int contarHojasHelper(BinaryTree<T> a) {
		if (a.isLeaf()) {
			return 1;
		}
		else {
			int cant = 0;
			if (a.hasLeftChild()) {
				cant += contarHojasHelper(a.getLeftChild());
			}
			if (a.hasRightChild()) {
				cant += contarHojasHelper(a.getRightChild());
			}
			return cant;
		}
	}
    	 
    //public BinaryTree<T> espejo(){
    	//BinaryTree<T> espejo = new BinaryTree<T>(this.getData());
    	//if (!this.isEmpty()) {
    		//espejo.helperEspejo(this);
    	//}
    	//return espejo;
    //}
	
	public BinaryTree<T> espejo(){
		BinaryTree<T> espejo = new BinaryTree<T>(this.getData());
	    if (this.hasLeftChild()) {
	    	espejo.addRightChild(this.getLeftChild().espejo());
	    }
	    if (this.hasRightChild()) {
	    	espejo.addLeftChild(this.getRightChild().espejo());
	    }
	    
	    return espejo;
    }

	// 0<=n<=m
	public void entreNiveles(int n, int m){
		Queue<BinaryTree<T>> cola = new LinkedList<BinaryTree<T>>();
		BinaryTree<T> nodo;
		
		cola.add(this);
		cola.add(null);
		int nivel = 0;
		while (!cola.isEmpty()) {
			nodo = cola.remove();
			if (nodo != null) {
				if ((nivel >= n) && (nivel <= m)) {
					System.out.println(nodo.getData());
				}
				if (nodo.hasLeftChild()) {
					cola.add(nodo.getLeftChild());
				}
				if (nodo.hasRightChild()) {
					cola.add(nodo.getRightChild());
				}
			}
			else if (!cola.isEmpty()){
				nivel++;
				cola.add(null);
			}
		}
   }
	
    public void imprimirArbol() {
        if(this.hasLeftChild()) this.getLeftChild().imprimirArbol();
        System.out.print(this.getData() + " ");
        if(this.hasRightChild()) this.getRightChild().imprimirArbol();
    }
	
	public static void main (String[] args){
        BinaryTree<Integer> ab = new BinaryTree<Integer>(4);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        //ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        
        System.out.println(ab.contarHojas());
        System.out.println("Arbol original: ");
        ab.entreNiveles(1, 2);
        System.out.println("Arbol espejo: ");
        BinaryTree<Integer> abEspejo = ab.espejo();
        abEspejo.entreNiveles(1, 2);
        System.out.println("Impresion Arbol por niveles 0 y 1");
        ab.entreNiveles(0, 1);
	}
		
}

